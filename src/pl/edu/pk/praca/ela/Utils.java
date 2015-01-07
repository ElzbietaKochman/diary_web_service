package pl.edu.pk.praca.ela;

import java.util.List;

public class Utils {
	public static String makeSelect(String label, String id, List<String[]> options){
		String select = "<div class='inputs'><label class='l_input'>"+label+"</label><select id='"+id+"' name='"+id+"' required='required'>";
		for(String[] elems : options){
			select += "<option value='"+elems[0]+"'>"+elems[1]+"</option>";
		}
		select += "</select></div>";
		return select;
	}
	
	public static String makeMultipleSelect(String label, String id, List<String[]> options){
		String select = "<div class='inputs'><label class='l_input'>"+label+"</label><select id='"+id+"' name='"+id+"' required='required' multiple>";
		for(String[] elems : options){
			select += "<option value='"+elems[0]+"'>"+elems[1]+"</option>";
		}
		select += "</select></div>";
		return select;
	}
	public static String makeInputText(String id, String label, String value){
		return "<div class='inputs'><label class='l_input'>"+label+"</label><input type='text' id='"+id+"' name='"+id+"' value='"+value+"' size='30'  required='required'></input></div>";
	}

	public static String makeInputTextReadOnly(String id, String label, String value){
		return "<div class='inputs'><label class='l_input'>"+label+"</label><input type='text' id='"+id+"' name='"+id+"' value='"+value+"' size='30' readonly='readonly' ></input></div>";
	}
	
	public static String makeRadio(String name, String value, String label){
		return label+":<input type='radio' name='"+name+"' value='"+value+"'></input>";
	}
	
	public static String makeButton(String value,String onclick, String clas){
		return "<button class='button "+clas+"' type='button' onclick='"+onclick+"'>"+value+"</button>";
	}
	
	public static String createTable(List<List<String>> lista, List<String> headers){
		String html = "<div class='table_wrap'><table class='result_table'>";
		html += "<thead><tr>";
		for(String h : headers)
			html += "<th>"+h+"</th>";
		html += "</tr></thead>";
		
		int line = 1;
		String t_class = "odd";
		for(List<String> s : lista){
			if(line % 2 == 0){
				t_class = "even";
			}
			else {
				t_class = "odd";
			}
			line++;
			html += "<tr class='"+t_class+"'>";
			for(String s1: s){
				html += "<td>"+s1+"</td>";
			}
			html += "</tr>";
		}
		html += "</table></div>";
		return html;
	}
}
