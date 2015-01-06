package pl.edu.pk.praca.ela;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.edu.pk.praca.ela.mysql.QueryMaker;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static HttpSession session;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DiaryDAO.DiaryDAO();
		QueryMaker query = QueryMaker.getInstance(conn);
		PrintWriter out = response.getWriter();
		response.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		switch(action){
		
		}
	}

}
