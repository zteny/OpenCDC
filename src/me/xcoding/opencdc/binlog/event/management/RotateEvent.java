package me.xcoding.opencdc.binlog.event.management;

import me.xcoding.opencdc.binlog.EventContext;
import me.xcoding.opencdc.binlog.event.Event;
import me.xcoding.opencdc.binlog.parser.EventParser;
import me.xcoding.opencdc.mysql.protocol.BasicReader;

/**
 * <b> Rotate Event </b>
 * </br></br>
 * 
 * The rotate event is added to the binlog as last event to tell 
 * the reader what binlog to request next.
 * 
 * @author Teny Zh(zh.Teny.1@gmail.com)
 * @see http://dev.mysql.com/doc/internals/en/rotate-event.html
 */
public class RotateEvent extends Event implements EventParser {
	private long position = 0l;
	private byte[] nextBinLog = null;
	
	@Override // FIXME  是这样吗？
	public Event parser(EventContext context, BasicReader reader) {
		if(context.getVersion() > 1) {
			position = reader.readFixedIntT8();
		}
		nextBinLog = reader.readBytesEOF();
		
		return this;
	}

	public long getPosition() {
		return position;
	}

	public byte[] getNextBinLog() {
		return nextBinLog;
	}

	public RotateEvent setPosition(long position) {
		this.position = position;
		return this;
	}

	public RotateEvent setNextBinLog(byte[] nextBinLog) {
		this.nextBinLog = nextBinLog;
		return this;
	}
	
}
