package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Employee;
import resources.ConnectionFactory;

public class EmployeeDao {
	private final String addEmployeeStmt = "insert into employee (login, password, role) values (?,?,'deliveryman')";
	private final String getEmployeeByLoginStmt = "select * from employee where login = ?";
	private final String getDeliverymansStmt = "select * from employee where role = 'deliveryman'";

	public int addEmployee(Employee employee) throws ClassNotFoundException {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ConnectionFactory.getConnection();
			con.setAutoCommit(false);
			stmt = con.prepareStatement(addEmployeeStmt, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, employee.getLogin());
			stmt.setString(2, employee.getPassword());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			int idCliente = 0;
			if (rs != null && rs.next()) {
				idCliente = rs.getInt(1);
			}
			con.commit();
			return idCliente;

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

	public Employee getEmployeeByLogin(String login) throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = ConnectionFactory.getConnection();
			stmt = con.prepareStatement(getEmployeeByLoginStmt);
			stmt.setString(1, login);
			rs = stmt.executeQuery();
			if (rs.next()) {
				Employee employee = new Employee();
				employee.setId(rs.getInt("idemployee"));
				employee.setLogin(login);
				employee.setPassword(rs.getString("password"));
				employee.setRole(rs.getString("role"));
				return employee;
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

	public List<Employee> getDeliverymans() throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = ConnectionFactory.getConnection();
			stmt = con.prepareStatement(getDeliverymansStmt);
			rs = stmt.executeQuery();
			List<Employee> deliverymanList = new ArrayList<>();
			while(rs.next()) {
				Employee deliveryman = new Employee();
				deliveryman.setId(rs.getInt("idemployee"));
				deliveryman.setLogin(rs.getString("login"));
				deliveryman.setRole(rs.getString("role"));
				deliverymanList.add(deliveryman);
			}
			return deliverymanList;
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
}
