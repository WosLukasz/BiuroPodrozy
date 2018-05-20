package biuro.Models;

import java.io.Serializable;
import java.sql.Date;

public class OfertaWyc implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idOferty;
	private int cena;
	private int cenaBrutto;
	private String nazwaHotelu;
	private String lokalizacja;
	private String wyzywienie;
	private Date dataWyjazdu;
	private Date dataPowrotu;
	private int iloscMiejsc;
	private String opisWycieczki;
	private int dostepnoscOf;
	private String zdj;
	
	public OfertaWyc() {
		
	}
	
	public String getZdj() {
		return zdj;
	}

	public void setZdj(String zdj) {
		this.zdj = zdj;
	}
	
	public int getCenaBrutto() {
		return cenaBrutto;
	}

	public void setCenaBrutto(int cenaBrutto) {
		this.cenaBrutto = cenaBrutto;
	}

	public int getIdOferty() {
		return idOferty;
	}
	public void setIdOferty(int idOferty) {
		this.idOferty = idOferty;
	}
	public int getCena() {
		return cena;
	}
	public void setCena(int cena) {
		this.cena = cena;
	}
	public String getNazwaHotelu() {
		return nazwaHotelu;
	}
	public void setNazwaHotelu(String nazwaHotelu) {
		this.nazwaHotelu = nazwaHotelu;
	}
	public String getLokalizacja() {
		return lokalizacja;
	}
	public void setLokalizacja(String lokalizacja) {
		this.lokalizacja = lokalizacja;
	}
	public String getWyzywienie() {
		return wyzywienie;
	}
	public void setWyzywienie(String wyzywienie) {
		this.wyzywienie = wyzywienie;
	}
	public Date getDataWyjazdu() {
		return dataWyjazdu;
	}
	public void setDataWyjazdu(Date dataWyjazdu) {
		this.dataWyjazdu = dataWyjazdu;
	}
	public Date getDataPowrotu() {
		return dataPowrotu;
	}
	public void setDataPowrotu(Date dataPowrotu) {
		this.dataPowrotu = dataPowrotu;
	}
	public int getIloscMiejsc() {
		return iloscMiejsc;
	}
	public void setIloscMiejsc(int iloscMiejsc) {
		this.iloscMiejsc = iloscMiejsc;
	}
	public String getOpisWycieczki() {
		return opisWycieczki;
	}
	public void setOpisWycieczki(String opisWycieczki) {
		this.opisWycieczki = opisWycieczki;
	}
	public int getDostepnoscOf() {
		return dostepnoscOf;
	}
	public void setDostepnoscOf(int dostepnoscOf) {
		this.dostepnoscOf = dostepnoscOf;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
