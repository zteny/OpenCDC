package me.xcoding.opencdc.binlog.event.statement;

import me.xcoding.opencdc.binlog.EventContext;
import me.xcoding.opencdc.binlog.event.StatementEvent;
import me.xcoding.opencdc.binlog.parser.EventParser;
import me.xcoding.opencdc.mysql.protocol.BasicReader;

/**
 * <b> Query Event </b>
 * </br></br>
 * The query event is used to send text querys right the binlog.
 * 
 * @author Teny Zh(zh.Teny.1@gmail.com)
 * @see http://dev.mysql.com/doc/internals/en/query-event.html
 */
public class QueryEvent extends StatementEvent implements EventParser {
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
		
		if(context.getVersion() >= 4) {
			statusVarsLength = reader.readFixedIntT2();
			statusVars = reader.readBytesVarLen(statusVarsLength);
		}
		
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
