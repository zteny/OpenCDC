package me.xcoding.opencdc.binlog.event.row;

import me.xcoding.opencdc.binlog.EventContext;
import me.xcoding.opencdc.binlog.event.RowsEvent;
import me.xcoding.opencdc.binlog.parser.EventParser;
import me.xcoding.opencdc.mysql.BinLogEventType;
import me.xcoding.opencdc.mysql.protocol.BasicReader;
import me.xcoding.opencdc.mysql.protocol.column.Column;
import me.xcoding.opencdc.mysql.protocol.column.ColumnDef;
import me.xcoding.opencdc.mysql.protocol.column.ColumnValueParser;

public class UpdateRowsEventV1 extends RowsEvent implements EventParser {
	
	@Override
	public UpdateRowsEventV1 parser(EventContext context, BasicReader reader) {
		if(context.getHeaderLen(BinLogEventType.WRITE_ROWS_EVENTv1) == 6) {
			tableId = reader.readFixedIntT4();
		} else {
			tableId = reader.readFixedIntT6();
		}
		flags = reader.readFixedIntT2();
		
//		extraDataLength = reader.readFixedIntT2();
//		extraData = reader.readBytesVarLen(extraDataLength - 2);
		
		// FIXME Zh主观认为columns不会大于 Integer.MaxValue，Haha.
		numberOfColumns = (int) reader.readLenEncInt(); 
		columnsPresent = reader.readBytesVarLen((numberOfColumns + 7 ) / 8);
		columnsPresent2 = reader.readBytesVarLen((numberOfColumns + 7 ) / 8);
		
		nullBitmap = reader.readBytesVarLen((numberOfColumns + 7 ) / 8);
		columns1 = context.getAfterColumns().init(columnsPresent, nullBitmap);
		while(columns1.hasNext()) {
			ColumnDef def = columns1.next();
			Object v = null;
			if(!columns1.isNull()) {
				v = ColumnValueParser.valueOf(def.getType(), def.getMeta(), reader);
			}
			
			columns1.add(new Column(def.getType(), v));
		}
		
		columns2 = context.getBeforeColumns().init(columnsPresent2, nullBitmap);
		while(columns2.hasNext()) {
			ColumnDef def = columns2.next();
			Object v = null;
			if(!columns2.isNull()) {
				v = ColumnValueParser.valueOf(def.getType(), def.getMeta(), reader);
			}
			
			columns2.add(new Column(def.getType(), v));
		}
		return null;
	}
}
