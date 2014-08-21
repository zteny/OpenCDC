package me.xcoding.opencdc.binlog;

import me.xcoding.opencdc.binlog.event.AnonymousGtidEvent;
import me.xcoding.opencdc.binlog.event.Event;
import me.xcoding.opencdc.binlog.event.GtidEvent;
import me.xcoding.opencdc.binlog.event.IgnorableEvent;
import me.xcoding.opencdc.binlog.event.PreviousGtidsEvent;
import me.xcoding.opencdc.binlog.event.RowsQueryEvent;
import me.xcoding.opencdc.binlog.event.UnknownEvent;
import me.xcoding.opencdc.binlog.event.loadInfile.*;
import me.xcoding.opencdc.binlog.event.management.*;
import me.xcoding.opencdc.binlog.event.statement.*;
import me.xcoding.opencdc.binlog.event.row.*;
import me.xcoding.opencdc.binlog.parser.EventParser;
import me.xcoding.opencdc.mysql.protocol.BasicReader;

// Parser
public class EventMaker {
	private static final EventParser[] parsers = new EventParser[]{
		new UnknownEvent(),
		new StartEventV3(),
		new QueryEvent(),
		new StopEvent(),
		new RotateEvent(),
		new IntvarEvent(),
		new LoadEvent(),
		new SlaveEvent(),
		new CreateFileEvent(),
		new AppendBlockEvent(),
		new ExecLoadEvent(),
		new DeleteFileEvent(),
		new NewLoadEvent(),
		new RandEvent(),
		new UserVarEvent(),
		new FormatDescriptionEvent(),
		new XIDEvent(),
		new BeginLoadQueryEvent(),
		new ExecuteLoadQueryEvent(),
		new TableMapEvent(),
		new WriteRowsEventV0(),
		new UpdateRowsEventV0(),
		new DeleteRowsEventV0(),
		new WriteRowsEventV1(),
		new UpdateRowsEventV1(),
		new DeleteRowsEventV1(),
		new IncidentEvent(),
		new HeartbeatLogEvent(),
		new IgnorableEvent(),
		new RowsQueryEvent(),
		new WriteRowsEventV2(),
		new UpdateRowsEventV2(),
		new DeleteRowsEventV2(),
		new GtidEvent(),
		new AnonymousGtidEvent(),
		new PreviousGtidsEvent(),
	};
	
	public static void main(String[] args) {
		System.out.println(parsers.length);
	}
	
	public static Event parser(EventContext context, int typecode, BasicReader p) {
		if(parsers[typecode] == null)
			return null;
		return parsers[typecode].parser(context, p);
	}
	
	public EventMaker() {
		
	}
}

