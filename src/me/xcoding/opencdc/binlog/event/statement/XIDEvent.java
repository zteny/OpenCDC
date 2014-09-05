package me.xcoding.opencdc.binlog.event.statement;

import me.xcoding.opencdc.binlog.EventContext;
import me.xcoding.opencdc.binlog.event.StatementEvent;
import me.xcoding.opencdc.binlog.parser.EventParser;
import me.xcoding.opencdc.mysql.protocol.BasicReader;

/**
 * <b> XID Event </b>
 * </br></br>
 * Transaction ID for 2PC, written whenever a COMMIT is expected.
 * 
 * @author Teny Zh(zh.Teny.1@gmail.com)
 * @see http://dev.mysql.com/doc/internals/en/xid-event.html
 */
public class XIDEvent extends StatementEvent implements EventParser {
	private long xid;
	
	@Override
	public XIDEvent parser(EventContext context, BasicReader reader) {
		xid = reader.readFixedIntT8();
		return this;
	}

	public long getXid() {
		return xid;
	}
	
}
