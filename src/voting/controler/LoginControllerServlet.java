package voting.controler;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import voting.dao.UserDao;
import voting.pojo.UserPojo;

/**
 * Servlet implementation class LoginControllerServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/LoginControllerServlet" })
public class LoginControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginControllerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserPojo user = new UserPojo();
		String userid = request.getParameter("userid");
		user.setUserId(userid);
		user.setPassword(request.getParameter("password"));
		RequestDispatcher rd = null;
		try {
			String result = UserDao.validateUser(user);
			rd = request.getRequestDispatcher("loginresponse.jsp");
			request.setAttribute("result", result);
			request.setAttribute("userid", userid);
		} catch (SQLException e) {
			request.setAttribute("exception", e);
			rd = request.getRequestDispatcher("showexception.jsp");
			e.printStackTrace();
		} finally {
			rd.forward(request, response);
		}
	}

}
