package me.xcoding.opencdc.mysql;

/**
 * +------------+---------------+
 * |	type 	|	meta-len	|
 * +------------+---------------+
 * | Type_String|		2		|
 * +------------+---------------+
 * 
 * @author Teny ZH(zh.Teny.1@gmail.com)
 *
 */
public interface ColumnType {
	public int MYSQL_TYPE_DECIMAL	= 0x00;
	public int MYSQL_TYPE_TINY		= 0x01;
	public int MYSQL_TYPE_SHORT		= 0x02;
	public int MYSQL_TYPE_LONG		= 0x03;
	public int MYSQL_TYPE_FLOAT		= 0x04;
	public int MYSQL_TYPE_DOUBLE	= 0x05;
	public int MYSQL_TYPE_NULL		= 0x06;
	public int MYSQL_TYPE_TIMESTAMP	= 0x07;
	public int MYSQL_TYPE_LONGLONG	= 0x08;
	public int MYSQL_TYPE_INT24		= 0x09;
	public int MYSQL_TYPE_DATE		= 0x0a;
	public int MYSQL_TYPE_TIME		= 0x0b;
	public int MYSQL_TYPE_DATETIME	= 0x0c;
	public int MYSQL_TYPE_YEAR		= 0x0d;
	public int MYSQL_TYPE_NEWDATE 	= 0x0e;
	public int MYSQL_TYPE_VARCHAR	= 0x0f;
	public int MYSQL_TYPE_BIT		= 0x10;
	public int MYSQL_TYPE_TIMESTAMP2= 0x11;
	public int MYSQL_TYPE_DATETIME2 = 0x12;
	public int MYSQL_TYPE_TIME2 	= 0x13;
	public int MYSQL_TYPE_NEWDECIMAL= 0xf6;
	public int MYSQL_TYPE_ENUM		= 0xf7;
	public int MYSQL_TYPE_SET		= 0xf8;
	public int MYSQL_TYPE_TINY_BLOB	= 0xf9;
	public int MYSQL_TYPE_MEDIUM_BLOB = 0xfa;
	public int MYSQL_TYPE_LONG_BLOB	= 0xfb;
	public int MYSQL_TYPE_BLOB		= 0xfc;
	public int MYSQL_TYPE_VAR_STRING= 0xfd;
	public int MYSQL_TYPE_STRING	= 0xfe;
	public int MYSQL_TYPE_GEOMETRY	= 0xff;

}
