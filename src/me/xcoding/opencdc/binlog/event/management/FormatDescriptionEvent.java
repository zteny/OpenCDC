package me.xcoding.opencdc.binlog.event.management;

import me.xcoding.opencdc.binlog.EventContext;
import me.xcoding.opencdc.binlog.event.Event;
import me.xcoding.opencdc.binlog.parser.EventParser;
import me.xcoding.opencdc.mysql.protocol.BasicReader;

/**
 * 
 * @author Teny ZH(zh.Teny.1@gmail.com)
 * @see http://dev.mysql.com/doc/internals/en/format-description-event.html
 *
 */
// typecode = 15
public class FormatDescriptionEvent extends Event implements EventParser {
	private int binlogVersion;
	private String serverVersion;
	private long timestamp;
	
	// @see Event type header lengths by binlog version.
	private byte[] eventTypeHeaderLen;
	
	@Override
	public FormatDescriptionEvent parser(EventContext context, BasicReader reader) {
		binlogVersion = reader.readFixedIntT2();
		serverVersion = reader.readStringFixLen(50);
		timestamp = (reader.readFixedIntT4() & BasicReader._8F) * 1000l;
		reader.readFixedIntT1();
		eventTypeHeaderLen = reader.readBytesEOF();
		
		context.setEventType(eventTypeHeaderLen);
		return this;
	}

	public int getBinlogVersion() {
		return binlogVersion;
	}

	public String getServerVersion() {
		return serverVersion;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public byte[] getEventType() {
		return eventTypeHeaderLen;
	}
}
