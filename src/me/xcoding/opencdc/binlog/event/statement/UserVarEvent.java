package me.xcoding.opencdc.binlog.event.statement;

import me.xcoding.opencdc.binlog.EventContext;
import me.xcoding.opencdc.binlog.event.Event;
import me.xcoding.opencdc.binlog.parser.EventParser;
import me.xcoding.opencdc.mysql.protocol.BasicReader;

// @see http://dev.mysql.com/doc/internals/en/user-var-event.html
public class UserVarEvent extends Event implements EventParser {
	int nameLength;
	String name;
	boolean isNull;
	int type;
	int charset;
	int valueLength;
	String value;
	
	int flags;
	
	@Override
	public Event parser(EventContext context, BasicReader reader) {
		// TODO Auto-generated method stub
		return null;
	}
}
