package pl.edu.pk.praca.ela;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.edu.pk.praca.ela.mysql.QueryMaker;



/**
 * Servlet implementation class Diary_connector
 */
@WebServlet("/Diary_connector")
public class Diary_connector extends HttpServlet {
	Connection conn;
	private static final long serialVersionUID = 1L;
	/**
	 * Default constructor. 
	 */
	public Diary_connector() {

	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		conn = DiaryDAO.DiaryDAO();

		QueryMaker query = QueryMaker.getInstance(conn);

		ObjectInputStream in = new ObjectInputStream(request.getInputStream());
		Map<String, String> obj = null;
		try {
			obj = (Map<String, String>) in.readObject();
		} catch (ClassNotFoundException e) {

		}

		ObjectOutputStream out = new ObjectOutputStream(response.getOutputStream());

		String action = obj.get("action");


		switch(action){
		case "getUsername":
			String user2 = obj.get("Arg1");
			String pass2 = obj.get("Arg2");
			out.writeObject(query.getUsername(user2, pass2));
			out.flush();
			break;
		case "getWychowawca":
			String user = obj.get("Arg1");
			String pass = obj.get("Arg2");
			out.writeObject(query.getWychowawca(user, pass));
			out.flush();
			break;
		case "getUczenOgloszenia":
			int id = Integer.parseInt(obj.get("Arg1"));
			System.out.println(id);
			out.writeObject(query.getUczenOgloszenia(id));
			out.flush();
			break;
		case "getNauczyciel":
			int idN = Integer.parseInt(obj.get("Arg1"));
			System.out.println(idN);
			out.writeObject(query.getNauczyciel(idN));
			out.flush();
			break;
		case "getUczen":
			int idO = Integer.parseInt(obj.get("Arg1"));
			System.out.println(idO);
			out.writeObject(query.getUczen(idO));
			out.flush();
			break;
		case "getGrupa":
			int idOg = Integer.parseInt(obj.get("Arg1"));
			out.writeObject(query.getGrupa(idOg));
			out.flush();
			break;
		case "getTegoroczni":
			String tegoroczni = obj.get("Arg1");
			out.writeObject(query.getTegoroczni(tegoroczni));
			out.flush();
			break;
		case "getPoziom":
			String poziomke = obj.get("Arg1");
			out.writeObject(query.getPoziom(poziomke));
			out.flush();
			break;
		case "getRelacja":
			int relacja = Integer.parseInt(obj.get("Arg1"));
			System.out.println(relacja);
			out.writeObject(query.getRelacja(relacja));
			out.flush();
			break;
		case "getUczenUwagi":
			int idu = Integer.parseInt(obj.get("Arg1"));
			String data2 = obj.get("Arg2");
			System.out.println(data2);
			out.writeObject(query.getUczenUwagi(idu,data2));
			out.flush();
			break;
		case "getUczenOceny":
			int idU = Integer.parseInt(obj.get("Arg1"));
			int id2 = Integer.parseInt(obj.get("Arg2"));
			String data20 = obj.get("Arg3");
			String data21 = obj.get("Arg4");
			out.writeObject(query.getUczenOceny(idU,id2,data20,data21));
			out.flush();
			break;
		case "ocenyCalyRok":
			int a = Integer.parseInt(obj.get("Arg1"));
			String a11 = obj.get("Arg2");
			out.writeObject(query.ocenyCalyRok(a,a11));
			out.flush();
			break;
		case "getUczenPrzedmioty":
			int id1 = Integer.parseInt(obj.get("Arg1"));
			System.out.println(id1);
			out.writeObject(query.getUczenPrzedmioty(id1));
			out.flush();
			break;
		case "getNieobecnosci":
			int i = Integer.parseInt(obj.get("Arg1"));
			String data = obj.get("Arg2");
			String status = obj.get("Arg3");
			System.out.println(i);
			out.writeObject(query.getNieobecnosci(i,data,status));
			out.flush();
			break;
		case "getNieobecnosciData":
			int i1 = Integer.parseInt(obj.get("Arg1"));
			String data3 = obj.get("Arg2");
			String data4 = obj.get("Arg3");
			System.out.println(i1);
			out.writeObject(query.getNieobecnosciData(i1,data3,data4));
			out.flush();
			break;
		case "getNieobecnosciNieusprawiedliwione":
			int li1 = Integer.parseInt(obj.get("Arg1"));
			String ldata3 = obj.get("Arg2");
			String ldata4 = obj.get("Arg3");
			out.writeObject(query.getNieobecnosciNieusprawiedliwione(li1,ldata3,ldata4));
			out.flush();
			break;
		case "getRokSzkolny":
			String data1 = obj.get("Arg1");
			out.writeObject(query.getRokSzkolny(data1));
			out.flush();
			break;
		case "getUczenId":
			String nazwaUcznia = obj.get("Arg1");
			out.writeObject(query.getUczenId(nazwaUcznia));
			out.flush();
			break;
		case "getRokSz":
			out.writeObject(query.getRokSz());
			out.flush();
			break;
		case "getRokId":
			String rokszkolny = obj.get("Arg1");
			out.writeObject(query.getRokId(rokszkolny));
			out.flush();
			break;
		case "getSemestr":
			String data10 = obj.get("Arg1");
			out.writeObject(query.getSemestr(data10));
			out.flush();
			break;
		case "getUczenArchiwumOgloszenia":
			int idA = Integer.parseInt(obj.get("Arg1"));
			String dataA = obj.get("Arg2");
			System.out.println(idA);
			out.writeObject(query.getUczenArchiwumOgloszenia(idA, dataA));
			out.flush();
			break;
		case "sredniaSzkolna":
			int idS = Integer.parseInt(obj.get("Arg1"));
			String st = obj.get("Arg2");
			out.writeObject(query.sredniaSzkolna(idS, st));
			out.flush();
			break;
		case "sredniaRoku":
			int idR = Integer.parseInt(obj.get("Arg1"));
			String stR = obj.get("Arg2");
			String stSr = obj.get("Arg3");
			out.writeObject(query.sredniaRoku(idR, stR,stSr));
			out.flush();
			break;
		case "ocenaKoncowa":
			int idR1 = Integer.parseInt(obj.get("Arg1"));
			String stR1 = obj.get("Arg2");
			out.writeObject(query.ocenaKoncowa(idR1, stR1));
			out.flush();
			break;
		case "zliczPliki":
			int iduczniaka = Integer.parseInt(obj.get("Arg1"));
			String roke = obj.get("Arg2");
			out.writeObject(query.zliczPliki(iduczniaka, roke));
			out.flush();
			break;
		case "getPlik":
			int iduczniak = Integer.parseInt(obj.get("Arg1"));
			String rokel = obj.get("Arg2");
			out.writeObject(query.getPlik(iduczniak, rokel));
			out.flush();
			break;
		case "ocenaKoncowaZ":
			int idR1a = Integer.parseInt(obj.get("Arg1"));
			String stR1a = obj.get("Arg2");
			int idzs = Integer.parseInt(obj.get("Arg3"));
			out.writeObject(query.ocenaKoncowaZ(idR1a, stR1a,idzs));
			out.flush();
			break;
		case "getPrzedmiot":
			int id_przedmiotu = Integer.parseInt(obj.get("Arg1"));
			out.writeObject(query.getPrzedmiot(id_przedmiotu));
			out.flush();
			break;
		case "getNauczycielPrzedmiot":
			int idNa = Integer.parseInt(obj.get("Arg1"));
			String rokSz = obj.get("Arg2");
			out.writeObject(query.getNauczycielPrzedmiot(idNa,rokSz));
			out.flush();
			break;
			
		case "getKlasaPoziom":
			String id_grupy = obj.get("Arg1");
			out.writeObject(query.getKlasaPoziom(id_grupy));
			out.flush();
			break;			
		case "getWycho":
			int id_wych = Integer.parseInt(obj.get("Arg1"));
			out.writeObject(query.getWycho(id_wych));
			out.flush();
			break;
		case "zlicz":
			int zlicze = Integer.parseInt(obj.get("Arg1"));
			out.writeObject(query.zlicz(zlicze));
			out.flush();
			break;
		case "getKlasaOceny":
			int id_grupy1 = Integer.parseInt(obj.get("Arg1"));
			int id_grupy2 = Integer.parseInt(obj.get("Arg2"));
			out.writeObject(query.getKlasaOceny(id_grupy1,id_grupy2));
			out.flush();
			break;
		case "dodajOcena":
			int a1 = Integer.parseInt(obj.get("Arg1"));
			int a2 = Integer.parseInt(obj.get("Arg2"));
			float a3 = Float.parseFloat(obj.get("Arg3"));
			String a4 = obj.get("Arg4");
			out.writeObject(query.dodajOcena(a1, a2, a3, a4));
			out.flush();
			break;
		case "dodajOcenaSemestralna":
			int aS1 = Integer.parseInt(obj.get("Arg1"));
			int aS2 = Integer.parseInt(obj.get("Arg2"));
			int aS3 = Integer.parseInt(obj.get("Arg3"));
			float aS4 = Float.parseFloat(obj.get("Arg4"));
			out.writeObject(query.dodajOcenaSemestralna(aS1, aS2, aS3, aS4));
			out.flush();
			break;
		case "dodajOcenaKoncowa":
			float aK4 = Float.parseFloat(obj.get("Arg1"));
			int aK2 = Integer.parseInt(obj.get("Arg2"));
			out.writeObject(query.dodajOcenaKoncowa(aK4,aK2));
			out.flush();
			break;
		case "dodajOgloszenie":
			int dodaj1 = Integer.parseInt(obj.get("Arg1"));
			int dodaj2 = Integer.parseInt(obj.get("Arg2"));
			String dodaj3 = obj.get("Arg3");
			String dodaj4 = obj.get("Arg4");
			out.writeObject(query.dodajOgloszenie(dodaj1, dodaj2, dodaj3,dodaj4));
			out.flush();
			break;
		case "dodajUwaga":
			int id_ucznia1 = Integer.parseInt(obj.get("Arg1"));
			int id_nauczyciela1 = Integer.parseInt(obj.get("Arg2"));
			String tresc1 = obj.get("Arg3");
			out.writeObject(query.dodajUwaga(id_ucznia1, id_nauczyciela1, tresc1));
			out.flush();
			break;
		case "dodajNieobecnosc":
			int dn1 = Integer.parseInt(obj.get("Arg1"));
			int dn2 = Integer.parseInt(obj.get("Arg2"));
			String dn3 = obj.get("Arg3");
			String dn4 = obj.get("Arg4");
			String dn5 = obj.get("Arg5");
			out.writeObject(query.dodajNieobecnosc(dn1, dn2, dn3, dn4, dn5));
			out.flush();
			break;
		case "usprawiedliw":
			int nieo = Integer.parseInt(obj.get("Arg1"));
			out.writeObject(query.usprawiedliw(nieo));
			out.flush();
			break;
		case "getNoweWiadomosci":
			int idOd = Integer.parseInt(obj.get("Arg1"));
			out.writeObject(query.getNoweWiadomosci(idOd));
			out.flush();
			break;
		case "getNowe":
			int odbiorca = Integer.parseInt(obj.get("Arg1"));
			int nadawca = Integer.parseInt(obj.get("Arg2"));
			String dataWyslania = obj.get("Arg3");
			System.out.println("***********"+odbiorca + " "+nadawca+" "+dataWyslania);
			out.writeObject(query.getNowe(odbiorca, nadawca, dataWyslania));
			out.flush();
			break;
		case "getUserN":
			int idUsera = Integer.parseInt(obj.get("Arg1"));
			out.writeObject(query.getUserN(idUsera));
			out.flush();
			break;
		case "getUserO":
			int idUsera1 = Integer.parseInt(obj.get("Arg1"));
			out.writeObject(query.getUserO(idUsera1));
			out.flush();
			break;
		case "getWiadomosci":
			int odbiorca1 = Integer.parseInt(obj.get("Arg1"));
			int nadawca1 = Integer.parseInt(obj.get("Arg2"));
			out.writeObject(query.getWiadomosci(odbiorca1, nadawca1));
			out.flush();
			break;
		case "getRozmowy":
			int odbiorca2 = Integer.parseInt(obj.get("Arg1"));
			out.writeObject(query.getRozmowy(odbiorca2));
			out.flush();
			break;
		case "getWszyscyNauczyciel":
			out.writeObject(query.getWszyscyNauczyciel());
			out.flush();
			break;
		case "getWszyscyOpiekun":
			out.writeObject(query.getWszyscyOpiekun());
			out.flush();
			break;
		case "wyslij":
			int na = Integer.parseInt(obj.get("Arg1"));
			int od = Integer.parseInt(obj.get("Arg2"));
			String no = obj.get("Arg3");
			out.writeObject(query.wyslij(na, od, no));
			out.flush();
			break;
		case "ustawOdebrane":
			String dataW  = obj.get("Arg1");
			int idNad = Integer.parseInt(obj.get("Arg2"));
			out.writeObject(query.ustawOdebrane(dataW, idNad));
			out.flush();
			break;
		case "uploadPhoto":
			int id_ucznia  = Integer.parseInt(obj.get("Arg1"));
			String opis = obj.get("Arg2");
			String nazwa_pliku = obj.get("Arg3");
			out.writeObject(query.uploadPhoto(id_ucznia, opis, nazwa_pliku));
			out.flush();
			break;
		}

		closeMySql(conn);

	}
	private void closeMySql(Connection conn){
		try {
			conn.close();
		} catch (SQLException e) {
			System.err.println("Problem z zamykaniem bazy danych");
		}
	}


}
