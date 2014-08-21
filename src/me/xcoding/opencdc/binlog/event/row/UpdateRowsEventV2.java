package me.xcoding.opencdc.binlog.event.row;

import me.xcoding.opencdc.binlog.EventContext;
import me.xcoding.opencdc.binlog.event.RowsEvent;
import me.xcoding.opencdc.binlog.parser.EventParser;
import me.xcoding.opencdc.mysql.protocol.BasicReader;

public class UpdateRowsEventV2 extends RowsEvent implements EventParser {
	
	@Override
	public UpdateRowsEventV2 parser(EventContext context, BasicReader reader) {
		// TODO Auto-generated method stub
		return null;
	}
}
