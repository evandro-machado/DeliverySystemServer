package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Delivery;
import entity.Employee;
import resources.ConnectionFactory;

public class DeliveryDao {
	private final String addDeliveryStmt = "insert into delivery (fkemployee, address, image, description, status, downloaded) values (?,?,?,?,'Ready to deliver', false)";
	private final String getDeliveryInfoStmt = "select iddelivery, address, description, status, login from delivery,employee where fkemployee = idemployee";
	private final String getDeliveryByDeliverymanIdStmt = "select iddelivery, address, description, status, image from delivery where (fkemployee = ?) AND (downloaded = false)";
	private final String getDeliveryImageStmt = "select image from delivery where iddelivery = ?";
	private final String updateDownloadedDeliveryStmt = "update delivery set downloaded = true where fkemployee = ?";
	private final String updateDeliveryStatusStmt = "update delivery set status = ? where idDelivery = ?";

	public int addDelivery(Delivery delivery) throws ClassNotFoundException, IOException {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ConnectionFactory.getConnection();
			con.setAutoCommit(false);
			stmt = con.prepareStatement(addDeliveryStmt, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, delivery.getEmployee().getId());
			stmt.setString(2, delivery.getAddress());

			InputStream input = delivery.getImage();
			stmt.setBinaryStream(3, input, input.available());

			stmt.setString(4, delivery.getDescription());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			int idDelivery = 0;
			if (rs != null && rs.next()) {
				idDelivery = rs.getInt(1);
			}
			con.commit();
			return idDelivery;

		} catch (SQLException e) {
			throw new RuntimeException("Error inserting an employee to the database. Origin: " + e.getMessage());
		} finally {
			try {
				stmt.close();
			} catch (SQLException ex) {
				System.out.println("Error closing statement. Exception = " + ex.getMessage());
			}
			try {
				con.close();
			} catch (SQLException ex) {
				System.out.println("Error closing connection. Exception = " + ex.getMessage());
			}
		}
	}

	public InputStream getImageById(int id) throws ClassNotFoundException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = ConnectionFactory.getConnection();
			stmt = con.prepareStatement(getDeliveryImageStmt);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getBinaryStream("image");
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				rs.close();
			} catch (Exception ex) {
				System.out.println("Error closing result set. Exception = " + ex.getMessage());
			}
			try {
				stmt.close();
			} catch (SQLException ex) {
				System.out.println("Error closing statement. Exception = " + ex.getMessage());
			}
			try {
				con.close();
			} catch (SQLException ex) {
				System.out.println("Error closing connection. Exception = " + ex.getMessage());
			}
		}

	}

	public List<Delivery> getDeliveries() throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = ConnectionFactory.getConnection();
			stmt = con.prepareStatement(getDeliveryInfoStmt);
			rs = stmt.executeQuery();
			List<Delivery> deliveryList = new ArrayList<>();
			while (rs.next()) {
				Delivery delivery = new Delivery();
				Employee employee = new Employee();

				delivery.setId(rs.getInt("iddelivery"));
				delivery.setAddress(rs.getString("address"));
				delivery.setDescription(rs.getString("description"));
				delivery.setStatus(rs.getString("status"));

				employee.setLogin(rs.getString("login"));

				delivery.setEmployee(employee);

				deliveryList.add(delivery);
			}
			return deliveryList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				rs.close();
			} catch (Exception ex) {
				System.out.println("Error closing result set. Exception = " + ex.getMessage());
			}
			try {
				stmt.close();
			} catch (SQLException ex) {
				System.out.println("Error closing statement. Exception = " + ex.getMessage());
			}
			try {
				con.close();
			} catch (SQLException ex) {
				System.out.println("Error closing connection. Exception = " + ex.getMessage());
			}
		}
	}

	public List<Delivery> getDeliveriesById(int deliverymanId) throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = ConnectionFactory.getConnection();
			stmt = con.prepareStatement(getDeliveryByDeliverymanIdStmt);
			stmt.setInt(1, deliverymanId);
			rs = stmt.executeQuery();
			List<Delivery> deliveryList = new ArrayList<>();
			while (rs.next()) {
				Delivery delivery = new Delivery();
				Employee employee = new Employee();

				delivery.setId(rs.getInt("iddelivery"));
				delivery.setAddress(rs.getString("address"));
				delivery.setDescription(rs.getString("description"));
				delivery.setStatus(rs.getString("status"));
				delivery.setImage(rs.getBinaryStream("image"));

				employee.setId(deliverymanId);

				delivery.setEmployee(employee);

				deliveryList.add(delivery);
			}
			return deliveryList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				rs.close();
			} catch (Exception ex) {
				System.out.println("Error closing result set. Exception = " + ex.getMessage());
			}
			try {
				stmt.close();
			} catch (SQLException ex) {
				System.out.println("Error closing statement. Exception = " + ex.getMessage());
			}
			try {
				con.close();
			} catch (SQLException ex) {
				System.out.println("Error closing connection. Exception = " + ex.getMessage());
			}
		}
	}

	public void updateDownloadedDeliveries(int deliverymanId) throws ClassNotFoundException, IOException {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ConnectionFactory.getConnection();
			con.setAutoCommit(false);
			stmt = con.prepareStatement(updateDownloadedDeliveryStmt);

			stmt.setInt(1, deliverymanId);

			stmt.executeUpdate();
			con.commit();

		} catch (SQLException e) {
			throw new RuntimeException("Error inserting an employee to the database. Origin: " + e.getMessage());
		} finally {
			try {
				stmt.close();
			} catch (SQLException ex) {
				System.out.println("Error closing statement. Exception = " + ex.getMessage());
			}
			try {
				con.close();
			} catch (SQLException ex) {
				System.out.println("Error closing connection. Exception = " + ex.getMessage());
			}
		}
	}

	public void updateDeliveryStatus(String status, int deliveryId) throws ClassNotFoundException, IOException {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ConnectionFactory.getConnection();
			con.setAutoCommit(false);
			stmt = con.prepareStatement(updateDeliveryStatusStmt);

			stmt.setString(1, status);
			stmt.setInt(2, deliveryId);
			System.out.println(updateDeliveryStatusStmt);
			stmt.executeUpdate();
			con.commit();

		} catch (SQLException e) {
			throw new RuntimeException("Error inserting an employee to the database. Origin: " + e.getMessage());
		} finally {
			try {
				stmt.close();
			} catch (SQLException ex) {
				System.out.println("Error closing statement. Exception = " + ex.getMessage());
			}
			try {
				con.close();
			} catch (SQLException ex) {
				System.out.println("Error closing connection. Exception = " + ex.getMessage());
			}
		}
	}
}
