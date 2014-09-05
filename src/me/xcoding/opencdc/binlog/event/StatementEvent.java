package me.xcoding.opencdc.binlog.event;

/**
 * <b> Statement Based Replication Events </b>
 * </br> </br>
 * Statement Based Replication or SBR sends the SQL queries a client sent
 * to the master AS IS to the slave. It needs extra events to mimic the 
 * client connection's state on the slave side.
 * 
 * @author Teny Zh(zh.Teny.1@gmail.com)
 * @see http://dev.mysql.com/doc/internals/en/binlog-event.html
 */
public abstract class StatementEvent extends Event {
	// QueryEvent
	// IntvarEvent
	// RandEvent
	// UserVarEvent
	// XIDEvent
	
}
