package me.xcoding.opencdc.binlog.event.statement;

import me.xcoding.opencdc.binlog.EventContext;
import me.xcoding.opencdc.binlog.event.Event;
import me.xcoding.opencdc.binlog.parser.EventParser;
import me.xcoding.opencdc.mysql.protocol.BasicReader;

/**
 * 
	0x00
	INVALID_INT_EVENT
	0x01
	LAST_INSERT_ID_EVENT
	0x02
	INSERT_ID_EVENT
 * 
 * @author Teny ZH(zh.Teny.1@gmail.com)
 *
 */

// 0x05
public class IntvarEvent extends Event implements EventParser {
	int type;
	long value;
	
	@Override
	public Event parser(EventContext context, BasicReader reader) {
		// TODO Auto-generated method stub
		return null;
	}
}
