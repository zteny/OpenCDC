package me.xcoding.opencdc.binlog.event.row;

import me.xcoding.opencdc.binlog.EventContext;
import me.xcoding.opencdc.binlog.event.RowsEvent;
import me.xcoding.opencdc.binlog.parser.EventParser;
import me.xcoding.opencdc.mysql.protocol.BasicReader;

public class UpdateRowsEventV1 extends RowsEvent implements EventParser {
	
	@Override
	public UpdateRowsEventV1 parser(EventContext context, BasicReader reader) {
		// TODO Auto-generated method stub
		return null;
	}
}
