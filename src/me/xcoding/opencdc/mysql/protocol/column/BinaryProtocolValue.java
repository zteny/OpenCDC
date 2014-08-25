package me.xcoding.opencdc.mysql.protocol.column;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;

import me.xcoding.opencdc.mysql.ColumnType;
import me.xcoding.opencdc.mysql.EventType;
import me.xcoding.opencdc.mysql.protocol.BasicReader;

/**
 * 
 * @author Teny Zh(zh.Teny.1@gmail.com)
 * @see http://www.mysqlsystems.com/mysql_source_analytics/html/d3/d06/classTable__map__log__event.html
 *
 */
public interface BinaryProtocolValue {

}

interface ValueParser {
	Object valueOf(int length, BasicReader reader);
} 

class MDecimal implements ValueParser {
	private static final int DECIMAL_BINARY_SIZE[] = {0, 1, 1, 2, 2, 3, 3, 4, 4, 4};
	@Override
	public Object valueOf(int length, BasicReader reader) {
		// A 1 byte unsigned int representing the precision,
		// followed by a 1 byte unsigned int representing the number of decimals.
		
		final int precision = length >> 0x08;
		final int decimals = length &  0xFF;
		
		// FIXME
		
		return null;
	}
}

class MTiny implements ValueParser {
	@Override
	public Object valueOf(int length, BasicReader reader) {
		return reader.readFixedIntS1();
	}
}

class MShort implements ValueParser {
	@Override
	public Object valueOf(int length, BasicReader reader) {
		return reader.readFixedIntS2();
	}
}

class MLong implements ValueParser {
	@Override
	public Object valueOf(int length, BasicReader reader) {
		return reader.readFixedIntT4();
	}
}

class MFloat implements ValueParser {
	@Override
	public Object valueOf(int length, BasicReader reader) {
		return Float.intBitsToFloat(reader.readFixedIntT4());
	}
}

class MDouble implements ValueParser {
	@Override
	public Object valueOf(int length, BasicReader reader) {
		long v = reader.readFixedIntT8();
		return Double.longBitsToDouble(v);
	}
}

class MNull implements ValueParser {
	@Override
	public Object valueOf(int length, BasicReader reader) {
		throw new BinProtoValParserException("not support this type!");
	}
}

class MTimestamp implements ValueParser {
	@Override
	public Object valueOf(int length, BasicReader reader) {
		return new Timestamp((reader.readFixedIntT4() & BasicReader._8F )* 1000);
	}
}

class MLongLong implements ValueParser {
	@Override
	public Object valueOf(int length, BasicReader reader) {
		return reader.readFixedIntT8();
	}
}

class MInt24 implements ValueParser {
	@Override
	public Object valueOf(int length, BasicReader reader) {
		return reader.readFixedIntS3();
	}
}

class MDate implements ValueParser {
	@Override
	public Object valueOf(int length, BasicReader reader) {
		// @Refer to Open Replicator & tungsten replicator
		int value = reader.readFixedIntT3();

		final int d = value % 32; value >>>= 5; 
		final int m = value % 16;
		final int y = value >> 4;
		final Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(y, m - 1, d);
		return new java.sql.Date(cal.getTimeInMillis());
	}
}

class MTime implements ValueParser {
	@Override
	public Object valueOf(int length, BasicReader reader) {
		// @refer to Open Replicator & tungsten replicator
		int time = reader.readFixedIntT3();
		
		final int s = (int)(time % 100); time /= 100;
		final int m = (int)(time % 100);
		final int h = (int)(time / 100);
		final Calendar c = Calendar.getInstance();
        c.set(1970, 0, 1, h, m, s);
        c.set(Calendar.MILLISECOND, 0);
        
        return new java.sql.Time(c.getTimeInMillis());
	}
}

class MDateTime implements ValueParser {
	@Override
	public Object valueOf(int length, BasicReader reader) {
		// @refer to Open Replicator & tungsten replicator
		long value = reader.readFixedIntT8();
		
		final int second = (int)(value % 100); value /= 100;
		final int minute = (int)(value % 100); value /= 100;
		final int hour = (int)(value % 100); value /= 100;
		final int day = (int)(value % 100); value /= 100;
		final int month = (int)(value % 100);
		final int year = (int)(value / 100);
		
		final Calendar c = Calendar.getInstance();
        c.set(year, month - 1, day, hour, minute, second);
        c.set(Calendar.MILLISECOND, 0);
        
        return c.getTime();
	}
}

class MYear implements ValueParser {
	@Override
	public Object valueOf(int length, BasicReader reader) {
		return 1900 + reader.readFixedIntT1();
	}
}

/**
 * This enumeration value is only used internally and cannot exist in a binlog.
 * @author Teny Zh(zh.Teny.1@gmail.com)
 *
 */
