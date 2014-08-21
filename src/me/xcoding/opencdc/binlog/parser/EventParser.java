package me.xcoding.opencdc.binlog.parser;

import me.xcoding.opencdc.binlog.EventContext;
import me.xcoding.opencdc.binlog.event.Event;
import me.xcoding.opencdc.mysql.protocol.BasicReader;

public interface EventParser {

	Event parser(EventContext context, BasicReader reader);
}
