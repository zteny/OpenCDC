package me.xcoding.opencdc.mysql;

/**
 * 
 * @author Teny ZH(zh.Teny.1@gmail.com)
 * @see http://dev.mysql.com/doc/internals/en/event-classes-and-types.html
 * @see http://dev.mysql.com/doc/internals/en/event-meanings.html
 */
public interface EventType {
	int UNKNOWN_EVENT= 0;
	int START_EVENT_V3= 1;
	int QUERY_EVENT= 2;
	int STOP_EVENT= 3;
	int ROTATE_EVENT= 4;
	int INTVAR_EVENT= 5;
	int LOAD_EVENT= 6;
	int SLAVE_EVENT= 7;
	int CREATE_FILE_EVENT= 8;
	int APPEND_BLOCK_EVENT= 9;
	int EXEC_LOAD_EVENT= 10;
	int DELETE_FILE_EVENT= 11;
	int NEW_LOAD_EVENT= 12;
	int RAND_EVENT= 13;
	int USER_VAR_EVENT= 14;
	int FORMAT_DESCRIPTION_EVENT= 15;
	int XID_EVENT= 16;
	int BEGIN_LOAD_QUERY_EVENT= 17;
	int EXECUTE_LOAD_QUERY_EVENT= 18;
	int TABLE_MAP_EVENT = 19;
	int PRE_GA_WRITE_ROWS_EVENT = 20;
	int PRE_GA_UPDATE_ROWS_EVENT = 21;
	int PRE_GA_DELETE_ROWS_EVENT = 22;
	int WRITE_ROWS_EVENT = 23;
	int UPDATE_ROWS_EVENT = 24;
	int DELETE_ROWS_EVENT = 25;
	int INCIDENT_EVENT= 26;
	int HEARTBEAT_LOG_EVENT= 27;
}
