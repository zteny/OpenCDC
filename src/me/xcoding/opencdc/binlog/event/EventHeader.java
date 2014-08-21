package me.xcoding.opencdc.binlog.event;

/**
 * 
 * @author Teny ZH(zh.Teny.1@gmail.com)
 * @
 */
public class EventHeader extends Event {
	// seconds since unix epoch
//	timestamp;
	
	// eventType // @see BinLogEventType
	
	// serverId // 
	
	// eventSize // size of the event (header, post-header, body)
	
	// logPos // position of the next event
	
	// flags // @see binlog event flags
	
	
	public long timestamp;
	public int eventLength;
	public int typeCode;
	public int serverId;
	
	public int flags;
	public int nextPosition;
	public byte[] extraHeaders;
	
	public EventHeader(long timestamp, int typeCode, int serverId, int eventLenth, int nextPosition, int flags, byte[] extraHeader) {
		this.timestamp = timestamp;
		this.typeCode = typeCode;
		this.serverId = serverId;
		this.eventLength = eventLenth;
		this.nextPosition = nextPosition;
		this.flags = flags;
		this.extraHeaders = extraHeader;
	}
}
