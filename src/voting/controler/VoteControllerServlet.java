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
import voting.dao.VoteDao;
import voting.pojo.Candidate;
import voting.pojo.CandidateDetails;
import voting.pojo.Election;
import voting.pojo.ResultCard;
import voting.pojo.Vote;

/**
 * Servlet implementation class VoteControllerServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/VoteControllerServlet" })
public class VoteControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VoteControllerServlet() {
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
			if (data.equals("options")) {
				ArrayList<Election> election = ElectionDao.getElectionForCondition(3);
				if (election.size() == 0) {
					request.setAttribute("message", "Sorry no election has been held right now.....!");
					rd = request.getRequestDispatcher("nothing.jsp");
					rd.forward(request, response);
				} else {
					request.setAttribute("elections", election);
					rd = request.getRequestDispatcher("votes.jsp");
					rd.forward(request, response);
				}
			} else if (data.equals("nominies")) {
				String electionId = request.getParameter("electionid");
				Vote vote = new Vote();
				vote.setElectionId(electionId);
				vote.setUserId(userid);
				String check = VoteDao.verifyVote(vote);
				if (check == null) {
					ArrayList<CandidateDetails> candidates = CandidateDao.getCandidateDetailsByElectionId(electionId);
					request.setAttribute("candidates", candidates);
					request.setAttribute("result", "yes");
				} else {
					CandidateDetails candidate = CandidateDao.getCandidateDetailsByCandidateId(check);
					request.setAttribute("candidate", candidate);
					request.setAttribute("result", "no");
				}
				rd = request.getRequestDispatcher("nominies.jsp");
				rd.forward(request, response);
			} else if (data.equals("checked")) {
				String electionId = request.getParameter("electionid");
				String candidateId = request.getParameter("candidateid");
				Vote vote = new Vote();
				vote.setElectionId(electionId);
				vote.setUserId(userid);
				vote.setCandidateId(candidateId);
				String check = VoteDao.verifyVote(vote);
				if (check == null) {
					if (VoteDao.addVote(vote)) {
						response.getWriter().print("success");
					} else {
						response.getWriter().print("error");
					}
				} else {
					CandidateDetails candidate = CandidateDao.getCandidateDetailsByCandidateId(check);
					request.setAttribute("candidate", candidate);
					request.setAttribute("result", "no");
					rd = request.getRequestDispatcher("nominies.jsp");
					rd.forward(request, response);
				}
			} else if (data.equals("resultoptions")) {
				ArrayList<Election> election = ElectionDao.getElectionForCondition(4);
				if (election.size() == 0) {
					request.setAttribute("message", "Sorry no result found.....!");
					rd = request.getRequestDispatcher("nothing.jsp");
					rd.forward(request, response);
				} else {
					request.setAttribute("elections", election);
					rd = request.getRequestDispatcher("result.jsp");
					rd.forward(request, response);
				}
			} else if (data.equals("result")) {
				String electionId = request.getParameter("electionid");
				ArrayList<ResultCard> results = VoteDao.getResult(electionId);
				int count = VoteDao.getCount(electionId);
				request.setAttribute("results", results);
				request.setAttribute("count", count);
				rd = request.getRequestDispatcher("resultcard.jsp");
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
