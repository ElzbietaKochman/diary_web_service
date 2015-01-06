package pl.edu.pk.praca.ela;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DiaryDAO {
	private final static String DBURL = "jdbc:mysql://localhost:3306/diary_db";
	private final static String DBUSER = "root";
	private final static String DBpass = "";
	private final static String DBDriver = "com.mysql.jdbc.Driver";
	
	private static Connection conn= null;
	
	public static Connection DiaryDAO(){
			try {
				Class.forName(DBDriver).newInstance();
				conn = DriverManager.getConnection(DBURL, DBUSER, DBpass);
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | SQLException e) {
				System.err.println("error");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return conn;
	}
}
