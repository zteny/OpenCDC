package me.xcoding.opencdc.mysql.protocol;

import java.io.IOException;

public interface BasicWriter {
	static final int FF = 0x000000FF;
	static final long _FF = 0x00000000FFFFFFFF;
	static final long FFl = 0x00000000000000FF;
	
	void writeFixLenIntT1(int value);
	
	void writeFixLenIntT2(int value);
	
	void writeFixLenIntT3(int value);
	
	void writeFixLenIntT4(int value);
	
	void writeFixLenIntT6(long value);
	
	void writeFixLenIntT8(long value);
	
	void writeBytes(byte[] value);
	
	void writeBytes(byte[] value, int length);
	
	void writeBytes(byte[] value, int offset, int length);
	
	void writeBytesVarLen(byte[] value);
	
	void writeBytesTermNul(byte[] value);
	
	void writeBytesLenEnc(byte[] value);
	
	void flush() throws IOException;
}
