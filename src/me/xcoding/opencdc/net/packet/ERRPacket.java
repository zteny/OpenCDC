package me.xcoding.opencdc.net.packet;

public class ERRPacket {
	public final int errorCode;
	public final int sqlStateMarker;
	public final String sqlState;
	public final String errorMessage;
	
	public static ERRPacket builder(byte[] buffer) {
		ReadablePacket p = new ReadablePacket();
		p.setBody(buffer);
		
		p.read();
		
		int errorCode = p.readVIntD2();
		int sqlStateMarker = p.read();
		String sqlState = p.readVariableLengthString(5);
		String errorMessage = p.readNulTerminatedString();
		
		return new ERRPacket(errorCode, sqlStateMarker, sqlState, errorMessage);
	}

	public ERRPacket(int errorCode, int sqlStateMarker, String sqlState,
			String errorMessage) {
		this.errorCode = errorCode;
		this.sqlStateMarker = sqlStateMarker;
		this.sqlState = sqlState;
		this.errorMessage = errorMessage;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("[errorCode=").append(errorCode)
			.append(", sqlStateMarker=").append((char)sqlStateMarker)
			.append(", sqlState=").append(sqlState)
			.append(", errorMessage=").append(errorMessage).append("]");
		
		return sb.toString();
	}
	
//	public String toExceptionMessage() {
//		StringBuffer sb = new StringBuffer();
//		// code,state, message
//		// errorCode=1236, sqlStateMarker=#, sqlState=HY000, 
//		// errorMessage=Slave can not handle replication events with the checksum that master is configured to log; 
//		// the first event 'mysql-bin.000001' at 4, the last event read from '.\mysql-bin.000002' at 120, 
//		// the last byte read from '.\mysql-bin.000002' at 120.
//		
//		return "";
//	}
}
