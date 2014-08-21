package me.xcoding.opencdc.binlog.parser;

import me.xcoding.opencdc.binlog.EventContext;
import me.xcoding.opencdc.binlog.Parser;
import me.xcoding.opencdc.binlog.event.EventHeader;
import me.xcoding.opencdc.mysql.protocol.BasicReader;
import me.xcoding.opencdc.net.packet.ReadablePacket;

/**
 * v4 event structure:
 * 
 * +=====================================+
 * | event  | timestamp         0 : 4    |
 * | header +----------------------------+
 * |        | type_code         4 : 1    |
 * |        +----------------------------+
 * |        | server_id         5 : 4    |
 * |        +----------------------------+
 * |        | event_length      9 : 4    |
 * |        +----------------------------+
 * |        | next_position    13 : 4    |
 * |        +----------------------------+
 * |        | flags            17 : 2    |
 * |        +----------------------------+
 * |        | extra_headers    19 : x-19 |
 * +=====================================+
 * | event  | fixed part        x : y    |
 * | data   +----------------------------+
 * |        | variable part              |
 * +=====================================+
 * 
 * header length = x bytes
 * data length = (event_length - x) bytes
 * fixed data length = y bytes variable data length = (event_length - (x + y)) bytes
 * x is given by the header_length field in the format description event (FDE). Currently,
 * x is 19, so the extra_headers field is empty.
 * y is specific to the event type, and is given by the FDE. 
 * The fixed-part length is the same for all events of a given type, 
 * but may vary for different event types.
 * 
 * The fixed part of the event data is sometimes referred to as the "post-header" part. 
 * The variable part is sometimes referred to as the "payload" or "body."
 * 
 * @author Teny ZH(zh.Teny.1@gmail.com)
 *
 */
public class EventHeaderParser implements EventParser {
	
	@Override
	public EventHeader parser(EventContext context, BasicReader packet) {
		EventHeader header = new EventHeader(
				packet.readFixedIntT4() & BasicReader._8F,
				packet.readFixedIntT1(),
				packet.readFixedIntT4(),
				packet.readFixedIntT4(),
				packet.readFixedIntT4(),
				packet.readFixedIntT2(),
				null
			);
		
		System.out.print("EventHeaderParser.parser()");
		System.out.println(header);
		
		return header;
	}
}
