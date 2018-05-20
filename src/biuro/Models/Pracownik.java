package biuro.Models;

import java.io.Serializable;

public class Pracownik implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private int idPracownika;
	private String imie;
	private String nazwisko;
	private int idSpec;
	private String login;
	private String haslo;
	private String nrTel;
	
	

	public Pracownik() {
		
	}
	
	
	
	public String getNrTel() {
		return nrTel;
	}



	public void setNrTel(String nrTel) {
		this.nrTel = nrTel;
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

	public int getIdPracownika() {
		return idPracownika;
	}

	public void setIdPracownika(int idPracownika) {
		this.idPracownika = idPracownika;
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

	public int getIdSpec() {
		return idSpec;
	}

	public void setIdSpec(int idSpec) {
		this.idSpec = idSpec;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
