package me.xcoding.opencdc.mysql.protocol.column;

/**
 * Column Type
 * @author Teny Zh(zh.Teny.1@gmail.com)
 *
 */
public interface ColumnType {

	public static final int MYSQL_TYPE_DECIMAL     = 0x00;
	public static final int MYSQL_TYPE_TINY        = 0x01;
	public static final int MYSQL_TYPE_SHORT       = 0x02;
	public static final int MYSQL_TYPE_LONG        = 0x03;
	public static final int MYSQL_TYPE_FLOAT       = 0x04;
	public static final int MYSQL_TYPE_DOUBLE      = 0x05;
	public static final int MYSQL_TYPE_NULL        = 0x06;
	public static final int MYSQL_TYPE_TIMESTAMP   = 0x07;
	public static final int MYSQL_TYPE_LONGLONG    = 0x08;
	public static final int MYSQL_TYPE_INT24       = 0x09;
	public static final int MYSQL_TYPE_DATE        = 0x0a;
	public static final int MYSQL_TYPE_TIME        = 0x0b;
	public static final int MYSQL_TYPE_DATETIME    = 0x0c;
	public static final int MYSQL_TYPE_YEAR        = 0x0d;
	public static final int MYSQL_TYPE_NEWDATE     = 0x0e;
	public static final int MYSQL_TYPE_VARCHAR     = 0x0f;
	public static final int MYSQL_TYPE_BIT         = 0x10;
	public static final int MYSQL_TYPE_TIMESTAMP2  = 0x11;
	public static final int MYSQL_TYPE_DATETIME2   = 0x12;
	public static final int MYSQL_TYPE_TIME2       = 0x13;
	public static final int MYSQL_TYPE_NEWDECIMAL  = 0xf6 & 0x001F;
	public static final int MYSQL_TYPE_ENUM        = 0xf7 & 0x001F;
	public static final int MYSQL_TYPE_SET         = 0xf8 & 0x001F;
	public static final int MYSQL_TYPE_TINY_BLOB   = 0xf9 & 0x001F;
	public static final int MYSQL_TYPE_MEDIUM_BLOB = 0xfa & 0x001F;
	public static final int MYSQL_TYPE_LONG_BLOB   = 0xfb & 0x001F;
	public static final int MYSQL_TYPE_BLOB        = 0xfc & 0x001F;
	public static final int MYSQL_TYPE_VAR_STRING  = 0xfd & 0x001F;
	public static final int MYSQL_TYPE_STRING      = 0xfe & 0x001F;
	public static final int MYSQL_TYPE_GEOMETRY    = 0xFF & 0x001F;

	public static final String[] NAMES = new String[]{
		"decimal", "tiny", "short", "long", "float", "double", "null", "timestamp", "longlong", "int24", "date", "time", "datetime", "year", "newdate", "varchar", "bit", "timestamp2", "datetime2", "time2", "", "", "newdecimal", "enum", "set", "tiny_blob", "medium_blob", "long_blob", "blob", "var_string", "string"
	};
}
