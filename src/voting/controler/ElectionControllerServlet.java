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

import voting.dao.ElectionDao;
import voting.pojo.Election;

/**
 * Servlet implementation class ElectionControllerServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ElectionControllerServlet" })
public class ElectionControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ElectionControllerServlet() {
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
		try {
			if (data.equals("electionform")) {
				ArrayList<Election> elections = ElectionDao.getElection();
				request.setAttribute("elections", elections);
				rd = request.getRequestDispatcher("election.jsp");
				rd.forward(request, response);
			} else if (data.equals("add")) {
				String election = request.getParameter("election");
				if (ElectionDao.checkDup(election)) {
					response.getWriter().print("duplicate");
				} else {
					String id = ElectionDao.getElectionId();
					Election e = new Election();
					e.setElectionId(id);
					e.setElectionName(election);
					e.setStatus(0);
					if (ElectionDao.addElection(e)) {
						response.getWriter().print("success");
					} else {
						response.getWriter().print("error");
					}
				}
			} else if (data.equals("search")) {
				String election = request.getParameter("election");
				ArrayList<Election> elections = ElectionDao.searchElection(election);
				System.out.println(elections.size());
				if (elections.size() == 0) {
					request.setAttribute("message", "No Election Name Found of Given Data...");
					rd = request.getRequestDispatcher("nothing.jsp");
					rd.forward(request, response);
				} else {
					request.setAttribute("elections", elections);
					rd = request.getRequestDispatcher("election.jsp");
					rd.forward(request, response);
				}
			} else if (data.equals("delete")) {
				String election = request.getParameter("election");
				if (ElectionDao.deleteElection(election)) {
					response.getWriter().print("success");
				} else {
					response.getWriter().print("error");
				}
			} else if (data.equals("startnomination")) {
				String election = request.getParameter("election");
				if (ElectionDao.updateElection(election, 1)) {
					response.getWriter().print("success");
				} else {
					response.getWriter().print("error");
				}
			} else if (data.equals("stopnomination")) {
				String election = request.getParameter("election");
				if (ElectionDao.updateElection(election, 2)) {
					response.getWriter().print("success");
				} else {
					response.getWriter().print("error");
				}
			} else if (data.equals("startelection")) {
				String election = request.getParameter("election");
				if (ElectionDao.updateElection(election, 3)) {
					response.getWriter().print("success");
				} else {
					response.getWriter().print("error");
				}
			} else if (data.equals("stopelection")) {
				String election = request.getParameter("election");
				if (ElectionDao.updateElection(election, 4)) {
					response.getWriter().print("success");
				} else {
					response.getWriter().print("error");
				}
			}
		} catch (SQLException e) {
			request.setAttribute("exception", e);
			rd = request.getRequestDispatcher("showexception.jsp");
			e.printStackTrace();
			System.out.println(e.getMessage());
			rd.forward(request, response);
		}
	}

}
