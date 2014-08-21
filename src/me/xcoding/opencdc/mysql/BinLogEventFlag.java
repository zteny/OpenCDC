package me.xcoding.opencdc.mysql;

/**
 * 
 * @author Teny ZH(zh.Teny.1@gmail.com)
 * @see http://dev.mysql.com/doc/internals/en/binlog-event-flag.html
 */
public interface BinLogEventFlag {                         
	public final int LOG_EVENT_BINLOG_IN_USE_F             = 0x0001;
	public final int LOG_EVENT_FORCED_ROTATE_F             = 0x0002;
	public final int LOG_EVENT_THREAD_SPECIFIC_F           = 0x0004;
	public final int LOG_EVENT_SUPPRESS_USE_F              = 0x0008;
	public final int LOG_EVENT_UPDATE_TABLE_MAP_VERSION_F  = 0x0010;
	public final int LOG_EVENT_ARTIFICIAL_F                = 0x0020;
	public final int LOG_EVENT_RELAY_LOG_F                 = 0x0040;
	public final int LOG_EVENT_IGNORABLE_F                 = 0x0080;
	public final int LOG_EVENT_NO_FILTER_F                 = 0x0100;
	public final int LOG_EVENT_MTS_ISOLATE_F               = 0x0200;
}                                               
