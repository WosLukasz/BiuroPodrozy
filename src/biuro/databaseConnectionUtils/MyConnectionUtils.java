package biuro.databaseConnectionUtils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import biuro.Models.Klient;
import biuro.Models.OfertaWyc;
import biuro.Models.Pracownik;
import biuro.Models.Rezerwacja;
import biuro.databaseConnection.GetConnection;
import biuro.exception.MyException;

public class MyConnectionUtils {
	

	public static Connection getMyConnection() throws ClassNotFoundException, SQLException {
		
			return GetConnection.dajPolaczenie();
		
	}
	
	public static void closeMyConnection(Connection conn)
	{
		try {
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Klient znajdzKlienta(String login ,String haslo) throws ClassNotFoundException, SQLException {
		
		Connection conn = getMyConnection();
		Klient k = null;
		
			PreparedStatement stm = null;
		    ResultSet rs = null;
		    String sql = "SELECT * FROM Klient WHERE login= ? AND haslo=?";
		    stm = conn.prepareStatement(sql);
		    stm.setString(1, login);
		    stm.setString(2, haslo);
		      
		        rs = stm.executeQuery();
		
		        while(rs.next())
		        {
		        	k= new Klient();
		            k.setIdKlienta(Integer.parseInt(rs.getString("id_klienta")));
		            k.setImie(rs.getString("imie"));
		            k.setNazwisko(rs.getString("nazwisko"));
		            k.setNrDowodu(rs.getString("nr_dowodu"));
		            k.setLogin(rs.getString("login"));
		            k.setHaslo(rs.getString("haslo"));
		        }  
		        conn.close();

		return k;
	}
	
	public static Pracownik znajdzPracownika(String login, String haslo ) throws ClassNotFoundException, SQLException {
		
		Connection conn = getMyConnection();
		Pracownik p = null;
		
			PreparedStatement stm = null;
		    ResultSet rs = null;
		    String sql = "SELECT * FROM Pracownik WHERE login=? AND haslo=? ";
		   
		    try {
		    stm = conn.prepareStatement(sql);
		    stm.setString(1, login);
		    stm.setString(2, haslo);
		      
		        rs = stm.executeQuery();
		
		        while(rs.next())
		        {
		        	p= new Pracownik();
		            p.setIdPracownika(Integer.parseInt(rs.getString("id_prac")));
		            p.setImie(rs.getString("imie"));
		            p.setNazwisko(rs.getString("nazwisko"));
		            p.setIdSpec(rs.getInt("SPECJALIZACJA_ID_SPEC"));
		            p.setLogin(rs.getString("login"));
		            p.setHaslo(rs.getString("haslo"));
		            p.setNrTel(rs.getString("numer_tel"));
		        }  
		        conn.close();
		    }catch( Exception e)
		    {
		    	System.out.println(e);
		    }
		    
		return p;
	}
	
	
	public static void dodajKlienta( Klient k) throws ClassNotFoundException, SQLException {
		
		Connection conn = getMyConnection();
		
		String sql = "Insert into klient (id_klienta, imie, nazwisko, nr_dowodu, login, haslo)"
				+ " values(Numeracja1.nextval, ?, ?, ?, ? , ?)";

		PreparedStatement stm = null;

		
		try {

			stm = conn.prepareStatement(sql);
			
			stm.setString(1, k.getImie());
			stm.setString(2, k.getNazwisko());
			stm.setString(3, k.getNrDowodu());
			stm.setString(4, k.getLogin());
			stm.setString(5, k.getHaslo());

			stm.executeUpdate();

			stm.close();
			conn.close();
			
			}catch (SQLException e) {
			//	System.out.println("B³¹d podczas wykonywania zapytania");
				try {
					if (stm != null) {
						stm.close();
					}
				} catch (Exception eStm) {
					// nic nie rob
				}

				try {
					if (conn != null) {
						conn.close();
					}
				} catch (Exception eConn) {
					// nic nie rob
				}

			}
		

	}
	
	public static List<OfertaWyc> dajWszystkieOferty() throws ClassNotFoundException, SQLException{
		
		List<OfertaWyc> lista = new ArrayList<>();
		
		Connection conn = getMyConnection();
		
		//String sql = "SELECT * FROM Oferta_wyc WHERE DOSTEPNOSC_OF=1 ORDER BY ID_OFERTY";
		String sql = "SELECT CenaBrutto(cena) as CenaBrutto, ID_OFERTY, CENA, NAZWA_HOTELU, LOKALIZACJA, WYZYWIENIE, DATA_WYJAZDU , DATA_POWROTU , ILOSC_MIEJSC , OPIS_WYCIECZKI , DOSTEPNOSC_OF  FROM Oferta_wyc WHERE DOSTEPNOSC_OF=1 ORDER BY ID_OFERTY";
		
		
		ResultSet rs = null;
		
		PreparedStatement stm = null;
		
		try {
			
			stm = conn.prepareStatement(sql);
			
			rs = stm.executeQuery();
			
			while(rs.next())
			{
				OfertaWyc o = new OfertaWyc();
				
				o.setIdOferty(rs.getInt("ID_OFERTY"));
				o.setCena(rs.getInt("CENA"));
				o.setCenaBrutto(rs.getInt("CenaBrutto"));
				o.setNazwaHotelu(rs.getString("NAZWA_HOTELU"));
				o.setLokalizacja(rs.getString("LOKALIZACJA"));
				o.setWyzywienie(rs.getString("WYZYWIENIE"));
				o.setDataWyjazdu(rs.getDate("DATA_WYJAZDU"));
				o.setDataPowrotu(rs.getDate("DATA_POWROTU"));
				o.setIloscMiejsc(rs.getInt("ILOSC_MIEJSC"));
				o.setOpisWycieczki(rs.getString("OPIS_WYCIECZKI"));
				o.setDostepnoscOf(rs.getInt("DOSTEPNOSC_OF"));
				o.setZdj("img/"+o.getIdOferty()+".jpg");
				
				
				lista.add(o);

			}

			stm.close();
			conn.close();
			
			}catch (SQLException e) {
				//System.out.println("B³¹d podczas wykonywania zapytania");
				try {
					if (stm != null) {
						stm.close();
					}
				} catch (Exception eStm) {
					// nic nie rob
				}

				try {
					if (conn != null) {
						conn.close();
					}
				} catch (Exception eConn) {
					// nic nie rob
				}

			}
		
		
		
		
			
		return lista;
		
	}
	
	public static OfertaWyc znajdzWycieczkePoId( int id) throws ClassNotFoundException, SQLException{
		
		
		Connection conn = getMyConnection();
		
		OfertaWyc o = null;
		
		String sql = "SELECT CenaBrutto(cena) as CenaBrutto, ID_OFERTY, CENA, NAZWA_HOTELU, LOKALIZACJA, WYZYWIENIE, DATA_WYJAZDU , DATA_POWROTU , ILOSC_MIEJSC , OPIS_WYCIECZKI , DOSTEPNOSC_OF  FROM Oferta_wyc WHERE id_oferty = ?";

		ResultSet rs = null;
		
		PreparedStatement stm = null;
		
		try {
			
			stm = conn.prepareStatement(sql);
			
			stm.setInt(1, id);
			
			rs = stm.executeQuery();

			while(rs.next())
			{
				o = new OfertaWyc();
				
				o.setIdOferty(rs.getInt("ID_OFERTY"));
				o.setCena(rs.getInt("CENA"));
				o.setCenaBrutto(rs.getInt("CenaBrutto"));
				o.setNazwaHotelu(rs.getString("NAZWA_HOTELU"));
				o.setLokalizacja(rs.getString("LOKALIZACJA"));
				o.setWyzywienie(rs.getString("WYZYWIENIE"));
				o.setDataWyjazdu(rs.getDate("DATA_WYJAZDU"));
				o.setDataPowrotu(rs.getDate("DATA_POWROTU"));
				o.setIloscMiejsc(rs.getInt("ILOSC_MIEJSC"));
				o.setOpisWycieczki(rs.getString("OPIS_WYCIECZKI"));
				o.setDostepnoscOf(rs.getInt("DOSTEPNOSC_OF"));
				o.setZdj("img/"+o.getIdOferty()+".jpg");
				
			}
			
			stm.close();
			conn.close();
			
			}catch (SQLException e) {
				//System.out.println("B³¹d podczas wykonywania zapytania");
				try {
					if (stm != null) {
						stm.close();
					}
				} catch (Exception eStm) {
					// nic nie rob
				}

				try {
					if (conn != null) {
						conn.close();
					}
				} catch (Exception eConn) {
					// nic nie rob
				}

			}
		
		
		return o;
		
	}
	
	public static Map<Rezerwacja,OfertaWyc> znajdzRezerwacjeKlienta(Klient k) throws ClassNotFoundException, SQLException{
		
		List<Rezerwacja> list = new ArrayList<>();
		
		Connection conn = getMyConnection();
		
		String sql = "SELECT * FROM REZERWACJA WHERE KLIENT_ID_KLIENTA = ?";

		ResultSet rs = null;
		
		PreparedStatement stm = null;
		
		try {
			
			stm = conn.prepareStatement(sql);
			
			stm.setInt(1, k.getIdKlienta());
			
			rs = stm.executeQuery();

			
			while(rs.next())
			{
				Rezerwacja r = new Rezerwacja();
				
				r.setIdRez(rs.getInt("id_rez"));
				r.setCzyZaplacone(rs.getInt("czy_zaplacone"));
				r.setIdKlienta(rs.getInt("klient_id_klienta"));
				r.setIdOferty(rs.getInt("oferta_wyc_id_oferty"));
				r.setDataRez(rs.getDate("data_rez"));
				
				//System.out.println(rs.getInt("id_rez")+ " | "+ rs.getInt("klient_id_klienta") );
				list.add(r);

			}
			
			stm.close();
			conn.close();
			
			}catch (SQLException e) {
				//System.out.println("B³¹d podczas wykonywania zapytania");
				try {
					if (stm != null) {
						stm.close();
					}
				} catch (Exception eStm) {
					// nic nie rob
				}

				try {
					if (conn != null) {
						conn.close();
					}
				} catch (Exception eConn) {
					// nic nie rob
				}

			}
		
		//List<OfertaWyc> listaOfert = new ArrayList<>();
		
		Map<Rezerwacja,OfertaWyc> mapa = new HashMap<>();
		
		
		for(Rezerwacja r :list)
		{
			OfertaWyc o = znajdzWycieczkePoId(r.getIdOferty());
			
			//System.out.println(o.getIdOferty()+ " / "+ o.getNazwaHotelu() );
			
			mapa.put(r,o);
			
		}
		

		return mapa;
	}
	
	
	
	
	
	public static void dodajOferte(OfertaWyc w) throws ClassNotFoundException, SQLException{
		
		
			Connection conn = getMyConnection();
			
			conn.setAutoCommit(false);
			
			String sql = "Insert into Oferta_wyc (id_oferty, cena, nazwa_hotelu, lokalizacja,wyzywienie,data_wyjazdu,data_powrotu,ilosc_miejsc,opis_wycieczki,dostepnosc_of)"
					+ " values(Numeracja2.NEXTVAL, ?, ?, ?, ? , ? , ? , ? , ? , ?)";
	
			PreparedStatement stm = null;
	
			try {
	
				stm = conn.prepareStatement(sql);
				//!POPRAWIC DATE BO SIE ZLE WPISUJE !!
				
				
				stm.setString(1, String.valueOf(w.getCena()));
				stm.setString(2, w.getNazwaHotelu());
				stm.setString(3, w.getLokalizacja());
				stm.setString(4, w.getWyzywienie());
				stm.setDate(5,  w.getDataWyjazdu());
				stm.setDate(6,  w.getDataPowrotu());
				stm.setString(7, String.valueOf(w.getIloscMiejsc()));
				stm.setString(8, w.getOpisWycieczki());
				stm.setString(9, String.valueOf(w.getDostepnoscOf()));
				//System.out.println("przed update");
				stm.executeUpdate();
				//System.out.println("zrobiono update");
	
				stm.close();
				
				conn.commit();
				
				conn.close();
				}catch (SQLException e) {
					System.out.println(e);
					try {
						if (stm != null) {
							stm.close();
						}
					} catch (Exception eStm) {
						// nic nie rob
					}
	
					try {
						if (conn != null) {
							conn.close();
						}
					} catch (Exception eConn) {
						// nic nie rob
					}
	
				}
	}
	
	
	public static List<Pracownik> dajListePracownikowODanejSpecjalizacji(int spec , Connection conn)
	{
		
		List<Pracownik> list = new ArrayList<>();
		Pracownik p;
		
		String sql = "SELECT * FROM PRACOWNIK WHERE SPECJALIZACJA_ID_SPEC = ? ";

		ResultSet rs = null;
		
		PreparedStatement stm = null;
		
		try {
			
			stm = conn.prepareStatement(sql);
			
			stm.setInt(1, spec);
			
			rs = stm.executeQuery();

			while(rs.next())
	        {
	        	p= new Pracownik();
	        	
	        	p.setIdPracownika(rs.getInt("id_prac"));
	        	p.setIdSpec(rs.getInt("SPECJALIZACJA_ID_SPEC"));
	        	p.setImie(rs.getString("imie"));
	        	p.setNazwisko(rs.getString("nazwisko"));
	        	p.setLogin(rs.getString("login"));
	        	p.setHaslo(rs.getString("haslo"));
	        	p.setNrTel(rs.getString("numer_tel"));
	        	
	        	list.add(p);
	        	
	        }  
			
			
			stm.close();
			
			
			}catch (SQLException e) {
				//System.out.println("B³¹d podczas wykonywania zapytania");
				try {
					if (stm != null) {
						stm.close();
					}
				} catch (Exception eStm) {
					// nic nie rob
				}

				try {
					if (conn != null) {
						conn.close();
					}
				} catch (Exception eConn) {
					// nic nie rob
				}

			}
		
		return list;
	}
	
	
	public static void dodajObslugeRezerwacji(int idRez, int idPrac,Connection conn){
		
		String sql = "INSERT INTO OBSLUGA_REZ (REZERWACJA_ID_REZ, PRACOWNIK_ID_PRAC) VALUES(?,?)";
		PreparedStatement stm = null;
		try{
			
			stm = conn.prepareStatement(sql);
			
			stm.setInt(1, idRez);
			stm.setInt(2, idPrac);
			//System.out.println("zrobiono dodanie parametrow=============");
			
			stm.executeUpdate();

			
		}catch (SQLException e) {
			//System.out.println(e);
			//System.out.println("B³¹d podczas wykonywania zapytania4");
			try {
				if (stm != null) {
					stm.close();
				}
			} catch (Exception eStm) {
				// nic nie rob
			}

			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception eConn) {
				// nic nie rob
			}
		}
		
		
		
	}
	
	
	public static int sprawdzNrRezerwacji(Connection conn) {
		
		
		
		String sql = "SELECT Numeracja4.Currval as ile FROM DUAL";
		
		PreparedStatement stm = null;
		ResultSet rs=null;
		int id = 0;
		
		try{
			
			stm = conn.prepareStatement(sql);
			
			rs = stm.executeQuery();
			
	        while(rs.next())
	        {
	        	id = rs.getInt("ile");
	        	
	        }  
			

		}catch (SQLException e) {
			//System.out.println("B³¹d podczas wykonywania zapytania5");
			try {
				if (stm != null) {
					stm.close();
				}
			} catch (Exception eStm) {
				// nic nie rob
			}

			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception eConn) {
				// nic nie rob
			}

		}
		
		return id;
		
	}
	
	
	public static void kupWycieczke(int idWycieczki, Klient k) throws ClassNotFoundException, SQLException {
		
		Connection conn = getMyConnection();
		
		String sql = "Insert into Rezerwacja (id_rez, czy_zaplacone, KLIENT_ID_KLIENTA, OFERTA_WYC_ID_OFERTY, data_rez)"
				+ " values(Numeracja4.NEXTVAL, 0, ?, ?,CURRENT_DATE)";
		
		PreparedStatement stm = null;

		try {

			stm = conn.prepareStatement(sql);
			
			stm.setInt(1, k.getIdKlienta());
			stm.setInt(2, idWycieczki);
			
			
			System.out.println("przed update rezerwacji w kup");
			stm.executeUpdate();
			System.out.println("zrobiono update rezerwacji w kup");

			stm.close();
			
			
			}catch (SQLException e) {
				//System.out.println("B³¹d podczas wykonywania zapytania1");
				try {
					if (stm != null) {
						stm.close();
					}
				} catch (Exception eStm) {
					// nic nie rob
				}

				try {
					if (conn != null) {
						conn.close();
					}
				} catch (Exception eConn) {
					// nic nie rob
				}

			}
		
		String sql2 = "UPDATE OFERTA_WYC SET ILOSC_MIEJSC = ILOSC_MIEJSC-1 WHERE ID_OFERTY = ?";
		PreparedStatement stm2 = null;
		try{
			
			stm2 = conn.prepareStatement(sql2);
			
			stm2.setInt(1, idWycieczki);
			System.out.println("przed update oferta w kup");
			stm2.executeUpdate();
			System.out.println("po update oferta w kup");

			stm2.close();
			
			
		}catch (SQLException e) {
			//System.out.println("B³¹d podczas wykonywania zapytania2");
			try {
				if (stm != null) {
					stm.close();
				}
			} catch (Exception eStm) {
				// nic nie rob
			}

			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception eConn) {
				// nic nie rob
			}

		}
		conn.commit();
		
