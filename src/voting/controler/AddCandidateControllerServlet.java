package voting.controler;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import voting.pojo.AddCandidate;

/**
 * Servlet implementation class AddCandidateControllerServlet
 */
@WebServlet("/AddCandidateControllerServlet")
public class AddCandidateControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCandidateControllerServlet() {
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
		RequestDispatcher rd = null;
		try {
			DiskFileItemFactory dif = new DiskFileItemFactory();
			ServletRequestContext srq = new ServletRequestContext(request);
			ServletFileUpload sfu = new ServletFileUpload(dif);
			List<FileItem> multiparts = sfu.parseRequest(srq);
			String electionId = "";
			InputStream fileContent = null;
			for (FileItem item : multiparts) {
				if (item.isFormField()) {
					electionId = item.getString();
				} else {
					fileContent = item.getInputStream();
				}
			}
			String userId = (String) session.getAttribute("userid");
			boolean flag = voting.dao.CandidateDao.checkDup(userId, electionId);
			if (!flag) {
				AddCandidate candidate = new AddCandidate();
				candidate.setCandidateId(voting.dao.CandidateDao.getCandidateId());
				candidate.setUserId(userId);
				candidate.setElectionId(electionId);
				candidate.setSymbol(fileContent);
				candidate.setActive("y");
				boolean result = voting.dao.CandidateDao.addCandidate(candidate);
				if (result) {
					response.getWriter().print("success");
				} else {
					response.getWriter().print("error");
				}
			} else {
				response.getWriter().print("duplicate");
			}
		} catch (Exception e) {
			request.setAttribute("exception", e);
			rd = request.getRequestDispatcher("showexception.jsp");
			rd.forward(request, response);
			e.printStackTrace();
			// TODO: handle exception
		}
	}

}
