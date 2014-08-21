package me.xcoding.opencdc.net.connector;

public class ConnectionException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public ConnectionException(){
		super();
	}
	
	public ConnectionException(String message) {
		super(message);
	}
	
	public ConnectionException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ConnectionException(Throwable cause) {
		super(cause);
	}
}
