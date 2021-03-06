package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.User;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/admin/Login")
public class LoginAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @throws IOException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String type = request.getParameter("type");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		HttpSession session = request.getSession();

		
		if (type.equals("login")) {
			if (new UserDAO().loginAdmin(email, password) == null) {

				response.sendRedirect("/Book/admin/Login");

			} else {

				User u = new UserDAO().loginAdmin(email, password);
				
				session.setAttribute("idAdmin", u.getId());
				session.setAttribute("email", email);
				session.setAttribute("password", password);
				session.setAttribute("userName", u.getName());
				session.setAttribute("role", u.getRole().toString());

				response.sendRedirect("/Book/admin/dasboard");
			}

		} else {
			
			session.removeAttribute( "email" );
			session.removeAttribute( "password" );
			session.removeAttribute( "userName" );
			
			response.sendRedirect("/Book/admin/Login");
		}

	}

}
