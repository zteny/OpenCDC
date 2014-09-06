package me.xcoding.opencdc.binlog.event;

/**
 * <b> Binlog Event Header </b>
 * 
 * @author Teny ZH(zh.Teny.1@gmail.com)
 * @see http://dev.mysql.com/doc/internals/en/binlog-event-header.html
 */
public class EventHeader extends Event {
	public final long timestamp;
	public final int eventLength;
	public final int typeCode;
	public final int serverId;
	
	public final int flags;
	public final int nextPosition;
	
	public EventHeader(long timestamp, int typeCode, int serverId, int eventLenth, int nextPosition, int flags) {
		this.timestamp = timestamp;
		this.typeCode = typeCode;
		this.serverId = serverId;
		this.eventLength = eventLenth;
		this.nextPosition = nextPosition;
		this.flags = flags;
	}
}
