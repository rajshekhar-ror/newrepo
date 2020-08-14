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

import voting.dao.CandidateDao;
import voting.dao.ElectionDao;
import voting.pojo.Candidate;
import voting.pojo.CandidateDetails;
import voting.pojo.Election;

/**
 * Servlet implementation class CandidateControllerServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/CandidateControllerServlet" })
public class CandidateControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CandidateControllerServlet() {
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
		String userId = (String) request.getSession().getAttribute("userid");
		RequestDispatcher rd = null;
		try {
			if (data.equals("candidateform")) {
				ArrayList<Election> election = ElectionDao.getElectionForCondition(1);
				ArrayList<Candidate> candidates = CandidateDao.getCandidate(userId);
				for (Candidate candidate : candidates) {
					candidate.setElectionId(ElectionDao.getElectionName(candidate.getElectionId()));
					candidate.setSymbol("data:image/jpg;base64," + candidate.getSymbol());
				}
				request.setAttribute("elections", election);
				request.setAttribute("candidates", candidates);
				rd = request.getRequestDispatcher("candidate.jsp");
				rd.forward(request, response);
			} else if (data.equals("candidates")) {
				ArrayList<CandidateDetails> candidates = CandidateDao.getCandidateDetails();
				if (candidates.size() != 0) {
					for (CandidateDetails candidate : candidates) {
						candidate.setElectionId(ElectionDao.getElectionName(candidate.getElectionId()));
					}
					request.setAttribute("candidates", candidates);
					rd = request.getRequestDispatcher("admincandidate.jsp");
					rd.forward(request, response);
				} else {
					request.setAttribute("message", "No candidates Present in the records");
					rd = request.getRequestDispatcher("nothing.jsp");
					rd.forward(request, response);
				}
			} else if (data.equals("search")) {
				String username = request.getParameter("username");
				String uid = request.getParameter("userid");
				ArrayList<CandidateDetails> candidates = CandidateDao.searchCandidateDetailsById(uid, username);
				if (candidates.size() != 0) {
					for (CandidateDetails candidate : candidates) {
						candidate.setElectionId(ElectionDao.getElectionName(candidate.getElectionId()));
					}
					request.setAttribute("candidates", candidates);
					rd = request.getRequestDispatcher("admincandidate.jsp");
					rd.forward(request, response);
				} else {
					request.setAttribute("message", "No candidates Present in the records");
					rd = request.getRequestDispatcher("nothing.jsp");
					rd.forward(request, response);
				}
			} else if (data.equals("delete")) {
				String candidateId = request.getParameter("candidateid");
				boolean result = CandidateDao.deleteCandidate(candidateId);
				if (result) {
					response.getWriter().print("success");
				} else {
					response.getWriter().print("error");
				}
			} else if (data.equals("enable")) {
				String candidateId = request.getParameter("candidateid");
				boolean result = CandidateDao.updateCandidate(candidateId, "y");
				if (result) {
					response.getWriter().print("success");
				} else {
					response.getWriter().print("error");
				}
			} else if (data.equals("disable")) {
				String candidateId = request.getParameter("candidateid");
				boolean result = CandidateDao.updateCandidate(candidateId, "n");
				if (result) {
					response.getWriter().print("success");
				} else {
					response.getWriter().print("error");
				}
			}
		} catch (SQLException e) {
			request.setAttribute("exception", e);
			rd = request.getRequestDispatcher("showexception.jsp");
			e.printStackTrace();
			rd.forward(request, response);
		}
	}

}
