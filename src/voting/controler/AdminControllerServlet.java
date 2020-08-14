package voting.controler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import voting.dao.UserDao;
import voting.pojo.UserDetails;

/**
 * Servlet implementation class AdminControllerServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AdminControllerServlet" })
public class AdminControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminControllerServlet() {
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
		HttpSession session = request.getSession();
		String userid = (String)session.getAttribute("userid");
		if(userid == null) {
			response.sendRedirect("accessdenied.html");
			return;
		}
		String data = request.getParameter("data");
		RequestDispatcher rd = null;
		if (data.equals("logout")) {
			if (session != null) {
				session.removeAttribute("userid");
				session.invalidate();
			}
		} else if (data.equals("users")) {
			try {
				ArrayList<UserDetails> users = UserDao.getUsers();
				if(users.size() != 0) {
					request.setAttribute("users", users);
					rd = request.getRequestDispatcher("users.jsp");
					rd.forward(request, response);
				} else {
					request.setAttribute("message", "Sorry! No user present in the record...");
					rd = request.getRequestDispatcher("nothing.jsp");
					rd.forward(request, response);
				}
				
			} catch (SQLException e) {
				request.setAttribute("exception", e);
				rd = request.getRequestDispatcher("showexception.jsp");
				e.printStackTrace();
				rd.forward(request, response);
			}
		}  else if (data.equals("search")) {
			try {
				String username = request.getParameter("username");
				String uid = request.getParameter("userid");
				ArrayList<UserDetails> users = UserDao.searchUsers(uid, username);
				if(users.size() != 0) {
					request.setAttribute("users", users);
					rd = request.getRequestDispatcher("users.jsp");
					rd.forward(request, response);
				} else {
					request.setAttribute("message", "Sorry! No user present in the record...");
					rd = request.getRequestDispatcher("nothing.jsp");
					rd.forward(request, response);
				}
				
			} catch (SQLException e) {
				request.setAttribute("exception", e);
				rd = request.getRequestDispatcher("showexception.jsp");
				e.printStackTrace();
				rd.forward(request, response);
			}
		}
	}

}
