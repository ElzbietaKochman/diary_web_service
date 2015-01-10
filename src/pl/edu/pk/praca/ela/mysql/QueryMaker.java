package pl.edu.pk.praca.ela.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class QueryMaker {

	@SuppressWarnings("unused")
	private static String query = "";
	private static QueryMaker instance = null;
	private static Connection connection;
	String nazwaUcznia;
	int wynik;

	ResultSet res= null;
	List<Map<String, String>> result = null;

	private QueryMaker() {}
	public static QueryMaker getInstance(Connection conn){
		if(instance == null){
			instance = new QueryMaker();
		}
		connection = conn;
		return instance;
	}
	// wysylanie nowej wiadomosci
	
	public int wyslij(int nadawca, int odbiorca, String nowa){

		try {
			//nowa = nowa.replace("+", " ");
			System.out.println(nadawca + " "+odbiorca+" "+nowa);
			wynik = connection.createStatement().executeUpdate("INSERT INTO `diary_db`.`komunikator` (`id_wiadomosci`, `id_nadawcy`, `id_odbiorcy`, `odebrano`, `data`, `godzina`, `tresc`) VALUES (NULL, '"+nadawca+"', '"+odbiorca+"', '0', curDate(), curTime(), '"+nowa+"')");

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		
		return wynik;
	}
	
	//pobieranie wszystkich wiadomosci
	
	public List<Map<String, String>> getWiadomosci(int id_odbiorcy, int nadawcy){

		try {
			res = connection.createStatement().executeQuery("select * from komunikator where id_odbiorcy="+id_odbiorcy+" and id_nadawcy = "+nadawcy+" or  id_odbiorcy="+nadawcy+" and  id_nadawcy = "+id_odbiorcy+" order by data desc, godzina desc");

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}

		return result;
	}
	
	// pobieranie id po idUser
	
	public List<Map<String, String>> getUserN(int id_user){

		try {
			res = connection.createStatement().executeQuery("select * from  nauczyciel where id_user = "+id_user);

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}
		return result;
	}
	public List<Map<String, String>> getUserO(int id_user){

		try {
			res = connection.createStatement().executeQuery("select * from  opiekun where id_user = "+id_user);

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}
		return result;
	}
	
		//pobieranie rozmow
		
		public List<Map<String, String>> getRozmowy(int id_odbiorcy){

			try {
				res = connection.createStatement().executeQuery("select * from komunikator where id_odbiorcy="+id_odbiorcy+" or id_nadawcy ="+id_odbiorcy+" order by data desc, godzina desc");

			} catch (SQLException e) {
				System.err.println("Zle zapytanie");
			}
			try {

				result = Utilities_servlet.createMap(res);
			} catch (SQLException e) {
			}

		

		return result;
	}
	
	//pobieranie nowych wiadomosci konkretnie od danego nadawcy i w danym dniu
	public List<Map<String, String>> getNowe(int id_odbiorcy, int nadawcy, String data){

		try {
			System.out.println("query "+id_odbiorcy + " "+nadawcy+" "+data);
			res = connection.createStatement().executeQuery("select * from komunikator where id_odbiorcy="+id_odbiorcy+" and id_nadawcy = "+nadawcy+" and data = '"+data+"' and odebrano = 0 order by data desc, godzina asc");

		} catch (SQLException e) {
			System.err.println("Zle zapytanie getNowe");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}


		return result;
	}
	
	
	// ustawienie statusu odebranej wiadomosci
	public int ustawOdebrane(String data, int nadawca){

		try {
			//data = data.replace("+", "-");
			wynik = connection.createStatement().executeUpdate("UPDATE `diary_db`.`komunikator` SET `odebrano` = '1' WHERE `komunikator`.`data` = '"+data+"' and id_nadawcy ="+nadawca);
System.out.println(wynik);
		} catch (SQLException e) {
			System.err.println("Zle zapytanie ustawOdebrane");
		}
		
		return wynik;
	}
	
	public int opiekunUczen(String id_opiekun, List<String> uczniowie){
		wynik = 0;
		try {
			//data = data.replace("+", "-");
			for(String id_uczen : uczniowie)
				wynik += connection.createStatement().executeUpdate("INSERT INTO `diary_db`.`uczen_opiekun` VALUES (NULL, '"+id_uczen+"', '"+id_opiekun+"')");

		} catch (SQLException e) {
			System.err.println("Zle zapytanie ustawOdebrane");
		}
		int res = 0;
		if(wynik == uczniowie.size())
			res = 1;
		return res;
	}
	
	public int dodajRokSzkolny(String rok_szk, String pocz_roku, String kon_zim, String kon_roku){
		wynik = 0;
		try {
			//data = data.replace("+", "-");
			wynik = connection.createStatement().executeUpdate("INSERT INTO `diary_db`.`rok_szkolny` VALUES (NULL, '"+rok_szk+"', '"+pocz_roku+"', '"+kon_zim+"', '"+kon_roku+"')");

		} catch (SQLException e) {
			System.err.println("Zle zapytanie ustawOdebrane");
		}
		
		return wynik;
	}
	
	//pobieranie nowych wiadomo�ci opiekuna
	
	public List<Map<String, String>> getNoweWiadomosci(int id_odbiorcy){

		try {
			res = connection.createStatement().executeQuery("select * from komunikator where id_odbiorcy="+id_odbiorcy+" and odebrano = 0 order by data desc, godzina desc");

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}


		return result;
	}
	
		
	public int usprawiedliw(int id_nieobecnosci){

		try {
			wynik = connection.createStatement().executeUpdate("UPDATE `diary_db`.`nieobecnosci` SET `usprawiedliwione` = 'tak' WHERE `nieobecnosci`.`id_nieobecnosci` = "+id_nieobecnosci);
System.out.println(wynik);
		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		
		return wynik;
	}

	public int dodajOcena(int id_przedmiotu, int id_ucznia, float wartosc, String uwaga_oceny){

		try {
			wynik = connection.createStatement().executeUpdate("INSERT INTO `diary_db`.`ocena` (`id_oceny`, `id_przedmiotu`, `id_ucznia`, `wartosc`, `data`, `uwaga_oceny`) VALUES (NULL, '"+id_przedmiotu+"', '"+id_ucznia+"', '"+wartosc+"',curdate() , '"+uwaga_oceny+"')");
System.out.println(wynik);
		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		
		return wynik;
	}
	
	public int dodajUwaga(int id_ucznia, int id_nauczyciela, String tresc){

		try {
			wynik = connection.createStatement().executeUpdate("INSERT INTO `diary_db`.`uwaga` (`id_uwagi`, `id_ucznia`, `id_nauczyciela`, `data`, `tresc`) VALUES (NULL, '"+id_ucznia+"', '"+id_nauczyciela+"', curdate(), '"+tresc+"')");
System.out.println(wynik);
		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		
		return wynik;
	}
	
	public int dodajNieobecnosc(int id_ucznia, int id_przedmiotu, String pocz, String kon, String status){

		try {
			String godzPocz = pocz.replace("%3A", ":");
			String godzKon = kon.replace("%3A", ":");
			
			wynik = connection.createStatement().executeUpdate("INSERT INTO `diary_db`.`nieobecnosci` (`id_nieobecnosci`, `id_ucznia`, `id_przedmiotu`, `godz_pocz`, `godz_kon`, `data`, `usprawiedliwione`) VALUES (NULL, '"+id_ucznia+"', '"+id_przedmiotu+"', '"+godzPocz+"', '"+godzKon+"', curdate(), '"+status+"')");
System.out.println(wynik);
		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		
		return wynik;
	}
	
	public int dodajOcenaSemestralna(int id_przedmiotu, int id_ucznia, int id_rok, float wartosc){

		try {
			wynik = connection.createStatement().executeUpdate("INSERT INTO `diary_db`.`ocena_koncowa` (`id_ocenyK`, `id_ucznia`,`id_przedmiotu`, `id_rok`, `semestralna`, `koncowa`) VALUES (NULL, '"+id_ucznia+"', '"+id_przedmiotu+"', '"+id_rok+"', '"+wartosc+"', null)");
System.out.println(wynik);
		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		
		return wynik;
	}
	
	public int dodajOgloszenie(int id_ucznia, int id_nauczyciela, String temat, String tresc){
		try{
			wynik = connection.createStatement().executeUpdate("INSERT INTO `diary_db`.`ogloszenia` (`id_ogloszenia`, `id_ucznia`, `id_nauczyciela`, `data`, `godz_wyslania`, `temat`, `tresc`) VALUES (NULL, '"+id_ucznia+"', '"+id_nauczyciela+"', curdate(), curtime(), '"+temat+"', '"+tresc+"')");
		}
		catch(SQLException e){
			System.err.println("Zle zapytanie");
		}
		return wynik;
	}
	
	public int dodajOcenaKoncowa(float wartosc, int id_ocenyK){

		try {
			wynik = connection.createStatement().executeUpdate("UPDATE `diary_db`.`ocena_koncowa` SET `koncowa` = '"+wartosc+"' WHERE `ocena_koncowa`.`id_ocenyK` = "+id_ocenyK+"");
			System.out.println(wynik);
		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		
		return wynik;
	}
	
	public int dodajUzytkownika(String typ, Map<String,String> data){
		String zapytanie = "";
		zapytanie += "INSERT INTO `diary_db`.`login_data` (`id_user`, `login`, `password`, `role`) VALUES (NULL, '"+data.get("login")+"', '"+data.get("password")+"', '"+typ+"')";
		try {
			wynik = connection.createStatement().executeUpdate(zapytanie);
			
			if(wynik > 0){
				zapytanie = "INSERT INTO `diary_db`.`"+typ+"` VALUES(NULL,";
				int login_id = getLoginDataId(data.get("login"));
				if(typ.equals("uczen")){
					zapytanie += login_id + "," + data.get("nr_grupy")+ ",'" + data.get("nazwa") + "'";
				}
				else if(typ.equals("nauczyciel")){
					zapytanie += login_id +",'" + data.get("nazwa") + "','"+data.get("email")+"'," + data.get("tel");
				}
				else if(typ.equals("opiekun")){
					zapytanie += login_id +",'" + data.get("nazwa") + "'," + data.get("tel");
				}
				else {
					zapytanie += login_id +",'" + data.get("nazwa") + "'";
				}
				zapytanie += ")";
				System.out.println(zapytanie);
				wynik = connection.createStatement().executeUpdate(zapytanie);
			}
		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		
		return wynik;
	}
	
	public int uploadPhoto(int id_uczen, String opis, String nazwa){
		try {
			wynik = connection.createStatement().executeUpdate("INSERT INTO `diary_db`.`plik` (`id_pliku`, `id_ucznia`, `nazwa`, `etykieta`, `data`) VALUES (NULL, "+id_uczen+", '"+nazwa+"', '"+opis+"', curdate())");
			System.out.println(wynik);
		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		
		return wynik;
	}
	
	public List<Map<String, String>> getPrzedmiot(int id_przedmiotu){

		try {
			res = connection.createStatement().executeQuery("select * from przedmiot where id_przedmiotu ="+id_przedmiotu);

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}
		return result;
	}
	
	public List<Map<String, String>> getWycho(int id_nauczyciela){

		try {
			res = connection.createStatement().executeQuery("select * from wychowawca where id_nauczyciela ="+id_nauczyciela);

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}
		return result;
	}
	
	
	public List<Map<String, String>> getKlasaPoziom(String rok_szkolny){

		try {
			res = connection.createStatement().executeQuery("select * from poziom_klasa where rok_szkolny='"+rok_szkolny+"'");

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}
		return result;
	}
	
	public List<Map<String, String>> getKlasaOceny(int id_przedmiotu, int id_grupy){

		try {
			res = connection.createStatement().executeQuery("select * from uczen natural join ocena where id_przedmiotu="+id_przedmiotu+" and id_grupy="+id_grupy);

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}
		return result;
	}
	
	public List<Map<String, String>> getNauczycielPrzedmiot(int id_nauczyciela, String rok_szkolny){

		try {
			res = connection.createStatement().executeQuery("select * from przedmiot natural join poziom_klasa where id_nauczyciela ="+id_nauczyciela+" and rok_szkolny='"+rok_szkolny+"'");;

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}
		return result;
	}
	
	public List<Map<String, String>> ocenaKoncowa(int id_ucznia, String rok){

		try {
			res = connection.createStatement().executeQuery("select * from ocena_koncowa where id_ucznia ="+id_ucznia+" and id_rok=(select id_rok from rok_szkolny where rok_szkolny='"+rok+"')");

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}
		return result;
	}
	public List<Map<String, String>> ocenaKoncowaZ(int id_ucznia, String rok, int id_przedmiotu){

		try {
			res = connection.createStatement().executeQuery("select * from ocena_koncowa where  id_przedmiotu ="+id_przedmiotu+" and id_ucznia ="+id_ucznia+" and id_rok=(select id_rok from rok_szkolny where rok_szkolny='"+rok+"')");

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}
		return result;
	}
	
	// srednia ze wszystkich ocen dla danego semestru
	public List<Map<String, String>> sredniaSzkolna(int id_ucznia, String status){

		try {
			res = connection.createStatement().executeQuery("select avg("+status+") as srednia from ocena_koncowa where id_ucznia ="+id_ucznia);

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}
		return result;
	}
	
	// jak srednia szkolna, tylko dla jednego, wybranego roku
	public List<Map<String, String>> sredniaRoku(int id_ucznia, String rok, String status){

		try {
			res = connection.createStatement().executeQuery("select avg("+status+") as srednia from ocena_koncowa where id_ucznia ="+id_ucznia+" and id_rok=(select id_rok from rok_szkolny where rok_szkolny='"+rok+"')");

		} catch (SQLException e) {
			System.out.println(id_ucznia + " "+rok+" "+status);
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}
		return result;
	}
	
	public List<Map<String, String>> getRelacja(int id_opiekuna){

		try {
			res = connection.createStatement().executeQuery("select *  from uczen_opiekun where id_opiekuna = "+id_opiekuna);

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}
		return result;
	}
	
	
	public List<Map<String, String>> getUczen(int id_ucznia){

		try {
			res = connection.createStatement().executeQuery("select * from uczen where id_ucznia = "+id_ucznia);

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}
		return result;
	}
	
	public List<Map<String, String>> getTegoroczni(String rok_szkolny){

		try {
			res = connection.createStatement().executeQuery("select * from uczen natural join poziom_klasa where rok_szkolny = '"+rok_szkolny+"'");

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}
		return result;
	}
	public List<Map<String, String>> getGrupa(int id_grupy){

		try {
			res = connection.createStatement().executeQuery("select * from uczen where id_grupy = "+id_grupy);

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}
		return result;
	}

	public List<Map<String, String>> getPoziom(String rokSzkolny){

		try {
			res = connection.createStatement().executeQuery("select * from poziom_klasa where rok_szkolny = '"+rokSzkolny+"'");

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}
		return result;
	}
	
	public List<Map<String, String>> zlicz(int id_ucznia){

		try {
			res = connection.createStatement().executeQuery("select count(usprawiedliwione) as liczba from nieobecnosci where usprawiedliwione = 'nie' and id_ucznia = "+id_ucznia);

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}
		return result;
	}
	
	public List<Map<String, String>> getNieobecnosciData(int id_ucznia, String rok_szkolny, String semestr){

		try {
			if(semestr.equals("zimowy")){
			res = connection.createStatement().executeQuery("select distinct data from nieobecnosci where id_ucznia="+id_ucznia+" and data between (select pocz_roku from rok_szkolny where rok_szkolny='"+rok_szkolny+"') and (select k_zimowego from rok_szkolny where rok_szkolny='"+rok_szkolny+"')");
			}
			else {
				res = connection.createStatement().executeQuery("select distinct data from nieobecnosci where id_ucznia="+id_ucznia+" and data between date_add((select k_zimowego from rok_szkolny where rok_szkolny='"+rok_szkolny+"'), interval 1 day) and (select k_roku from rok_szkolny where rok_szkolny='"+rok_szkolny+"')");
				
			}
		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}
		return result;
	}
	
	public List<Map<String, String>> getNieobecnosciNieusprawiedliwione(int id_ucznia, String rok_szkolny, String semestr){

		try {
			if(semestr.equals("zimowy")){
			res = connection.createStatement().executeQuery("select distinct data from nieobecnosci where usprawiedliwione='nie' and id_ucznia="+id_ucznia+" and data between (select pocz_roku from rok_szkolny where rok_szkolny='"+rok_szkolny+"') and (select k_zimowego from rok_szkolny where rok_szkolny='"+rok_szkolny+"')");
			}
			else {
				res = connection.createStatement().executeQuery("select distinct data from nieobecnosci where usprawiedliwione='nie' and id_ucznia="+id_ucznia+" and data between date_add((select k_zimowego from rok_szkolny where rok_szkolny='"+rok_szkolny+"'), interval 1 day) and (select k_roku from rok_szkolny where rok_szkolny='"+rok_szkolny+"')");
				
			}
		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}
		return result;
	}
	
	public List<Map<String, String>> getNieobecnosci(int id_ucznia, String data, String status){

		try {
			res = connection.createStatement().executeQuery("select * from nieobecnosci where id_ucznia="+id_ucznia+" and data='"+data+"' and usprawiedliwione='"+status+"' order by data");

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}
		return result;
	}
	
	public List<Map<String, String>> zliczPliki(int id_ucznia, String rokSzkolny){

		try {
			res = connection.createStatement().executeQuery("select count(*) as liczba from plik where id_ucznia="+id_ucznia+" and data between (select pocz_roku from rok_szkolny where rok_szkolny='"+rokSzkolny+"') and (select k_roku from rok_szkolny where rok_szkolny='"+rokSzkolny+"')");

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}
		return result;
	}
	public List<Map<String, String>> getPlik(int id_ucznia, String rokSzkolny){

		try {
			res = connection.createStatement().executeQuery("select * from plik where id_ucznia="+id_ucznia+" and data between (select pocz_roku from rok_szkolny where rok_szkolny='"+rokSzkolny+"') and (select k_roku from rok_szkolny where rok_szkolny='"+rokSzkolny+"')");

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}
		return result;
	}
	
	public List<Map<String, String>> getUczenId(String nazwa){

		try {
			
			nazwaUcznia = nazwa.replace("+", " ");
			res = connection.createStatement().executeQuery("select * from uczen where nazwa='"+nazwaUcznia+"'");

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}
		return result;
	}
	
	public List<Map<String, String>> getRokSz(){

		try {
			res = connection.createStatement().executeQuery("select * from rok_szkolny");

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}
		return result;
	}
	
	public List<Map<String, String>> getSemestr(String data){

		try {
			res = connection.createStatement().executeQuery("call getSemestrType('"+data+"')");

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}
		return result;
	}
	
	public List<Map<String, String>> getRokSzkolny(String data){

		try {
			res = connection.createStatement().executeQuery("select * from rok_szkolny where '"+data+"' between pocz_roku and k_roku");

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}
		return result;
	}
	public List<Map<String, String>> getRokId(String rok_szkolny){

		try {
			res = connection.createStatement().executeQuery("select id_rok from rok_szkolny where rok_szkolny='"+rok_szkolny+"'");

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}
		return result;
	}
	
	public List<Map<String, String>> getUczenUwagi(int id_ucznia, String rok_szkolny){

		try {
			res = connection.createStatement().executeQuery("select * from uwaga where id_ucznia="+id_ucznia+" and data between (select pocz_roku from rok_szkolny where rok_szkolny='"+rok_szkolny+"') and (select k_roku from rok_szkolny where rok_szkolny='"+rok_szkolny+"') order by data desc");

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}
		return result;
	}
	
	public List<Map<String, String>> getUczenPrzedmioty(int id_ucznia){

		try {
			res = connection.createStatement().executeQuery("select distinct id_przedmiotu, nazwa, rok_szkolny from przedmiot natural join ocena where id_ucznia="+id_ucznia);

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}
		return result;
	}
	
	public List<Map<String, String>> ocenyCalyRok(int id_ucznia, String rok_szkolny){

		try {
			res = connection.createStatement().executeQuery("select * from ocena where id_ucznia="+id_ucznia+" and data between (select pocz_roku from rok_szkolny where rok_szkolny='"+rok_szkolny+"') and (select k_zimowego from rok_szkolny where rok_szkolny='"+rok_szkolny+"') order by wartosc");

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}
		return result;
	}
	
	public List<Map<String, String>> getUczenOceny(int id_ucznia, int id_przedmiotu, String rok_szkolny, String semestr){

		try {
			if(semestr.equals("zimowy")){
				res = connection.createStatement().executeQuery("select * from ocena where id_ucznia="+id_ucznia +" and id_przedmiotu="+id_przedmiotu+" and data between (select pocz_roku from rok_szkolny where rok_szkolny='"+rok_szkolny+"') and (select k_zimowego from rok_szkolny where rok_szkolny='"+rok_szkolny+"') order by data");

			}
			else if(semestr.equals("caly")){
				res = connection.createStatement().executeQuery("select * from ocena where id_ucznia="+id_ucznia +" and id_przedmiotu="+id_przedmiotu+" and data between (select pocz_roku from rok_szkolny where rok_szkolny='"+rok_szkolny+"') and (select k_roku  from rok_szkolny where rok_szkolny='"+rok_szkolny+"') order by wartosc ");

			}
			else if(semestr.equals("cos")){
				res = connection.createStatement().executeQuery("select * from ocena where id_ucznia="+id_ucznia +" and id_przedmiotu="+id_przedmiotu+" and data between (select pocz_roku from rok_szkolny where rok_szkolny='"+rok_szkolny+"') and (select k_roku  from rok_szkolny where rok_szkolny='"+rok_szkolny+"') order by data ");

			}
			else {
				res = connection.createStatement().executeQuery("select * from ocena where id_ucznia="+id_ucznia +" and id_przedmiotu="+id_przedmiotu+" and data between date_add((select k_zimowego from rok_szkolny where rok_szkolny='"+rok_szkolny+"'), interval 1 day) and (select k_roku  from rok_szkolny where rok_szkolny='"+rok_szkolny+"') order by wartosc");

			}
			
		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}
		return result;
	}
	
	public List<Map<String, String>> getWszyscyNauczyciel(){

		try {
			res = connection.createStatement().executeQuery("select * from nauczyciel order by nazwa");

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}


		return result;
	}
	
	public List<Map<String, String>> getWszyscyUzytkownik(){

		try {
			res = connection.createStatement().executeQuery("(select id_user, nazwa,role from nauczyciel natural join login_data) union (select id_user, nazwa,role from opiekun  natural join login_data) union (select id_user, nazwa,role from uczen natural join login_data) union (select id_user, nazwa,role from admin natural join login_data) order by role,nazwa");

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}


		return result;
	}
	
	public List<Map<String, String>> getWszyscyNieprzypisaniUczen(){

		try {
			res = connection.createStatement().executeQuery("select * from uczen where id_ucznia not in (select id_ucznia from uczen_opiekun) order by nazwa");

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}


		return result;
	}
	
	public List<Map<String, String>> getWszyscyOpiekun(){

		try {
			res = connection.createStatement().executeQuery("select * from opiekun order by nazwa");

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}


		return result;
	}
	
	public List<Map<String, String>> getNauczyciel(int id_nauczyciela){

		try {
			res = connection.createStatement().executeQuery("select * from nauczyciel where id_user="+id_nauczyciela);

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}


		return result;
	}
	
	public List<Map<String, String>> getOpiekun(int id_opiekuna){

		try {
			res = connection.createStatement().executeQuery("select * from opiekun where id_user="+id_opiekuna);

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}


		return result;
	}
	
	public List<Map<String, String>> getAdmin(int id_admina){

		try {
			res = connection.createStatement().executeQuery("select * from admin where id_user="+id_admina);

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}


		return result;
	}
	
	
	public List<Map<String, String>> getUczenOgloszenia(int id_ucznia){

		try {
			res = connection.createStatement().executeQuery("select * from ogloszenia where id_ucznia="+id_ucznia+" and datediff(curdate(),data)< 30 order by data desc");

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}


		return result;
	}
	
	public List<Map<String, String>> getUczenArchiwumOgloszenia(int id_ucznia, String rok_szkolny){

		try {
			res = connection.createStatement().executeQuery("select * from ogloszenia where id_ucznia="+id_ucznia+" and data between (select pocz_roku from rok_szkolny where rok_szkolny='"+rok_szkolny+"') and (select k_roku from rok_szkolny where rok_szkolny='"+rok_szkolny+"') order by data desc");

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}


		return result;
	}
	
	public List<Map<String, String>> getWychowawca(String user, String pass){

		try {
			res = connection.createStatement().executeQuery("select * from wychowawca where id_nauczyciela= ( select id_nauczyciela from nauczyciel where id_user= (select id_user from login_data where login='"+user+"' and password='"+pass+"'))");

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}


		return result;
	}
	
	public int getLoginDataId(String user){

		try {
			res = connection.createStatement().executeQuery("select id_user from login_data where login='"+user+"'");

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		int id = 0;
		try {
			res.beforeFirst();
			while(res.next())
				id = res.getInt("id_user");
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return id;
	}
	
	public String getRole(String user){

		try {
			res = connection.createStatement().executeQuery("select role from login_data where id_user="+user+"");

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		String role = "";
		try {
			res.beforeFirst();
			while(res.next())
				role = res.getString("role");
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return role;
	}
	
	
	

	public List<Map<String, String>> getUsername(String user, String pass){

		try {
			res = connection.createStatement().executeQuery("select * from uczen natural join login_data where id_user= ( select id_user from login_data where login='"+user+"' and password='"+pass+"')");
			if(res.next()){
			}
			else {
				res = connection.createStatement().executeQuery("select * from opiekun natural join login_data where id_user= ( select id_user from login_data where login='"+user+"' and password='"+pass+"')");
				if(res.next()){
				}
				else {
					res = connection.createStatement().executeQuery("select * from nauczyciel natural join login_data where id_user= ( select id_user from login_data where login='"+user+"' and password='"+pass+"')");
					if(res.next()){
					}
					else {
						res = connection.createStatement().executeQuery("select * from admin natural join login_data where id_user= ( select id_user from login_data where login='"+user+"' and password='"+pass+"')");
						if(res.next()){
							
						}
						
					}
					
				}
			}

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		try {

			result = Utilities_servlet.createMap(res);
		} catch (SQLException e) {
		}


		return result;
	}
	
	public int przypiszNauczyciela(int id_nauczyciela, int id_grupy){

		try {
			wynik = connection.createStatement().executeUpdate("INSERT INTO `diary_db`.`wychowawca` VALUES (NULL, '"+id_nauczyciela+"', '"+id_grupy+"')");

		} catch (SQLException e) {
			System.err.println("Zle zapytanie");
		}
		
		return wynik;
	}
}
