package me.xcoding.opencdc.binlog.event.management;

import me.xcoding.opencdc.binlog.EventContext;
import me.xcoding.opencdc.binlog.event.Event;
import me.xcoding.opencdc.binlog.parser.EventParser;
import me.xcoding.opencdc.mysql.protocol.BasicReader;

/**
 * <b> Stop Event </b>
 * </br></br>
 * 
 * Nothing. A stop_event has no payload or post-header.
 * 
 * @author Teny Zh(zh.Teny.1@gmail.com)
 * @see http://dev.mysql.com/doc/internals/en/stop-event.html
 */
public class StopEvent  extends Event implements EventParser  {

	public StopEvent() {
	}
	
	@Override
	public Event parser(EventContext context, BasicReader reader) {
		return null;
	}
}
