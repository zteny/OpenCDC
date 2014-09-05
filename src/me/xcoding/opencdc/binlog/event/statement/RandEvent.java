package me.xcoding.opencdc.binlog.event.statement;

import me.xcoding.opencdc.binlog.EventContext;
import me.xcoding.opencdc.binlog.event.StatementEvent;
import me.xcoding.opencdc.binlog.parser.EventParser;
import me.xcoding.opencdc.mysql.protocol.BasicReader;

/**
 * <b> Rand Event </b>
 * </br></br>
 * Internal state of the RAND() function.
 * 
 * @author Teny Zh(zh.Teny.1@gmail.com)
 * @see http://dev.mysql.com/doc/internals/en/rand-event.html
 */
public class RandEvent extends StatementEvent implements EventParser {
	private long seed1;
	private long seed2;
	
	@Override
	public RandEvent parser(EventContext context, BasicReader reader) {
		seed1 = reader.readFixedIntT8();
		seed2 = reader.readFixedIntT8();
		return this;
	}

	public long getSeed1() {
		return seed1;
	}

	public long getSeed2() {
		return seed2;
	}

	public RandEvent setSeed1(long seed1) {
		this.seed1 = seed1;
		return this;
	}

	public RandEvent setSeed2(long seed2) {
		this.seed2 = seed2;
		return this;
	}
	
}
