package me.xcoding.opencdc.mysql.protocol;

import java.io.IOException;

public interface BasicReader {
	int FF = 0x000000FF;
	long _8F = 0x00000000FFFFFFFFl;
	long FFl = 0x00000000000000FFl;
	
	/**
	 *  Signed integer number, big end;
	 * @param length (should be less than 4bytes)
	 * @return Integer with signed
	 */
	int readVarLenIntS(int length);
	
	/**
	 *  Unsigned integer number, big end;
	 * @param length (should be less than 4bytes)
	 * @return Integer with Unsigned
	 */
	int readVarLenIntU(int length);
	
	/**
	 *  Signed long number, big end;
	 * @param length (should be less than 8bytes)
	 * @return Long with signed
	 */
	long readVarLenLongS(int length);
	
	/**
	 *  Unsigned long number, big end;
	 * @param length (should be less than 8bytes)
	 * @return Long with Unsigned
	 */
	long readVarLenLongU(int length);
	
	/**
	 * Protocol::FixedLengthInteger
	 * Type 1
	 */
	int readFixedIntT1();
	
	/**
	 * Protocol::FixedLengthInteger
	 * Type 1 with signed
	 */
	int readFixedIntS1();
	
	/**
	 * Protocol::FixedLengthInteger
	 * Type 2
	 */
	int readFixedIntT2();
	
	/**
	 * Protocol::FixedLengthInteger
	 * Type 2 with signed
	 */
	int readFixedIntS2();
	
	/**
	 * Protocol::FixedLengthInteger
	 * Type 3
	 */
	int readFixedIntT3();
	
	/**
	 * Protocol::FixedLengthInteger
	 * Type 3 with signed
	 */
	int readFixedIntS3();
	
	/**
	 * Protocol::FixedLengthInteger
	 * Type 4 
	 * NOTE : with signed
	 */
	int readFixedIntT4();
	
	/**
	 * Protocol::FixedLengthInteger
	 * Type 6
	 */
	long readFixedIntT6();
	
	/**
	 * Protocol::FixedLengthInteger
	 * Type 6 with signed
	 */
	long readFixedIntS6();
	
	/**
	 * Protocol::FixedLengthInteger
	 * Type 8
	 * 
	 * NOTE : with signed
	 */ 
	long readFixedIntT8();
	
	/**
	 * Protocol::LengthEncodedInteger
	 * @see http://dev.mysql.com/doc/internals/en/integer.html#packet-Protocol::LengthEncodedInteger
	 */
	long readLenEncInt();

	/**
	 * Protocol::LengthEncodedString
	 * It is a special case of Protocol::VariableLengthString
	 */ 
	String readStringLenEnc();
	
	/**
	 * Protocol::NulTerminatedString
	 * Strings that are terminated by a [00] byte.
	 */
	String readStringNull();
	
	/**
	 * Protocol::RestOfPacketString
	 * If a string is the last component of a packet, its length can be calculated 
	 * from the overall packet length minus the current position.
	 */
	String readStringEOF();
	
	/**
	 * Protocol::FixedLengthString
	 * Fixed-length strings have a known, hardcoded length.
	 */
	String readStringFixLen(int length);
	
	/**
	 * Protocol::VariableLengthString
	 * The length of the string is determined by another field or is calculated at runtime.
	 */ 
	String readStringVarLen(int length);

	byte[] readBytesEOF();
	
	byte[] readBytesNull();
	
	byte[] readBytesLenEnc();
	
	byte[] readBytesVarLen(int length);
	
	boolean hasMore() throws IOException;
	
	boolean hasNext();
	
}
