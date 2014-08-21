package me.xcoding.opencdc.binlog.event.statement;

import me.xcoding.opencdc.binlog.EventContext;
import me.xcoding.opencdc.binlog.event.Event;
import me.xcoding.opencdc.binlog.parser.EventParser;
import me.xcoding.opencdc.mysql.protocol.BasicReader;

public class XIDEvent extends Event implements EventParser {
	long xid;
	
	@Override
	public XIDEvent parser(EventContext context, BasicReader reader) {
		xid = reader.readFixedIntT8();
		return this;
	}

	public long getXid() {
		return xid;
	}
	
}
