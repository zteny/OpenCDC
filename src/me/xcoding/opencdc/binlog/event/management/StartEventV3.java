package me.xcoding.opencdc.binlog.event.management;

import me.xcoding.opencdc.binlog.EventContext;
import me.xcoding.opencdc.binlog.event.Event;
import me.xcoding.opencdc.binlog.parser.EventParser;
import me.xcoding.opencdc.mysql.protocol.BasicReader;

/**
 * <b> Start Event Version 3 </b>
 * </br></br>
 * 
 * A start event is the first event of a binlog for binlog-version 1 to 3.
 * 
 * @author Teny Zh(zh.Teny.1@gmail.com)
 * @see http://dev.mysql.com/doc/internals/en/start-event-v3.html
 */
public class StartEventV3 extends Event implements EventParser {
	private int version;
	private byte[] versionName;
	private long timestamp;
	
	@Override
	public Event parser(EventContext context, BasicReader reader) {
		version = reader.readFixedIntT2();
		versionName = reader.readBytesVarLen(50);
		timestamp = reader.readFixedIntT4() * 1000l;
		return this;
	}

	public int getVersion() {
		return version;
	}

	public byte[] getVersionName() {
		return versionName;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public StartEventV3 setVersion(int version) {
		this.version = version;
		return this;
	}

	public StartEventV3 setVersionName(byte[] versionName) {
		this.versionName = versionName;
		return this;
	}

	public StartEventV3 setTimestamp(long timestamp) {
		this.timestamp = timestamp;
		return this;
	}

}
