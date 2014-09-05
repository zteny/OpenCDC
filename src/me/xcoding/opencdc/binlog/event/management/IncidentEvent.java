package me.xcoding.opencdc.binlog.event.management;

import me.xcoding.opencdc.binlog.EventContext;
import me.xcoding.opencdc.binlog.event.Event;
import me.xcoding.opencdc.binlog.parser.EventParser;
import me.xcoding.opencdc.mysql.protocol.BasicReader;

/**
 * <b> Incident Event </b>
 * </br>
 * 
 * @author Teny Zh(zh.Teny.1@gmail.com)
 *
 */
public class IncidentEvent extends Event implements EventParser {
	private int type;
	private int mLength;
	private byte[] message;
	
	@Override
	public Event parser(EventContext context, BasicReader reader) {
		type = reader.readFixedIntT2();
		mLength = reader.readFixedIntT1();
		
		message = reader.readBytesVarLen(mLength);
		return this;
	}

	public int getType() {
		return type;
	}

	public int getmLength() {
		return mLength;
	}

	public byte[] getMessage() {
		return message;
	}

	public IncidentEvent setType(int type) {
		this.type = type;
		return this;
	}

	public IncidentEvent setmLength(int mLength) {
		this.mLength = mLength;
		return this;
	}

	public IncidentEvent setMessage(byte[] message) {
		this.message = message;
		return this;
	}
	
}
