package pl.edu.pk.praca.ela;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
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
		case "add_user_form":
			String value = request.getParameter("value");
			String html = "";
			switch(value){
			case "uczen":
				html += Utils.makeInputText("group_id", "Numer grupy", "");
				break;
			case "nauczyciel":
				html += Utils.makeInputText("teacher_mail", "Adres e-mail", "");
				html += Utils.makeInputText("phone", "Numer telefonu", "");
				break;
			case "opiekun":
				html += Utils.makeInputText("phone", "Numer telefonu", "");
				
				List<Map<String,String>> uczniowie = query.getWszyscyUczen();
				List<String[]> options = new ArrayList<String[]>();
				for(Map<String,String> uczen : uczniowie){
					String[] dane = new String[2];
					dane[0] = uczen.get("id_ucznia");
					dane[1] = uczen.get("nazwa");
					options.add(dane);
				}
				html += Utils.makeMultipleSelect("Podopieczni", "podopieczni", options);
				break;
			case "admin":
				break;
			}
			html += Utils.makeButton("Dodaj", "confirm_add_user", "button");
			out.println(html);
			break;
		}
	}

}