class MNewDate implements ValueParser {
	@Override
	public Object valueOf(int length, BasicReader reader) {
		throw new BinProtoValParserException();
	}
}

class MVarchar implements ValueParser {
	@Override
	public Object valueOf(int length, BasicReader reader) {
		int v = length > 0x0FF ? reader.readFixedIntT2() : reader.readFixedIntT1();
		return reader.readStringVarLen(v);
	}
} 

class MBit implements ValueParser {
	@Override
	public Object valueOf(int length, BasicReader reader) {
		// FIXME
		return null;
	}
}

class MTimestamp2 implements ValueParser {
	@Override 
	public Object valueOf(int length, BasicReader reader) {
		final long time = reader.readVarLenLongU(4);
		final int nanos = reader.readVarLenIntU((length + 1) /2);

		Timestamp ts = new Timestamp(time);
		ts.setNanos(nanos);
		
		return ts;
	}
}

class MDateTime2 implements ValueParser {
	@Override 
	public Object valueOf(int length, BasicReader reader) {
		// @refer to Open Replicator & tungsten replicator
		final long time = reader.readVarLenLongU(5);
		final int nanos = reader.readVarLenIntU((length + 1) /2);

		final long x = (time >> 22) & 0x1FFFFL;
		final int year = (int)(x / 13);
		final int month = (int)(x % 13);
		final int day = ((int)(time >> 17)) & 0x1F;
		final int hour = ((int)(time >> 12)) & 0x1F;
		final int minute = ((int)(time >> 6)) & 0x3F;
		final int second = ((int)(time >> 0)) & 0x3F;
		
		final Calendar c = Calendar.getInstance();
        c.set(year, month - 1, day, hour, minute, second);
        c.set(Calendar.MILLISECOND, 0);
        final long millis = c.getTimeInMillis();
       
        return new java.util.Date(millis + (nanos / 1000000));
	}
}

class MTime2 implements ValueParser {
	@Override 
	public Object valueOf(int length, BasicReader reader) {
		// @refer to Open Replicator & tungsten replicator
		final int time = reader.readVarLenIntU(3);
		final int nanos = reader.readVarLenIntU((length + 1) / 2);
		
		final int h = (time >> 12) & 0x3FF;
		final int m = (time >> 6) & 0x3F;
		final int s = (time >> 0) & 0x3F;
		
		final Calendar c = Calendar.getInstance();
        c.set(1970, 0, 1, h, m, s);
        c.set(Calendar.MILLISECOND, 0);
        final long millis = c.getTimeInMillis();
        
        return new java.sql.Time(millis + (nanos / 1000000));
	}
}

class MNewDecimal implements ValueParser {
	private static final int DIG_PER_DEC1  = 0x09;
	private static final int dig2bytes[]={0, 1, 1, 2, 2, 3, 3, 4, 4, 4};
	
	@Override
	public Object valueOf(int length, BasicReader reader) {
		// TODO FIXME
		return null;
	}
	
	/**
	 * @see mysql-5.1.60/strings/decimal.c - bin2decimal()
	 */
	public BigDecimal getDecimal(int precision, int scale) {
		int intg = precision - scale,
	      intg0 = intg / DIG_PER_DEC1, frac0 = scale / DIG_PER_DEC1,
	      intg0x = intg - intg0 * DIG_PER_DEC1, frac0x = scale - frac0 * DIG_PER_DEC1,
	      intg1 = intg0 + (intg0x > 0 ? 1 : 0), frac1 = frac0 + (frac0x > 0 ? 1 : 0);
		
//		int bin_size = decimal_bin_size(precision, scale);
		int bin_size = intg0 << 2 + dig2bytes[intg0x]+ frac0 << 2 + dig2bytes[frac0x];
		byte[] value = new byte[bin_size]; // reader.readBytesVarLen(bin_size);
		value[0] ^= 0x80;
		
		int error = ERROR.E_DEC_OK;
		
		final int mask = ((value[0] & 0x80) == 0x80) ? 0 : -1;
		do {
			if(intg1 + frac1 > value.length) {
				if(intg1 > value.length) {
					intg1 = value.length;
					frac1 = 0;
					error = ERROR.E_DEC_OVERFLOW;
				} else {
					frac1 = value.length - intg1;
					error = ERROR.E_DEC_TRUNCATED;
				}
			} else {
				error = ERROR.E_DEC_OVERFLOW;
			}
		} while(false);
		
		if(error != 0) {
			if(intg1 < intg0 + (intg0x > 0 ? 1 : 0)) {
				int from = dig2bytes[intg0x] + ((intg0 - intg1) << 2);
				
				frac0 = frac0x = intg0x = 0;
			    intg0 = intg1;
			} else {
				frac0x = 0;
				frac0x = frac1;
			}
		}
		
		return null;
	}
	
