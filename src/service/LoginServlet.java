package service;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import dao.DeliveryDao;
import dao.EmployeeDao;
import entity.Delivery;
import entity.Employee;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		EmployeeDao employeeDao = new EmployeeDao();
		Employee employee;
		try {
			employee = employeeDao.getEmployeeByLogin(login);
			if ("operator".equals(request.getParameter("action"))) {
				if (employee != null && employee.getPassword().equals(password) && employee.getRole().equals("operator")) {
					response.sendRedirect("./delivery?action=list");
				} else {
					request.setAttribute("isWrongPassword", true);
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
					rd.forward(request, response);
				}
			} else if ("deliveryman".equals(request.getParameter("action"))) {
				PrintWriter out = response.getWriter();
				HashMap<String, Employee> hm = new HashMap<>();
				if (employee != null && employee.getPassword().equals(password) && employee.getRole().equals("deliveryman")) {
//					DeliveryDao deliveryDao = new DeliveryDao();
//					InputStream input = deliveryDao.getImageById(1);
//					byte[] byteImage = IOUtils.toByteArray(input);
//					String base64ImageStr = java.util.Base64.getEncoder().encodeToString(byteImage);
//					employee.setRole(base64ImageStr);
					hm.put("deliveryman", employee);
					JSONObject jsonObj = JSONObject.fromObject(hm);
					
					out.print(jsonObj);
					out.flush();
				} else {
					hm.put("deliveryman", null);
					JSONObject jsonObj = JSONObject.fromObject(hm);
					out.print(jsonObj);
					out.flush();
				}
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
