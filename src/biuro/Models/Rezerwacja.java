package biuro.Models;

import java.io.Serializable;
import java.sql.Date;

public class Rezerwacja implements Serializable{

	
	private static final long serialVersionUID = 1L;

	private int idRez;
	private int czyZaplacone;
	private int idKlienta;
	private int idOferty;
	private Date dataRez;
	
	public Rezerwacja() {
		
	}

	
	
	public Date getDataRez() {
		return dataRez;
	}



	public void setDataRez(Date dataRez) {
		this.dataRez = dataRez;
	}



	public int getIdRez() {
		return idRez;
	}

	
	public void setIdRez(int idRez) {
		this.idRez = idRez;
	}

	public int getCzyZaplacone() {
		return czyZaplacone;
	}

	public void setCzyZaplacone(int czyZaplacone) {
		this.czyZaplacone = czyZaplacone;
	}

	public int getIdKlienta() {
		return idKlienta;
	}

	public void setIdKlienta(int idKlienta) {
		this.idKlienta = idKlienta;
	}

	public int getIdOferty() {
		return idOferty;
	}

	public void setIdOferty(int idOferty) {
		this.idOferty = idOferty;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
}
