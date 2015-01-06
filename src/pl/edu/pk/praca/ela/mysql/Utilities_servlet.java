package pl.edu.pk.praca.ela.mysql;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utilities_servlet {

	public static List<Map<String, String>> createMap(ResultSet rs) throws SQLException{
		
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        ResultSetMetaData meta = rs.getMetaData();
        rs.beforeFirst();
        while (rs.next()) {
        	
        	Map<String,String> map = new HashMap<String,String>();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                String key = meta.getColumnName(i);
                String value = rs.getString(key);
                System.out.println("Putting: "+ key + " => " + value);
                map.put(key, value);
            }
            list.add(map);
        }
        return list;
	}
}
