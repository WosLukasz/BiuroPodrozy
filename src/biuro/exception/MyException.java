package biuro.exception;

public class MyException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String s;
	
	
	public MyException()
	{
		s="";
	}
	
	public MyException(String s)
	{
		this.s=s;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}
	
	
	

}
