package me.xcoding.opencdc.mysql.protocol.column;

/**
 * <b> Protocol::Binary Value Parser Exception </b>
 * @author Teny Zh(zh.Teny.1@gmail.com)
 * 
 */
public class BinProtoValParserException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BinProtoValParserException() {
//		this("not support this type!");
		this("This enumeration value is only used internally and cannot exist in a binlog.");
	}
	
	public BinProtoValParserException(String message) {
		super(message);
	}
	
	public BinProtoValParserException(String message, Throwable cause) {
		super(message, cause);
	}
}
