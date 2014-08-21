package me.xcoding.opencdc.mysql.protocol.column;

import me.xcoding.opencdc.mysql.protocol.BasicReader;

public class ColumnValueParser {
	static final ValueParser[] parsers = new ValueParser[]{
		new MDecimal(),
		new MTiny(),
		new MShort(),
		new MLong(),
		new MFloat(),
		new MDouble(),
		new MNull(),
		new MTimestamp(),
		new MLongLong(),
		new MInt24(),
		new MDate(),
		new MTime(),
		new MDateTime(),
		new MYear(),
		new MNewDate(),
		new MVarchar(),
		new MBit(),
		new MTimestamp2(),
		new MTime2(),
		new MNewDecimal(),
		new MEnum(),
		new MSet(),
		new MTinyBlob(),
		new MMediumBlob(),
		new MLongBlob(),
		new MBlob(),
		new MVarString(),
		new MString(),
		new MGeometry()
	};
	
	public static void main(String[] args) {
		int i = 0;
		for(ValueParser vp : parsers) {
			System.out.println(i ++ +vp.getClass().getSimpleName());
		}
	}
	
	public static Object valueOf(int type, int length, BasicReader reader) {
		return parsers[type & 0x1F].valueOf(length, reader);
	}
}
