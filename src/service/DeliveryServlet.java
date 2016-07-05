package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import dao.DeliveryDao;
import dao.EmployeeDao;
import entity.Delivery;
import entity.Employee;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class DeliveryServlet
 */
@WebServlet("/delivery")
@MultipartConfig
public class DeliveryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeliveryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if ("unload".equals(request.getParameter("action"))) {
			System.out.println(request.getParameter("deliveredIds"));
			System.out.println(request.getParameter("failedIds"));
			System.out.println(request.getParameter("readyIds"));
			int[] deliveredIds = toIntArray(request.getParameter("deliveredIds"));
			int[] failedIds = toIntArray(request.getParameter("failedIds"));
			int[] readyIds = toIntArray(request.getParameter("readyIds"));
			System.out.println(Arrays.toString(deliveredIds));
			System.out.println(Arrays.toString(failedIds));
			System.out.println(Arrays.toString(readyIds));
			DeliveryDao deliveryDao = new DeliveryDao();
			try {
				for (int i = 0; i < deliveredIds.length; i++) {
					deliveryDao.updateDeliveryStatus("Delivered", deliveredIds[i]);
				}
				for (int i = 0; i < failedIds.length; i++) {
					deliveryDao.updateDeliveryStatus("Attempt Failed", failedIds[i]);
				}
				for (int i = 0; i < readyIds.length; i++) {
					deliveryDao.updateDeliveryStatus("Ready to Deliver", readyIds[i]);
				}
				HashMap<String, Boolean> hm = new HashMap<>();
				hm.put("success", true);

				JSONObject jsonObj = JSONObject.fromObject(hm);
				PrintWriter out = response.getWriter();
				out.print(jsonObj);
				out.flush();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if ("listById".equals(request.getParameter("action"))) {
			try {
				int deliverymanId = Integer.parseInt(request.getParameter("id"));
				DeliveryDao deliveryDao = new DeliveryDao();
				List<Delivery> deliveryList = deliveryDao.getDeliveriesById(deliverymanId);
				deliveryDao.updateDownloadedDeliveries(deliverymanId);
				request.setAttribute("deliveryList", deliveryList);

				PrintWriter out = response.getWriter();
				// HashMap<String, HashMap<String, String>> hm = new
				// HashMap<>();
				// List<HashMap<String,String>> listN = new ArrayList<>();
				JSONArray arrJS = new JSONArray();
				for (Delivery delivery : deliveryList) {
					HashMap<String, String> hashm = new HashMap<>();
					hashm.put("address", delivery.getAddress());
					hashm.put("description", delivery.getDescription());
					hashm.put("id", delivery.getId() + "");
					hashm.put("deliverymanId", deliverymanId + "");
					hashm.put("status", delivery.getStatus());

					byte[] byteImage = IOUtils.toByteArray(delivery.getImage());
					String base64ImageStr = java.util.Base64.getEncoder().encodeToString(byteImage);
					hashm.put("image", base64ImageStr);
					// hm.put("delivery", hashm);
					arrJS.add(hashm);
				}
				// JSONObject jsonObj = JSONObject.fromObject(arrJS);
				// JSONObject jsonObj = JSONObject.fromObject(hm);

				out.print(arrJS);
				out.flush();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		} else if ("list".equals(request.getParameter("action"))) {
			try {
				DeliveryDao deliveryDao = new DeliveryDao();
				List<Delivery> deliveryList;
				deliveryList = deliveryDao.getDeliveries();
				request.setAttribute("deliveryList", deliveryList);

				RequestDispatcher rd = getServletContext().getRequestDispatcher("/deliveryPanel.jsp");
				rd.forward(request, response);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		} else if ("addScreen".equals(request.getParameter("action"))) {
			try {
				EmployeeDao employeeDao = new EmployeeDao();
				List<Employee> deliverymanList;
				deliverymanList = employeeDao.getDeliverymans();
				request.setAttribute("deliverymanList", deliverymanList);

				RequestDispatcher rd = getServletContext().getRequestDispatcher("/newDelivery.jsp");
				rd.forward(request, response);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		} else if ("add".equals(request.getParameter("action"))) {
			Delivery delivery = new Delivery();
			Employee employee = new Employee();

			delivery.setAddress(request.getParameter("address"));
			Part part = request.getPart("image");
			delivery.setImage(part.getInputStream());

			delivery.setDescription(request.getParameter("description"));

			delivery.setEmployee(employee);
			delivery.getEmployee().setId(Integer.parseInt(request.getParameter("deliverymanid")));

			DeliveryDao deliveryDao = new DeliveryDao();
			response.sendRedirect("./delivery?action=list");

			try {
				deliveryDao.addDelivery(delivery);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public int[] toIntArray(String arrayStr) {
		String[] items = arrayStr.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ", "").split(",");

		int[] results = new int[items.length];

		for (int i = 0; i < items.length; i++) {
			try {
				results[i] = Integer.parseInt(items[i]);
			} catch (NumberFormatException nfe) {
			}
			;
		}
		return results;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
