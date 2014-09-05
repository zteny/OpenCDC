package me.xcoding.opencdc.binlog.event.statement;

import me.xcoding.opencdc.binlog.EventContext;
import me.xcoding.opencdc.binlog.event.StatementEvent;
import me.xcoding.opencdc.binlog.parser.EventParser;
import me.xcoding.opencdc.mysql.protocol.BasicReader;

/**
 * <b> Intvar Event </b>
 * </br></br>
 * Integer based session-variables.
 * 
 * </br> what is this? 
 * 
 * @author Teny ZH(zh.Teny.1@gmail.com)
 * @see http://dev.mysql.com/doc/internals/en/intvar-event.html
 */
public class IntvarEvent extends StatementEvent implements EventParser {
	private int type;
	private long value;
	
	@Override
	public IntvarEvent parser(EventContext context, BasicReader reader) {
		type = reader.readFixedIntT1();
		value = reader.readFixedIntT8();
		return this;
	}

	public int getType() {
		return type;
	}

	public long getValue() {
		return value;
	}

	public IntvarEvent setType(int type) {
		this.type = type;
		return this;
	}

	public IntvarEvent setValue(long value) {
		this.value = value;
		return this;
	}
	
}
