package me.xcoding.opencdc.binlog;

import me.xcoding.opencdc.binlog.event.Event;
import me.xcoding.opencdc.binlog.event.EventHeader;
import me.xcoding.opencdc.net.packet.ReadablePacket;

public interface Parser {
	int FF = 0x000000FF;
	long FFl = 0x00000000000000FF;

	Event parser(EventHeader header, ReadablePacket packet);
}
