package biuro.Models;

import java.io.Serializable;

public class Klient implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int idKlienta;
	private String imie;
	private String nazwisko;
	private String nrDowodu;
	private String login;
	private String haslo;
	

	public Klient() {
		
	}
	
	
	
	public String getLogin() {
		return login;
	}



	public void setLogin(String login) {
		this.login = login;
	}



	public String getHaslo() {
		return haslo;
	}



	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}



	public int getIdKlienta() {
		return idKlienta;
	}
	public void setIdKlienta(int idKlienta) {
		this.idKlienta = idKlienta;
	}
	public String getImie() {
		return imie;
	}
	public void setImie(String imie) {
		this.imie = imie;
	}
	public String getNazwisko() {
		return nazwisko;
	}
	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}
	public String getNrDowodu() {
		return nrDowodu;
	}
	public void setNrDowodu(String nrDowodu) {
		this.nrDowodu = nrDowodu;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
