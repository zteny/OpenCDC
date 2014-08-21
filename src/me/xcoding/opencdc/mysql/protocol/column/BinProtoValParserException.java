package me.xcoding.opencdc.mysql.protocol.column;

@SuppressWarnings("serial")
public class BinProtoValParserException extends RuntimeException {
	
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
