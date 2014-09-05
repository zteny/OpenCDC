package me.xcoding.opencdc.binlog.event.statement;

import me.xcoding.opencdc.binlog.EventContext;
import me.xcoding.opencdc.binlog.event.StatementEvent;
import me.xcoding.opencdc.binlog.parser.EventParser;
import me.xcoding.opencdc.mysql.protocol.BasicReader;

/**
 * <b> User Var Event </b>
 * </br> </br>
 * 
 * @author Teny Zh(zh.Teny.1@gmail.com)
 * @see @see http://dev.mysql.com/doc/internals/en/user-var-event.html
 */
public class UserVarEvent extends StatementEvent implements EventParser {
	private int nameLength;
	private String name;
	private boolean isNull;
	private int type;
	private int charset;
	private int valueLength;
	private String value;
	private int flags;
	
	@Override
	public UserVarEvent parser(EventContext context, BasicReader reader) {
		nameLength = reader.readFixedIntT4();
		name = reader.readStringVarLen(nameLength);
		isNull = (reader.readFixedIntT1() == 1); // 1 if values is null;
		if(isNull) return this;
		
		type = reader.readFixedIntT1();
		charset = reader.readFixedIntT4();
		valueLength = reader.readFixedIntT4();
		value = reader.readStringVarLen(valueLength);
		
		byte[] b = reader.readBytesEOF();
		if(b != null && b.length > 0)
			flags =(b[0] & 0x00FF);
		return this;
	}

	public int getNameLength() {
		return nameLength;
	}

	public String getName() {
		return name;
	}

	public boolean isNull() {
		return isNull;
	}

	public int getType() {
		return type;
	}

	public int getCharset() {
		return charset;
	}

	public int getValueLength() {
		return valueLength;
	}

	public String getValue() {
		return value;
	}

	public int getFlags() {
		return flags;
	}

	public UserVarEvent setNameLength(int nameLength) {
		this.nameLength = nameLength;
		return this;
	}

	public UserVarEvent setName(String name) {
		this.name = name;
		return this;
	}

	public UserVarEvent setNull(boolean isNull) {
		this.isNull = isNull;
		return this;
	}

	public UserVarEvent setType(int type) {
		this.type = type;
		return this;
	}

	public UserVarEvent setCharset(int charset) {
		this.charset = charset;
		return this;
	}

	public UserVarEvent setValueLength(int valueLength) {
		this.valueLength = valueLength;
		return this;
	}

	public UserVarEvent setValue(String value) {
		this.value = value;
		return this;
	}

	public UserVarEvent setFlags(int flags) {
		this.flags = flags;
		return this;
	}
	
}
