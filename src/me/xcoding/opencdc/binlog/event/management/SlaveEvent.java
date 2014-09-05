package me.xcoding.opencdc.binlog.event.management;

import me.xcoding.opencdc.binlog.EventContext;
import me.xcoding.opencdc.binlog.event.Event;
import me.xcoding.opencdc.binlog.parser.EventParser;
import me.xcoding.opencdc.mysql.protocol.BasicReader;

/**
 * <b> Ignored Cvents </b>
 * </br></br>
 *  
 * @author Teny ZH(zh.Teny.1@gmail.com)
 * @see http://dev.mysql.com/doc/internals/en/ignored-events.html#slave-event
 */
public class SlaveEvent extends Event implements EventParser {

	@Override
	public Event parser(EventContext context, BasicReader reader) {
		return null;
	}
}
