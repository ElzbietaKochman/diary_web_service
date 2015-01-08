package pl.edu.pk.praca.ela;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
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
	int success = 0;
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
				
				
				break;
			case "admin":
				break;
			}
			html += Utils.makeButton("Dodaj", "confirm_add_user()", "button");
			out.println(html);
			break;
		case "confirm_add_user":
			String[] data = request.getParameterValues("values[]");
			Map<String, String> form = new HashMap<String, String>();
			form.put("nazwa",data[0]);
			form.put("login",data[1]);
			int isLogin = query.getLoginDataId(data[1]);
			if(isLogin != 0){
				out.println(3); // login istnieje
				return;
			}
			form.put("typ",data[2]);
			String typ = form.get("typ");
			String nazwa = form.get("nazwa");
			String nazwisko="";
			try{
			nazwisko = nazwa.substring(0,nazwa.indexOf(" "));
			}
			catch(IndexOutOfBoundsException e){
				out.println(2); // zly format nazwy
				return;
			}
			String p_l_imie = nazwa.substring(nazwa.indexOf(" ")+1,nazwa.indexOf(" ")+2);
			String password = nazwisko+p_l_imie;
			form.put("password", password.toLowerCase());
			if(typ.equals("uczen")){
				form.put("nr_grupy",data[3]);
			}
			else if(typ.equals("nauczyciel")){
				form.put("email",data[3]);
				form.put("tel",data[4]);
			}
			else if(typ.equals("opiekun")){
				form.put("tel",data[3]);
			}
			
			success = query.dodajUzytkownika(typ, form);
			out.println(success);
			break;
		case "assign_user":
			String[] assign = request.getParameterValues("values[]");
			String id_opiekun = assign[0];
			List<String> uczniowie = new ArrayList<String>();
			for(int i=1 ; i < assign.length;i++){
				uczniowie.add(assign[i]);
			}
			success = query.opiekunUczen(id_opiekun, uczniowie);
			out.println(success);
			break;
		case "new_year":
			String[] new_year = request.getParameterValues("values[]");
			String pocz_roku = new_year[0];
			String kon_zim = new_year[1];
			String kon_roku = new_year[2];
			
			String p_roku = pocz_roku.substring(0,pocz_roku.indexOf("-"));
			String k_roku = kon_roku.substring(0,kon_roku.indexOf("-"));
			String rok_szk = p_roku+"-"+k_roku;
			
			success = query.dodajRokSzkolny(rok_szk, pocz_roku, kon_zim, kon_roku);
			out.println(success);
			break;
		case "make_teacher":
			String[] make_teacher = request.getParameterValues("values[]");
			int teacher_id = 0,id_grupy = 0;
			try{
				teacher_id = Integer.parseInt(make_teacher[0]);
				id_grupy = Integer.parseInt(make_teacher[1]);
			}
			catch(NumberFormatException e){
				out.println(2);
				return;
			}
			
			int success = query.przypiszNauczyciela(teacher_id, id_grupy);
			out.println(success);
			break;
		case "is_logged":
			boolean logged = true;
			try{
				request.getSession().getAttribute("user").equals("admin");
			}
			catch(NullPointerException e){
				logged = false;
			}
			out.println(logged);
			break;
		}
	}

}
