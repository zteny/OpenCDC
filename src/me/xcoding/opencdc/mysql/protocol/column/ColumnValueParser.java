package me.xcoding.opencdc.mysql.protocol.column;

import me.xcoding.opencdc.mysql.protocol.BasicReader;

/**
 * All of Column Value Type Parsers.
 * @author Teny Zh(zh.Teny.1@gmail.com)
 *
 */
public class ColumnValueParser {
	static final ValueParser[] parsers = new ValueParser[0x0020];
	
	static {
		parsers[ColumnType.MYSQL_TYPE_NULL] = new MNull();
		
		parsers[ColumnType.MYSQL_TYPE_BIT] = new MBit();
		parsers[ColumnType.MYSQL_TYPE_BLOB] = new MBlob();
		parsers[ColumnType.MYSQL_TYPE_ENUM] = new MEnum();
		parsers[ColumnType.MYSQL_TYPE_TINY_BLOB] = new MTinyBlob();
		parsers[ColumnType.MYSQL_TYPE_MEDIUM_BLOB] = new MMediumBlob();
		
		parsers[ColumnType.MYSQL_TYPE_TINY] = new MTiny();
		parsers[ColumnType.MYSQL_TYPE_LONG] = new MLong();
		parsers[ColumnType.MYSQL_TYPE_INT24] = new MInt24();
		parsers[ColumnType.MYSQL_TYPE_SHORT] = new MShort();
		parsers[ColumnType.MYSQL_TYPE_LONGLONG] = new MLongLong();

		parsers[ColumnType.MYSQL_TYPE_DATE] = new MDate();
		parsers[ColumnType.MYSQL_TYPE_NEWDATE] = new MNewDate();
		
		parsers[ColumnType.MYSQL_TYPE_YEAR] = new MYear();
		
		parsers[ColumnType.MYSQL_TYPE_TIME] = new MTime();
		parsers[ColumnType.MYSQL_TYPE_TIME2] = new MTime2();
		
		parsers[ColumnType.MYSQL_TYPE_FLOAT] = new MFloat();
		parsers[ColumnType.MYSQL_TYPE_DOUBLE] = new MDouble();
		parsers[ColumnType.MYSQL_TYPE_DECIMAL] = new MDecimal();
		parsers[ColumnType.MYSQL_TYPE_NEWDECIMAL] = new MNewDecimal();
		
		parsers[ColumnType.MYSQL_TYPE_DATETIME] = new MDateTime();
		parsers[ColumnType.MYSQL_TYPE_DATETIME2] = new MDateTime2();

		parsers[ColumnType.MYSQL_TYPE_TIMESTAMP] = new MTimestamp();
		parsers[ColumnType.MYSQL_TYPE_TIMESTAMP2] = new MTimestamp2();
		
		parsers[ColumnType.MYSQL_TYPE_STRING] = new MString();
		parsers[ColumnType.MYSQL_TYPE_VARCHAR] = new MVarchar();
		parsers[ColumnType.MYSQL_TYPE_VAR_STRING] = new MVarString();

		parsers[ColumnType.MYSQL_TYPE_GEOMETRY] = new MGeometry();
	}
	
	public static Object valueOf(int type, int length, BasicReader reader) {
		assert type > 0x01F : "typecode must less than 32";
		return parsers[type & 0x01F].valueOf(length, reader);
	}
}
