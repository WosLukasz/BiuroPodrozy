package biuro.Models;

import java.io.Serializable;

public class Specjalizacja implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private int idSpec;
	private String nazwa;
	
	public Specjalizacja() {
		
	}

	public int getIdSpec() {
		return idSpec;
	}

	public void setIdSpec(int idSpec) {
		this.idSpec = idSpec;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	

}
