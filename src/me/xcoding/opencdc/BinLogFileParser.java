package me.xcoding.opencdc;

import java.io.FileInputStream;
import java.io.InputStream;

import me.xcoding.opencdc.binlog.EventContext;
import me.xcoding.opencdc.binlog.EventMaker;
import me.xcoding.opencdc.binlog.event.Event;
import me.xcoding.opencdc.binlog.event.EventHeader;
import me.xcoding.opencdc.binlog.parser.EventHeaderParser;
import me.xcoding.opencdc.io.XReader;
import me.xcoding.opencdc.mysql.protocol.BasicReader;

public class BinLogFileParser {
	
	public static void main(String[] args) throws Exception {
		InputStream is = new FileInputStream("./mysql-bin.000012");
		EventContext context = new EventContext();
		XReader xReader = new XReader(is);
		
		while(xReader.hasMore()) {
			BasicReader packet = xReader.initPacket();
			EventHeader header = new EventHeaderParser().parser(context, packet);
			Event e = EventMaker.parser(context, header.typeCode, packet);
			System.out.println(e);
		}
	}
	
}
