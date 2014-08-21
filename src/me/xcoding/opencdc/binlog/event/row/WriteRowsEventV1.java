package me.xcoding.opencdc.binlog.event.row;

import me.xcoding.opencdc.binlog.EventContext;
import me.xcoding.opencdc.binlog.event.Event;
import me.xcoding.opencdc.binlog.event.RowsEvent;
import me.xcoding.opencdc.binlog.parser.EventParser;
import me.xcoding.opencdc.mysql.protocol.BasicReader;

/**
 * 
 * include write_rows_eventV0 V1 V2
 * The format is pretty similar for all the events;
 * 
 * 
 * @author Teny ZH(zh.Teny.1@gmail.com)
 * @see http://dev.mysql.com/doc/internals/en/rows-event.html#write-rows-eventv2
 */
public class WriteRowsEventV1 extends RowsEvent implements EventParser {
//	header :
	long tableId;
	int flags;
	
	int version;
	int extraDataLength;
	String extraData;
	
//	body :
	int[] numberOfColumns;
	String[] columnsPresentBitmap1;
	String[] columnsPresentBitmap2;
	
//	rows : 
	
	@Override
	public WriteRowsEventV1 parser(EventContext context, BasicReader reader) {
		// TODO Auto-generated method stub
		return null;
	}
}
