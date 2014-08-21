package me.xcoding.opencdc.mysql.protocol.column;

import me.xcoding.opencdc.mysql.protocol.BasicReader;

/**
 * 
 * @author Teny Zh(zh.Teny.1@gmail.com)
 *
 */
public interface BinaryProtocolValue {

}

interface ValueParser {
	Object valueOf(int length, BasicReader reader);
} 

class MDecimal implements ValueParser {
	@Override
	public Object valueOf(int length, BasicReader reader) {
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
		// FIXME 
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
		return null;
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
		return null;
	}
}

class MTime implements ValueParser {
	@Override
	public Object valueOf(int length, BasicReader reader) {
		return null;
	}
}

class MDateTime implements ValueParser {
	@Override
	public Object valueOf(int length, BasicReader reader) {
		return null;
	}
}

class MYear implements ValueParser {
	@Override
	public Object valueOf(int length, BasicReader reader) {
		return null;
	}
}

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
		System.out.println(v);
		return reader.readStringVarLen(v);
	}
} 

class MBit implements ValueParser {
	@Override
	public Object valueOf(int length, BasicReader reader) {
		return null;
	}
}

class MTimestamp2 implements ValueParser {
	@Override
	public Object valueOf(int length, BasicReader reader) {
		// TODO Auto-generated method stub
		return null;
	}
}

class MDataTime2 implements ValueParser {
	@Override
	public Object valueOf(int length, BasicReader reader) {
		// TODO Auto-generated method stub
		return null;
	}
}

class MTime2 implements ValueParser {
	@Override
	public Object valueOf(int length, BasicReader reader) {
		// TODO Auto-generated method stub
		return null;
	}
}

class MNewDecimal implements ValueParser {
	@Override
	public Object valueOf(int length, BasicReader reader) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}
}

class MVarString implements ValueParser {
	@Override
	public Object valueOf(int length, BasicReader reader) {
		return null;
	}
}

class MString implements ValueParser {
	@Override
	public Object valueOf(int length, BasicReader reader) {
		return null;
	}
}

class MGeometry implements ValueParser {
	@Override
	public Object valueOf(int length, BasicReader reader) {
		return null;
	}
}