		System.out.println("przed zlozonymi operacjami :D");
		
		//losowanie przydzielonych pracoenikow do danej rezerwacji
		
		List<Pracownik> list1 = new ArrayList<>();
		List<Pracownik> list2 = new ArrayList<>();
		List<Pracownik> list3 = new ArrayList<>();
		
		
		
		list1 = dajListePracownikowODanejSpecjalizacji(1,conn);
		list2 = dajListePracownikowODanejSpecjalizacji(2,conn);
		list3 = dajListePracownikowODanejSpecjalizacji(3,conn);
		
		Collections.shuffle(list1);
		Collections.shuffle(list2);
		Collections.shuffle(list3);
		
		try {
			int a = list1.get(0).getIdPracownika();
			int b = list2.get(0).getIdPracownika();
			int c = list3.get(0).getIdPracownika();
			
			int idRez = sprawdzNrRezerwacji(conn);
			
			//System.out.println("Id rez: "+idRez);
			
			dodajObslugeRezerwacji(idRez, a, conn);
			dodajObslugeRezerwacji(idRez, b, conn);
			dodajObslugeRezerwacji(idRez, c, conn);
			
			
		}catch(IndexOutOfBoundsException e)
		{
			e.printStackTrace();
		}


		conn.close();
	}
	
	
	
	public static void myRollback(Connection conn) {
		
		try {
			conn.rollback();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	public static List<OfertaWyc> wykonajZlozoneZapytanie(String wzorzec, String lokalizacja, String cenamin, String cenamax,
			 String datamin, String datamax, String sort) throws ClassNotFoundException, SQLException {
		
		List<OfertaWyc> lista = new LinkedList<>();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = null;
		
        
        String zapytanie = "SELECT CenaBrutto(cena) as CenaBrutto, ID_OFERTY, CENA, NAZWA_HOTELU, LOKALIZACJA, WYZYWIENIE, DATA_WYJAZDU , DATA_POWROTU , ILOSC_MIEJSC , OPIS_WYCIECZKI , DOSTEPNOSC_OF  FROM Oferta_Wyc WHERE (NAZWA_HOTELU LIKE ? OR LOKALIZACJA LIKE ? OR OPIS_WYCIECZKI LIKE ? ) AND LOKALIZACJA LIKE ? AND cena >= ? AND cena <= ? AND data_wyjazdu >= ? AND data_POWROTU <= ? AND DOSTEPNOSC_OF=1 ORDER BY ? ASC";
		Connection conn = getMyConnection();

		ResultSet rs = null;
		
		PreparedStatement stm = null;

		try {
			
			stm = conn.prepareStatement(zapytanie);
			
			if(wzorzec!=null && wzorzec.length()!=0)
			{
				stm.setString(1, "%"+wzorzec+"%");
				stm.setString(2, "%"+wzorzec+"%");
				stm.setString(3, "%"+wzorzec+"%");

				
			}else {
				stm.setString(1, "%");
				stm.setString(2, "%");
				stm.setNString(3, "%");
			}
			
			
			if(lokalizacja!=null && lokalizacja.length()!=0) {
				
				stm.setString(4, "%"+lokalizacja+"%");
			}else
			{
				stm.setString(4, "%");
			}
			
			if(cenamin!=null && cenamin.length()!=0) {
				
				
				stm.setString(5, cenamin);
			}else
			{
				stm.setString(5, "0");
			}
				
			
			if(cenamax!=null && cenamax.length()!=0) {
				
			
				stm.setString(6, cenamax);
			}else
			{
			
				stm.setString(6, String.valueOf(Integer.MAX_VALUE-1));
			}
			
			
			if(datamin!=null && datamin.length()!=0) {
				
				
				try {
					parsed = format.parse(datamin);
				} catch (ParseException e2) {
					
					e2.printStackTrace();
				}
		        
		        java.sql.Date dataminimalna = new java.sql.Date(parsed.getTime());
				
				
		        stm.setDate(7, dataminimalna);
			}else
			{
				
				try {
					parsed = format.parse("0001-01-01");
				} catch (ParseException e1) {
					
					e1.printStackTrace();
				}
		        java.sql.Date dataminimalna = new java.sql.Date(parsed.getTime());
		        
				 stm.setDate(7, dataminimalna);
			}
			
			if(datamax!=null && datamax.length()!=0) {
				
				 try {
						parsed = format.parse(datamax);
					} catch (ParseException e1) {
						
						e1.printStackTrace();
					}
			        java.sql.Date datamaksymalna = new java.sql.Date(parsed.getTime());
				
			        stm.setDate(8, datamaksymalna);
			}else
			{
				try {
					parsed = format.parse("9000-12-12");
				} catch (ParseException e1) {
					
					e1.printStackTrace();
				}
		        java.sql.Date datamaksymalna = new java.sql.Date(parsed.getTime());
				
				
				 stm.setDate(8, datamaksymalna);
			}
			
			if(sort!=null && sort.length()!=0) {
				
				stm.setString(9, sort);
			}

			
			rs = stm.executeQuery();
			
			while(rs.next())
			{
				OfertaWyc o = new OfertaWyc();
				
				o.setIdOferty(rs.getInt("ID_OFERTY"));
				o.setCena(rs.getInt("CENA"));
				o.setCenaBrutto(rs.getInt("CenaBrutto"));
				o.setNazwaHotelu(rs.getString("NAZWA_HOTELU"));
				o.setLokalizacja(rs.getString("LOKALIZACJA"));
				o.setWyzywienie(rs.getString("WYZYWIENIE"));
				o.setDataWyjazdu(rs.getDate("DATA_WYJAZDU"));
				o.setDataPowrotu(rs.getDate("DATA_POWROTU"));
				o.setIloscMiejsc(rs.getInt("ILOSC_MIEJSC"));
				o.setOpisWycieczki(rs.getString("OPIS_WYCIECZKI"));
				o.setDostepnoscOf(rs.getInt("DOSTEPNOSC_OF"));
				o.setZdj("img/"+o.getIdOferty()+".jpg");

				
				lista.add(o);

			}

			stm.close();
			conn.close();
			
			}catch (SQLException e) {
			//	System.out.println(e);
				//System.out.println("B³¹d podczas wykonywania zapytania");
				e.printStackTrace();
				try {
					if (stm != null) {
						stm.close();
					}
				} catch (Exception eStm) {
					// nic nie rob
				}

				try {
					if (conn != null) {
						conn.close();
					}
				} catch (Exception eConn) {
					// nic nie rob
				}

			}
		
		
		System.out.println("WYSWIETLANIE Z Utils");
		
		for(OfertaWyc o : lista)
		{
			System.out.println(o.getIdOferty() + " | "+ o.getCena());
		}
		
		return lista;
		

	}

	public static List<Pracownik> dajkWszystkichPracownikowKontaktowych(Rezerwacja key) throws ClassNotFoundException, SQLException {

		
		Connection conn = getMyConnection();
		List<Pracownik> lista = new ArrayList<Pracownik>();
		Pracownik p = null;
		
		
			PreparedStatement stm = null;
		    ResultSet rs = null;
		    String sql = "SELECT * FROM Pracownik p JOIN Obsluga_rez r ON p.ID_PRAC=r.PRACOWNIK_ID_PRAC WHERE r.REZERWACJA_ID_REZ = ?";
		    
		    stm = conn.prepareStatement(sql);
		   
		    stm.setInt(1, key.getIdRez());
		    
		      
		        rs = stm.executeQuery();
		
		        while(rs.next())
		        {
		        	p= new Pracownik();
		        	
		        	p.setIdPracownika(rs.getInt("id_prac"));
		        	p.setIdSpec(rs.getInt("SPECJALIZACJA_ID_SPEC"));
		        	p.setImie(rs.getString("imie"));
		        	p.setNazwisko(rs.getString("nazwisko"));
		        	p.setLogin(rs.getString("login"));
		        	p.setHaslo(rs.getString("haslo"));
		        	p.setNrTel(rs.getString("numer_tel"));
		        	//System.out.println(p.getImie());
		        	lista.add(p);
		        		
		        }   
		        
		        conn.close();

		return lista;
	}

	public static void zaplacZaWycieczke(int id) throws ClassNotFoundException, SQLException {
		
		Connection conn = getMyConnection();
		
		//OfertaWyc o = null;
		
		String sql = "UPDATE Rezerwacja SET czy_zaplacone = 1 WHERE id_rez = ?";
		
		PreparedStatement stm = null;
		
		try {
			
			stm = conn.prepareStatement(sql);
			
			stm.setInt(1, id);
			
			stm.executeUpdate();
			
			stm.close();
			conn.close();
			
			}catch (SQLException e) {
				//System.out.println("B³¹d podczas wykonywania zapytania");
				try {
					if (stm != null) {
						stm.close();
					}
				} catch (Exception eStm) {
					// nic nie rob
				}

				try {
					if (conn != null) {
						conn.close();
					}
				} catch (Exception eConn) {
					// nic nie rob
				}

			}

	}

	public static void usunRezerwacje(int idRez, int idOferty) throws ClassNotFoundException, SQLException {
		
		Connection conn = getMyConnection();
		
		
		System.out.println("usunRezerwacje("+idRez+","+idOferty+")");
		
		String sql0 = "DELETE FROM OBSLUGA_REZ WHERE REZERWACJA_ID_REZ = ?";
		
		PreparedStatement stm = null;
		
		try {
			
			stm = conn.prepareStatement(sql0);
			
			stm.setInt(1, idRez);
			
			stm.executeUpdate();
			System.out.println("po delete obsluga");
			
			stm.close();
			
			
			}catch (SQLException e) {
				//System.out.println("B³¹d podczas wykonywania zapytania");
				try {
					if (stm != null) {
						stm.close();
					}
				} catch (Exception eStm) {
					// nic nie rob
				}

				try {
					if (conn != null) {
						conn.close();
					}
				} catch (Exception eConn) {
					// nic nie rob
				}

			}
		
		String sql2 = "UPDATE OFERTA_WYC SET ILOSC_MIEJSC = ILOSC_MIEJSC+1 WHERE ID_OFERTY = ?";
		//PreparedStatement stm2 = null;
		try{
			
			stm = conn.prepareStatement(sql2);
			
			stm.setInt(1, idOferty);
			System.out.println("przed update oferta wyc");
			stm.executeUpdate();
			System.out.println("po update oferta wyc");

			stm.close();
			
			
		}catch (SQLException e) {
			//System.out.println("B³¹d podczas wykonywania zapytania2");
			try {
				if (stm != null) {
					stm.close();
				}
			} catch (Exception eStm) {
				// nic nie rob
			}

			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception eConn) {
				// nic nie rob
			}
		}
		
		String sql = "DELETE FROM Rezerwacja WHERE id_rez = ?";
		
		//PreparedStatement stm = null;
		
		try {
			
			stm = conn.prepareStatement(sql);
			
			stm.setInt(1, idRez);
			
			stm.executeUpdate();
			System.out.println("po delete rezerwacja");
			
			stm.close();
			conn.close();
			
			}catch (SQLException e) {
				//System.out.println("B³¹d podczas wykonywania zapytania");
				try {
					if (stm != null) {
						stm.close();
					}
				} catch (Exception eStm) {
					// nic nie rob
				}

				try {
					if (conn != null) {
						conn.close();
					}
				} catch (Exception eConn) {
					// nic nie rob
				}
			}
	}

	public static void usunOferte(int idOf) throws ClassNotFoundException, SQLException, MyException {
		
		Connection conn = getMyConnection();
		
		PreparedStatement stm = null;
		
		String sql0 = "SELECT OFERTA_WYC_ID_OFERTY FROM REZERWACJA";
		
		ResultSet rs = null;
		
		int s;
		try {
			
			stm = conn.prepareStatement(sql0);
			
			//System.out.println("ID oferty usuwanej: "+idOf);
			rs = stm.executeQuery();
			
			System.out.println("jestem w select");
			
			while(rs.next())
			{
				s=rs.getInt("OFERTA_WYC_ID_OFERTY");
				System.out.println(s);
				
				if(s==idOf)
				{
					System.out.println("powtarza siê ");
					throw  new MyException("Nie mo¿na usn¹æ tej oferty poniewaz jest ona zarezerwowana. Jesli chcesz ja usun¹c prosze usun¹c rezerwacje powi¹zane z ta ofert¹");
				}
			}
			
			stm.close();
			
			}catch (SQLException e) {
				//System.out.println("B³¹d podczas wykonywania zapytania");
				try {
					if (stm != null) {
						stm.close();
					}
				} catch (Exception eStm) {
					// nic nie rob
				}

				try {
					if (conn != null) {
						conn.close();
					}
				} catch (Exception eConn) {
					// nic nie rob
					
				}
			}	
		
		
		
		
		String sql = "DELETE FROM Oferta_wyc WHERE id_oferty = ?";
		
		
		
		try {
			
			stm = conn.prepareStatement(sql);
			
			stm.setInt(1, idOf);
			//System.out.println("ID oferty usuwanej: "+idOf);
			stm.executeUpdate();
			
			stm.close();
			conn.close();
			
			}catch (SQLException e) {
				//System.out.println("B³¹d podczas wykonywania zapytania");
				try {
					if (stm != null) {
						stm.close();
					}
				} catch (Exception eStm) {
					// nic nie rob
				}

				try {
					if (conn != null) {
						conn.close();
					}
				} catch (Exception eConn) {
					// nic nie rob
				}
			}	
	}
	
	public static List<Rezerwacja> dajWszystkieRezerwacjePracownika(int id) throws ClassNotFoundException, SQLException
	{
		 List<Rezerwacja> lista = new ArrayList<Rezerwacja>();
		 
		 Connection conn = getMyConnection();
		 Rezerwacja r=null;
		 
		 PreparedStatement stm = null;
		    ResultSet rs = null;
		    String sql = "SELECT * FROM REZERWACJA R1 JOIN OBSLUGA_REZ R2 ON R1.ID_REZ = R2.REZERWACJA_ID_REZ WHERE R2.PRACOWNIK_ID_PRAC = ?";
		    
		    stm = conn.prepareStatement(sql);
		   
		    stm.setInt(1, id);
		    
		      
		        rs = stm.executeQuery();
		
		        while(rs.next())
		        {
		        	r= new Rezerwacja();
		        	
		        	r.setIdRez(rs.getInt("id_rez"));
		        	r.setCzyZaplacone(rs.getInt("czy_zaplacone"));
		        	r.setIdKlienta(rs.getInt("klient_id_klienta"));
		        	r.setIdOferty(rs.getInt("oferta_wyc_id_oferty"));
		        	r.setDataRez(rs.getDate("data_rez"));
		        	
		        	
		        	
		        	
		        	lista.add(r);
		        		
		        }   
		        
		        conn.close();
		 
		
		return lista;
	}
	
	
	public static Map<Klient, Rezerwacja> dajWszystkichKlientowPracownika(int id) throws ClassNotFoundException, SQLException
	{
		 //List<Klient> lista = new ArrayList<Klient>();
		
		Map<Klient, Rezerwacja> mapa = new HashMap<>();
		 
		 Connection conn = getMyConnection();
		 
		 Klient k=null;
		 Rezerwacja r=null;
		 
		 PreparedStatement stm = null;
		    ResultSet rs = null;
		    String sql = "SELECT * FROM KLIENT K JOIN REZERWACJA R1 ON K.ID_KLIENTA = R1.KLIENT_ID_KLIENTA JOIN OBSLUGA_REZ R2 ON R1.ID_REZ = R2.REZERWACJA_ID_REZ WHERE R2.PRACOWNIK_ID_PRAC = ?";
		    
		    stm = conn.prepareStatement(sql);
		   
		    stm.setInt(1, id);
		    
		      
		        rs = stm.executeQuery();
		
		        while(rs.next())
		        {
		        	k= new Klient();
		            k.setIdKlienta(Integer.parseInt(rs.getString("id_klienta")));
		            k.setImie(rs.getString("imie"));
		            k.setNazwisko(rs.getString("nazwisko"));
		            k.setNrDowodu(rs.getString("nr_dowodu"));
		            k.setLogin(rs.getString("login"));
		            k.setHaslo(rs.getString("haslo"));
		            
		            r= new Rezerwacja();
		        	
		        	r.setIdRez(rs.getInt("id_rez"));
		        	r.setCzyZaplacone(rs.getInt("czy_zaplacone"));
		        	r.setIdKlienta(rs.getInt("klient_id_klienta"));
		        	r.setIdOferty(rs.getInt("oferta_wyc_id_oferty"));
		        	r.setDataRez(rs.getDate("data_rez"));
		        	
		        	mapa.put(k, r);
		        	
		        }  
		        
		        conn.close();

		return mapa;
	}
	
	
	public static void edytujOferte(String idOferty,String nazwaHotelu,String cena,String lokalizacja,String wyzywienie,String dataWyjazdu,String dataPowrotu,String iloscMiejsc,String opisWycieczki) throws ClassNotFoundException, SQLException, ParseException {
		
		Connection conn = getMyConnection();
		 
		 PreparedStatement stm = null;
		    
		String sql = "UPDATE Oferta_wyc SET CENA = ? , NAZWA_HOTELU = ?, LOKALIZACJA = ? , WYZYWIENIE = ? , DATA_WYJAZDU = ? , DATA_POWROTU = ? , ILOSC_MIEJSC = ? , OPIS_WYCIECZKI = ? WHERE ID_OFERTY = ?";
		    
		// String sql = "UPDATE Oferta_wyc SET NAZWA_HOTELU = ? WHERE ID_OFERTY = ?";
		    
		 stm = conn.prepareStatement(sql);
		 
		 System.out.println("jestem w update");
		 
		 if(cena.length()!=0)
		 {
			 
			 stm.setInt(1, Integer.parseInt(cena));
			 System.out.println(cena);
		 }else
		 {
			 stm.setString(1, "CENA");
			 
			 System.out.println("CENA");
		 }
		 
		 
		 if(nazwaHotelu.length()!=0)
		 {
			 stm.setString(2, nazwaHotelu);
			 System.out.println(nazwaHotelu);
		 }else
		 {
			 stm.setString(2, "NAZWA_HOTELU");
			 System.out.println("NAZWA_HOTELU");
		 }

		 if(lokalizacja.length()!=0)
		 {
			 stm.setString(3, lokalizacja);
			 System.out.println(lokalizacja);
		 }else
		 {
			 stm.setString(3, "LOKALIZACJA");
			 System.out.println("LOKALIZACJA");
		 }
		 
		 if(wyzywienie.length()!=0)
		 {
			 stm.setString(4, wyzywienie);
			 System.out.println(wyzywienie);
		 }else
		 {
			 stm.setString(4, "WYZYWIENIE");
			 System.out.println("WYZYWIENIE");
		 }
		 
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	     java.util.Date parsed = null;
		 
		 
		 if(lokalizacja.length()!=0)
		 {
			 System.out.println(dataWyjazdu);
			 parsed = format.parse(dataWyjazdu);
			 stm.setDate(5, new java.sql.Date(parsed.getTime()));
		 }else
		 {
			 System.out.println("DATA_WYJAZDU");
			 stm.setString(5, "DATA_WYJAZDU");
		 }
		 if(lokalizacja.length()!=0)
		 {
			 System.out.println(dataPowrotu);
			 parsed = format.parse(dataPowrotu);
			 stm.setDate(6, new java.sql.Date(parsed.getTime()));
		 }else
		 {
			 System.out.println("DATA_POWROTU");
			 stm.setString(6, "DATA_POWROTU");
		 }
		 
		 if(iloscMiejsc.length()!=0)
		 {
			// stm.setString(7, iloscMiejsc);
			 stm.setInt(7, Integer.parseInt(iloscMiejsc));
			 System.out.println(iloscMiejsc);
		 }else
		 {
			 stm.setString(7, "ILOSC_MIEJSC");
			 System.out.println("ILOSC_MIEJSC");
		 }
		 
		 if(opisWycieczki.length()!=0)
		 {
			 stm.setString(8, opisWycieczki);
			 System.out.println(opisWycieczki);
		 }else
		 {
			 stm.setString(8, "OPIS_WYCIECZKI");
			 System.out.println("OPIS_WYCIECZKI");
		 }	 
		 
		 stm.setInt(9, Integer.parseInt(idOferty));
		 System.out.println(Integer.parseInt(idOferty));
		 
		 
		 System.out.println("jestem w update2");
		 
		 
		 stm.executeUpdate();
		 
		 System.out.println("jestem w update koniec");
		 
		 conn.close();

	}

}

	

