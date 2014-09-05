package me.xcoding.opencdc.mysql.protocol.column;

/**
 * Column MetaData Parser
 * @author Teny Zh(zh.Teny.1@gmail.com)
 *
 */
public abstract class MetaDataParser {
	public static final int MYSQL_TYPE_DECIMAL	 	= 0x00;
	public static final int MYSQL_TYPE_TINY			= 0x01;
	public static final int MYSQL_TYPE_SHORT		= 0x02;
	public static final int MYSQL_TYPE_LONG			= 0x03;
	public static final int MYSQL_TYPE_FLOAT		= 0x04;
	public static final int MYSQL_TYPE_DOUBLE		= 0x05;
	public static final int MYSQL_TYPE_NULL		 	= 0x06;
	public static final int MYSQL_TYPE_TIMESTAMP	= 0x07;
	public static final int MYSQL_TYPE_LONGLONG	 	= 0x08;
	public static final int MYSQL_TYPE_INT24		= 0x09;
	public static final int MYSQL_TYPE_DATE		 	= 0x0A;
	public static final int MYSQL_TYPE_TIME		 	= 0x0B;
	public static final int MYSQL_TYPE_DATETIME		= 0x0C;
	public static final int MYSQL_TYPE_YEAR		 	= 0x0D;
	public static final int MYSQL_TYPE_NEWDATE	 	= 0x0E;
	public static final int MYSQL_TYPE_VARCHAR	 	= 0x0F;
	public static final int MYSQL_TYPE_BIT		 	= 0x10;
	public static final int MYSQL_TYPE_TIMESTAMP2	= 0x11;
	public static final int MYSQL_TYPE_DATETIME2	= 0x12;
	public static final int MYSQL_TYPE_TIME2		= 0x13;
	                                                       
	public static final int MYSQL_TYPE_NEWDECIMAL	= 0x16;
	public static final int MYSQL_TYPE_ENUM			= 0x17;
	public static final int MYSQL_TYPE_SET			= 0x18;
	public static final int MYSQL_TYPE_TINY_BLOB	= 0x19;
	public static final int MYSQL_TYPE_MEDIUM_BLOB	= 0x1A;
	public static final int MYSQL_TYPE_LONG_BLOB	= 0x1B;
	public static final int MYSQL_TYPE_BLOB			= 0x1C;
	public static final int MYSQL_TYPE_VAR_STRING	= 0x1D;
	public static final int MYSQL_TYPE_STRING		= 0x1E;
	public static final int MYSQL_TYPE_GEOMETRY		= 0x1F;
	
	public static final int lengthOf[] = new int[0x20];

	static {
		lengthOf[MetaDataParser.MYSQL_TYPE_FLOAT]       = 1;
		lengthOf[MetaDataParser.MYSQL_TYPE_DOUBLE]      = 1;
		lengthOf[MetaDataParser.MYSQL_TYPE_TINY_BLOB]   = 1;
		lengthOf[MetaDataParser.MYSQL_TYPE_BLOB]        = 1;
		lengthOf[MetaDataParser.MYSQL_TYPE_MEDIUM_BLOB] = 1;
		lengthOf[MetaDataParser.MYSQL_TYPE_LONG_BLOB]   = 1;
		lengthOf[MetaDataParser.MYSQL_TYPE_TIME2]       = 1;
		lengthOf[MetaDataParser.MYSQL_TYPE_DATETIME2]   = 1;
		lengthOf[MetaDataParser.MYSQL_TYPE_TIMESTAMP2]  = 1;
		                                                
		lengthOf[MetaDataParser.MYSQL_TYPE_BIT]         = 2;
		lengthOf[MetaDataParser.MYSQL_TYPE_VARCHAR]     = 2;
		lengthOf[MetaDataParser.MYSQL_TYPE_NEWDECIMAL]	= 2;
		lengthOf[MetaDataParser.MYSQL_TYPE_SET]         = 2;
		lengthOf[MetaDataParser.MYSQL_TYPE_ENUM]        = 2;
		lengthOf[MetaDataParser.MYSQL_TYPE_STRING]      = 2;
	}
}

