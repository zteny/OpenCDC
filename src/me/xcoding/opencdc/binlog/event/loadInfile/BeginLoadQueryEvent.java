package me.xcoding.opencdc.binlog.event.loadInfile;

import me.xcoding.opencdc.binlog.EventContext;
import me.xcoding.opencdc.binlog.event.Event;
import me.xcoding.opencdc.binlog.parser.EventParser;
import me.xcoding.opencdc.mysql.protocol.BasicReader;

// 0x11 @see http://dev.mysql.com/doc/internals/en/begin-load-query-event.html
public class BeginLoadQueryEvent extends Event implements EventParser {
	int fileId;
	String blockData;
	
	@Override
	public Event parser(EventContext context, BasicReader reader) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
