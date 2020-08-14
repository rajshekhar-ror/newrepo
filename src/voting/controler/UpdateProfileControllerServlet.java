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
import java.util.Base64;
import java.util.Base64.Encoder;
import com.mysql.cj.jdbc.Blob;

import voting.dao.UserDao;

/**
 * Servlet implementation class UpdateProfileControllerServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UpdateProfileControllerServlet" })
public class UpdateProfileControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateProfileControllerServlet() {
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
			InputStream fileContent = null;
			for (FileItem item : multiparts) {
					fileContent = item.getInputStream();
			}
			if (UserDao.changePhoto(userid, fileContent)) {
				Blob blob = (Blob) UserDao.getPhoto(userid);
				byte[] imageBytes = blob.getBytes(1L, (int) blob.length());
				Encoder ec = Base64.getEncoder();
				String base64Image = ec.encodeToString(imageBytes);
				request.setAttribute("result", true);
				request.setAttribute("photo", base64Image);
			} else {
				request.setAttribute("result", false);
			}
			rd = request.getRequestDispatcher("updatephoto.jsp");
		} catch (Exception e) {
			request.setAttribute("exception", e);
			rd = request.getRequestDispatcher("showexception.jsp");
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			rd.forward(request, response);
		}
	}

}
