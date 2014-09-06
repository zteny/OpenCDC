package me.xcoding.opencdc.binlog.event.row;

import me.xcoding.opencdc.binlog.EventContext;
import me.xcoding.opencdc.binlog.event.Event;
import me.xcoding.opencdc.binlog.event.RowsEvent;
import me.xcoding.opencdc.binlog.parser.EventParser;
import me.xcoding.opencdc.mysql.BinLogEventType;
import me.xcoding.opencdc.mysql.protocol.BasicReader;
import me.xcoding.opencdc.mysql.protocol.column.Column;
import me.xcoding.opencdc.mysql.protocol.column.ColumnDef;
import me.xcoding.opencdc.mysql.protocol.column.ColumnValueParser;

/**
 * 
 * include write_rows_eventV0
 * The format is pretty similar for all the events;
 * 
 * 
 * @author Teny ZH(zh.Teny.1@gmail.com)
 * @see http://dev.mysql.com/doc/internals/en/rows-event.html
 */
public class WriteRowsEventV0 extends RowsEvent implements EventParser {
	
	@Override
	public Event parser(EventContext context, BasicReader reader) {
		if(context.getHeaderLen(BinLogEventType.WRITE_ROWS_EVENTv1) == 6){
			tableId = reader.readFixedIntT4();
		} else {
			tableId = reader.readFixedIntT6();
		}
		
		flags = reader.readFixedIntT2();

		numberOfColumns = (int) (reader.readLenEncInt() + 7) / 8;
		columnsPresent = reader.readBytesVarLen(numberOfColumns);
		nullBitmap = reader.readBytesVarLen(numberOfColumns);
		
		columns2 = context.getAfterColumns().init(columnsPresent, nullBitmap);
		
		while(columns2.hasNext()) {
			ColumnDef def = columns2.next();
			Object v = null;
			if(!columns2.isNull()) {
				v = ColumnValueParser.valueOf(def.getType(), def.getMeta(), reader);
			}
			
			columns2.add(new Column(def.getType(), v));
		}
		return this;
	}
}
