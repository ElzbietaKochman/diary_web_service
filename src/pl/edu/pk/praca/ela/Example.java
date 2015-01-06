package pl.edu.pk.praca.ela;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Example {
		private static Statement st;
		private static String query;
	public static void main(String[] args) {
		
		Connection diary = DiaryDAO.DiaryDAO();
		try {
			query = "select * from login_data";
			st = diary.createStatement();
			ResultSet res = st.executeQuery(query);
			while(res.next()){
				System.out.println(res.getString("login")+" "+res.getString("password"));
			}
			//zwolnienie zasobów
			st.close();
			diary.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
