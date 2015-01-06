package pl.edu.pk.praca.ela;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.edu.pk.praca.ela.mysql.QueryMaker;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	List<Map<String, String>> getUsername;
	public static HttpSession session;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String login = request.getParameter("login");
		String password = request.getParameter("pass");
		Connection conn = DiaryDAO.DiaryDAO();
		QueryMaker query = QueryMaker.getInstance(conn);
		PrintWriter out = response.getWriter();
		response.setCharacterEncoding("UTF-8");
		getUsername = query.getUsername(login, password);

		if(getUsername.size()>0){
			if(getUsername.get(0).get("role").equals("admin")){
				session = request.getSession();
				session.setAttribute("idAdmina", getUsername.get(0).get("id"));
				session.setAttribute("user", "admin");
				out.println(1);
			}
			else{

				out.println(2);
			}
		}
		else {
			out.println(3);
		}
	}

}
