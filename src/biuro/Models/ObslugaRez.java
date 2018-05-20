package biuro.Models;

import java.io.Serializable;

public class ObslugaRez implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private int idRez;
	private int idPrac;
	
	public ObslugaRez() {
		
	}

	public int getIdRez() {
		return idRez;
	}

	public void setIdRez(int idRez) {
		this.idRez = idRez;
	}

	public int getIdPrac() {
		return idPrac;
	}

	public void setIdPrac(int idPrac) {
		this.idPrac = idPrac;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
