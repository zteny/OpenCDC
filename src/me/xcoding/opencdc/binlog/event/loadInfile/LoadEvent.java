package me.xcoding.opencdc.binlog.event.loadInfile;

import me.xcoding.opencdc.binlog.EventContext;
import me.xcoding.opencdc.binlog.event.Event;
import me.xcoding.opencdc.binlog.parser.EventParser;
import me.xcoding.opencdc.mysql.protocol.BasicReader;

/**
 * 
 * @author Teny ZH(zh.Teny.1@gmail.com)
 * @see http://dev.mysql.com/doc/internals/en/load-event.html
 */
public class LoadEvent extends Event implements EventParser {

	@Override
	public Event parser(EventContext context, BasicReader reader) {
		// TODO Auto-generated method stub
		return null;
	}
}
