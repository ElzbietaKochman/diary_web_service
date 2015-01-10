package pl.edu.pk.praca.ela;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import pl.edu.pk.praca.ela.mysql.QueryMaker;

public class Utils {
	
	static QueryMaker query = QueryMaker.getInstance(DiaryDAO.DiaryDAO());
	
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
	
	public static String makeInputHidden(String id, String value){
		return "<input type='hidden' id='"+id+"' name='"+id+"' value='"+value+"'></input>";
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
	
	public static List<String[]> uczenOptions(){
		List<Map<String,String>> uczniowie = query.getWszyscyNieprzypisaniUczen();
		List<String[]> options = new ArrayList<String[]>();
		for(Map<String,String> uczen : uczniowie){
			String[] dane = new String[2];
			dane[0] = uczen.get("id_ucznia");
			dane[1] = uczen.get("nazwa");
			options.add(dane);
		}
		return options;
		//html += Utils.makeMultipleSelect("Podopieczni", "podopieczni", options);
	}
	
	public static List<String[]> opiekunOptions(){
		List<Map<String,String>> uczniowie = query.getWszyscyOpiekun();
		List<String[]> options = new ArrayList<String[]>();
		for(Map<String,String> uczen : uczniowie){
			String[] dane = new String[2];
			dane[0] = uczen.get("id_opiekuna");
			dane[1] = uczen.get("nazwa");
			options.add(dane);
		}
		return options;
	}
	
	public static List<String[]> nauczycielOptions(){
		List<Map<String,String>> uczniowie = query.getWszyscyNauczyciel();
		List<String[]> options = new ArrayList<String[]>();
		for(Map<String,String> uczen : uczniowie){
			String[] dane = new String[2];
			dane[0] = uczen.get("id_nauczyciela");
			dane[1] = uczen.get("nazwa");
			options.add(dane);
		}
		return options;
	}
	
	public static List<String[]> uzytkownikOptions(){
		List<Map<String,String>> uczniowie = query.getWszyscyUzytkownik();
		List<String[]> options = new ArrayList<String[]>();
		for(Map<String,String> uczen : uczniowie){
			String[] dane = new String[2];
			dane[0] = uczen.get("id_user");
			dane[1] = uczen.get("nazwa");
			options.add(dane);
		}
		return options;
	}
	
	public static String createInputsToEdit(Map<String,String> fields){
		String html = "";
		
		for(Entry<String, String> entry : fields.entrySet()){
			if(!entry.getKey().equals("id_user") && !entry.getKey().equals("id_nauczyciela") && !entry.getKey().equals("id_opiekuna") && !entry.getKey().equals("id_ucznia") && !entry.getKey().equals("id_admin")){
				String label = entry.getKey().substring(0, 1).toUpperCase()+entry.getKey().substring(1);
				html += makeInputText(entry.getKey(), label, entry.getValue());
			}
		}
		
		return html;
	}
}
