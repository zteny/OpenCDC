package me.xcoding.opencdc.binlog.event.management;

import me.xcoding.opencdc.binlog.EventContext;
import me.xcoding.opencdc.binlog.event.Event;
import me.xcoding.opencdc.binlog.parser.EventParser;
import me.xcoding.opencdc.mysql.protocol.BasicReader;

// 0x04
public class StopEvent  extends Event implements EventParser  {

	public StopEvent() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Event parser(EventContext context, BasicReader reader) {
		// TODO Auto-generated method stub
		return null;
	}
}
