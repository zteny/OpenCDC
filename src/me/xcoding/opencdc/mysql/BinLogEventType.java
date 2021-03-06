package me.xcoding.opencdc.mysql;

/**
 * 
 * @author Teny ZH(zh.Teny.1@gmail.com)
 * @see http://dev.mysql.com/doc/internals/en/event-classes-and-types.html
 * @see http://dev.mysql.com/doc/internals/en/event-meanings.html
 */
public interface BinLogEventType {
	public final int UNKNOWN_EVENT = 0x000;
	public final int START_EVENT_V3 = 0x0001;
	public final int QUERY_EVENT = 0x0002;
	public final int STOP_EVENT = 0x0003;
	public final int ROTATE_EVENT = 0x0004;
	public final int INTVAR_EVENT = 0x0005;
	public final int LOAD_EVENT = 0x0006;
	public final int SLAVE_EVENT = 0x0007;
	public final int CREATE_FILE_EVENT = 0x0008;
	public final int APPEND_BLOCK_EVENT = 0x00009;
	public final int EXEC_LOAD_EVENT = 0x000A;
	public final int DELETE_FILE_EVENT = 0x000B;
	public final int NEW_LOAD_EVENT = 0x000C;
	public final int RAND_EVENT = 0x000D;
	public final int USER_VAR_EVENT = 0x000E;
	public final int FORMAT_DESCRIPTION_EVENT = 0x000F;
	public final int XID_EVENT = 0x0010;
	public final int BEGIN_LOAD_QUERY_EVENT = 0x0011;
	public final int EXECUTE_LOAD_QUERY_EVENT = 0x0012;
	public final int TABLE_MAP_EVENT = 0x0013;
	public final int WRITE_ROWS_EVENTv0 = 0x0014;
	public final int UPDATE_ROWS_EVENTv0 = 0x0015;
	public final int DELETE_ROWS_EVENTv0 = 0x0016;
	public final int WRITE_ROWS_EVENTv1 = 0x00017;
	public final int UPDATE_ROWS_EVENTv1 = 0x0018;
	public final int DELETE_ROWS_EVENTv1 = 0x0019;
	public final int INCIDENT_EVENT = 0x001A;
	public final int HEARTBEAT_EVENT = 0x001B;
	public final int IGNORABLE_EVENT = 0x001C;
	public final int ROWS_QUERY_EVENT = 0x001D;
	public final int WRITE_ROWS_EVENTv2 = 0x001E;
	public final int UPDATE_ROWS_EVENTv2 = 0x001F;
	public final int DELETE_ROWS_EVENTv2 = 0x0020;
	public final int GTID_EVENT = 0x0021;
	public final int ANONYMOUS_GTID_EVENT = 0x0022;
	public final int PREVIOUS_GTIDS_EVENT = 0x0023;
}
