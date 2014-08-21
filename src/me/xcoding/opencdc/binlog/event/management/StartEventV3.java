package me.xcoding.opencdc.binlog.event.management;

import me.xcoding.opencdc.binlog.EventContext;
import me.xcoding.opencdc.binlog.event.Event;
import me.xcoding.opencdc.binlog.parser.EventParser;
import me.xcoding.opencdc.mysql.protocol.BasicReader;

public class StartEventV3 extends Event implements EventParser {
	int version;
	byte[] version2;
	long timestamp;
	int legnth;
	byte[] length2;
	
	@Override
	public Event parser(EventContext context, BasicReader reader) {
		// TODO Auto-generated method stub
		return null;
	}
}
