package me.xcoding.opencdc.binlog.event.row;

import me.xcoding.opencdc.binlog.EventContext;
import me.xcoding.opencdc.binlog.event.Event;
import me.xcoding.opencdc.binlog.parser.EventParser;
import me.xcoding.opencdc.mysql.protocol.BasicReader;
import me.xcoding.opencdc.mysql.protocol.column.ColumnList;

/**
 * 
 * @author Teny Zh(zh.Teny.1@gmail.com)
 *
 */
public class TableMapEvent extends Event implements EventParser {
	private long tableId;
	private int flags;
	private int schemaNameLength;
	private String schemaName;
	private int tableNameLength;
	private String tableName;
	// length-encoded integer type
	// @see http://dev.mysql.com/doc/internals/en/integer.html#packet-Protocol::LengthEncodedInteger
	private int columnCount;
	private byte[] columnTypeDef;
	private byte[] columnMetaDef;
	private byte[] nullBitmask;
	
	@Override
	public TableMapEvent parser(EventContext context, BasicReader reader) {
		// header-header :
		tableId = reader.readFixedIntT6();
		flags = reader.readFixedIntT2();
		
		// payload :
		schemaNameLength = reader.readFixedIntT1();
		schemaName = reader.readStringFixLen(schemaNameLength);
		reader.readFixedIntT1();
		
		tableNameLength = reader.readFixedIntT1();
		tableName = reader.readStringFixLen(tableNameLength);
		reader.readFixedIntT1();
		
		columnCount = (int) reader.readLenEncInt();
		columnTypeDef = reader.readBytesVarLen(columnCount);
		columnMetaDef = reader.readBytesLenEnc();
		
		nullBitmask = reader.readBytesVarLen((columnCount + 7) / 8);
		
		context.valueOf(
				new ColumnList(columnTypeDef, columnMetaDef, nullBitmask), 
				new ColumnList(columnTypeDef, columnMetaDef, nullBitmask)
			);
		return this;
	}

	public long getTableId() {
		return tableId;
	}

	public int getFlags() {
		return flags;
	}

	public int getSchemaNameLength() {
		return schemaNameLength;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public int getTableNameLength() {
		return tableNameLength;
	}

	public String getTableName() {
		return tableName;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public byte[] getColumnTypeDef() {
		return columnTypeDef;
	}

	public byte[] getColumnMetaDef() {
		return columnMetaDef;
	}

	public byte[] getNullBitmask() {
		return nullBitmask;
	}
	
}
