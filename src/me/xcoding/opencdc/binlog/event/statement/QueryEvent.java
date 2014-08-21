package me.xcoding.opencdc.binlog.event.statement;

import me.xcoding.opencdc.binlog.EventContext;
import me.xcoding.opencdc.binlog.event.Event;
import me.xcoding.opencdc.binlog.parser.EventParser;
import me.xcoding.opencdc.mysql.protocol.BasicReader;

public class QueryEvent extends Event implements EventParser {
	private int slaveProxyId;
	private int executionTime;
	
	private int schemaLength;
	private int errorCode;
	
	private int statusVarsLength;
	private byte[] statusVars;
	
	private String schemaName;
	private String query;
	
	@Override
	public QueryEvent parser(EventContext context, BasicReader reader) {
		slaveProxyId = reader.readFixedIntT4();
		executionTime = reader.readFixedIntT4();
		
		schemaLength = reader.readFixedIntT1();
		errorCode = reader.readFixedIntT2();
		
//		if(context.getVersion() == 4) {
			statusVarsLength = reader.readFixedIntT2();
			statusVars = reader.readBytesVarLen(statusVarsLength);
//		}
		
		schemaName = reader.readStringVarLen(schemaLength);
		query = reader.readStringEOF();
		return this;
	}

	public int getSlaveProxyId() {
		return slaveProxyId;
	}

	public int getExecutionTime() {
		return executionTime;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public int getStatusVarsLength() {
		return statusVarsLength;
	}

	public byte[] getStatusVars() {
		return statusVars;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public String getQuery() {
		return query;
	}
	
}