	public int decimal_bin_size(int precision, int scale) {
		int intg=precision-scale,
	      intg0=intg/DIG_PER_DEC1, frac0=scale/DIG_PER_DEC1,
	      intg0x=intg-intg0*DIG_PER_DEC1, frac0x=scale-frac0*DIG_PER_DEC1;

		return intg0 << 2 + dig2bytes[intg0x]+ frac0 << 2 + dig2bytes[frac0x];
	}
	
	interface ERROR {
		public static final int E_DEC_OK            =   0;
		public static final int E_DEC_TRUNCATED     =   1;
		public static final int E_DEC_OVERFLOW      =   2;
		public static final int E_DEC_DIV_ZERO      =   4;
		public static final int E_DEC_BAD_NUM       =   8;
		public static final int E_DEC_OOM           =  16;

		public static final int E_DEC_ERROR         =  31;
		public static final int E_DEC_FATAL_ERROR   =  30;
	}
	
	class decimal_t {
		
	}
}

/**
 * This enumeration value is only used internally and cannot exist in a binlog.
 * @author Teny Zh(zh.Teny.1@gmail.com)
 *
 */
class MEnum implements ValueParser {
	@Override
	public Object valueOf(int length, BasicReader reader) {
		// FIXME DBSync 
		throw new BinProtoValParserException();
	}
}

/**
 * This enumeration value is only used internally and cannot exist in a binlog.
 * @author Teny Zh(zh.Teny.1@gmail.com)
 *
 */
class MSet implements ValueParser {
	@Override
	public Object valueOf(int length, BasicReader reader) {
		throw new BinProtoValParserException();
	}
}

/**
 * This enumeration value is only used internally and cannot exist in a binlog.
 * @author Teny Zh(zh.Teny.1@gmail.com)
 *
 */
class MTinyBlob implements ValueParser {
	@Override
	public Object valueOf(int length, BasicReader reader) {
		throw new BinProtoValParserException();
	}
}

/**
 * This enumeration value is only used internally and cannot exist in a binlog.
 * @author Teny Zh(zh.Teny.1@gmail.com)
 *
 */
class MMediumBlob implements ValueParser {
	@Override
	public Object valueOf(int length, BasicReader reader) {
		throw new BinProtoValParserException();
	}
}

/**
 * This enumeration value is only used internally and cannot exist in a binlog.
 * @author Teny Zh(zh.Teny.1@gmail.com)
 *
 */
class MLongBlob implements ValueParser {
	@Override
	public Object valueOf(int length, BasicReader reader) {
		throw new BinProtoValParserException();
	}
}

class MBlob implements ValueParser {
	@Override
	public Object valueOf(int length, BasicReader reader) {
		int v = reader.readVarLenIntU(length);
		
		if(v < 0) 
			throw new BinProtoValParserException("this length less than 0!");
		
		return reader.readBytesVarLen(v);
	}
}

class MVarString implements ValueParser {
	@Override
	public Object valueOf(int length, BasicReader reader) {
		// FIXME fixed issue #66 ,binary类型在binlog中为var_string
		int v = length > 256 ? (reader.readFixedIntT2()) : (reader.readFixedIntT2());
		
		return reader.readStringVarLen(v);
	}
}

class MString implements ValueParser {
	@Override
	public Object valueOf(int length, BasicReader reader) {
		// fix #37426 
		// @see http://bugs.mysql.com/bug.php?id=37426
		int type = 0xFE;
		
		/*
		 * 1 	byte0 : type
		 * 2	byte1 : length
		 */
		final int meta0 = length & 0xFF;
		final int meta1 = (length >> 8 ) & 0xFF;
		
		if(meta1 > 0) {
			if ((meta0 & 0x30) != 0x30) { // a long CHAR() field: see #37426
				type = meta0 | 0x30;
				length = meta1 | (((meta0 & 0x30) ^ 0x30) << 4); 
			} else {
				switch (meta0) {
				case ColumnType.MYSQL_TYPE_SET:
				case ColumnType.MYSQL_TYPE_ENUM: {
					type = meta0;
					length = meta1;
					break;
				}
				case ColumnType.MYSQL_TYPE_STRING: {
					if(length > 256) { // FIXME
						throw new BinProtoValParserException("length greater than 256!");
					}
					length = length > 256 ? reader.readFixedIntT2() : reader.readFixedIntT1();
					
					return reader.readStringVarLen(length);
				}
				default:
					throw new RuntimeException("assertion failed, unknown column type: " + type);
				}
			}
		}
		
		return ColumnValueParser.valueOf(type, (meta0 << 8) | meta1, reader);
	}
}

class MGeometry implements ValueParser {
	@Override
	public Object valueOf(int length, BasicReader reader) {
		// FIXME TODO
		return null;
	}
}