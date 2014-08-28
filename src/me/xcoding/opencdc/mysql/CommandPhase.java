package me.xcoding.opencdc.mysql;

public interface CommandPhase {
	public static final int COM_SLEEP                 = 0x0000;
	public static final int COM_QUIT                  = 0x0001;
	public static final int COM_INIT_DB               = 0x0002;
	public static final int COM_QUERY                 = 0x0003;
	public static final int COM_FIELD_LIST            = 0x0004;
	public static final int COM_CREATE_DB             = 0x0005;
	public static final int COM_DROP_DB               = 0x0006;
	public static final int COM_REFRESH               = 0x0007;
	public static final int COM_SHUTDOWN              = 0x0008;
	public static final int COM_STATISTICS            = 0x0009;
	public static final int COM_PROCESS_INFO          = 0x000a;
	public static final int COM_CONNECT               = 0x000b;
	public static final int COM_PROCESS_KILL          = 0x000c;
	public static final int COM_DEBUG                 = 0x000d;
	public static final int COM_PING                  = 0x000e;
	public static final int COM_TIME                  = 0x000f;
	public static final int COM_DELAYED_INSERT        = 0x0010;
	public static final int COM_CHANGE_USER           = 0x0011;
	public static final int COM_BINLOG_DUMP           = 0x0012;
	public static final int COM_TABLE_DUMP            = 0x0013;
	public static final int COM_CONNECT_OUT           = 0x0014;
	public static final int COM_REGISTER_SLAVE        = 0x0015;
	public static final int COM_STMT_PREPARE          = 0x0016;
	public static final int COM_STMT_EXECUTE          = 0x0017;
	public static final int COM_STMT_SEND_LONG_DATA   = 0x0018;
	public static final int COM_STMT_CLOSE            = 0x0019;
	public static final int COM_STMT_RESET            = 0x001a;
	public static final int COM_SET_OPTION            = 0x001b;
	public static final int COM_STMT_FETCH            = 0x001c;
	public static final int COM_DAEMON                = 0x001d;
	public static final int COM_BINLOG_DUMP_GTID      = 0x001e;
	public static final int COM_RESET_CONNECTION      = 0x001f;
}
