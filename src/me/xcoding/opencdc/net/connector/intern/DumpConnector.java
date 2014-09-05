package me.xcoding.opencdc.net.connector.intern;

import java.io.IOException;
import java.net.Socket;

import javax.net.SocketFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.xcoding.opencdc.binlog.EventContext;
import me.xcoding.opencdc.binlog.EventMaker;
import me.xcoding.opencdc.binlog.event.Event;
import me.xcoding.opencdc.binlog.event.EventHeader;
import me.xcoding.opencdc.binlog.parser.EventHeaderParser;
import me.xcoding.opencdc.mysql.protocol.BasicReader;
import me.xcoding.opencdc.mysql.protocol.CapabilityFlags;
import me.xcoding.opencdc.mysql.protocol.DumpBinLog;
import me.xcoding.opencdc.mysql.protocol.HandshakeResponse;
import me.xcoding.opencdc.mysql.protocol.HandshakeV10;
import me.xcoding.opencdc.mysql.protocol.ReadablePacket;
import me.xcoding.opencdc.mysql.protocol.WritablePcaket;
import me.xcoding.opencdc.net.ConnectorContext;
import me.xcoding.opencdc.net.connector.ConnectionException;
import me.xcoding.opencdc.net.connector.IDumpConnector;
import me.xcoding.opencdc.net.connector.SocketReader;
import me.xcoding.opencdc.net.connector.SocketWriter;
import me.xcoding.opencdc.net.packet.generic.GenericPacket;
import me.xcoding.opencdc.net.packet.generic.OKPacket;
import me.xcoding.opencdc.utils.MySQLUtils;

public class DumpConnector implements IDumpConnector {
	private static final Logger logger = LoggerFactory.getLogger(DumpConnector.class);
	private static final SocketFactory socketFactory = null;
	private static final int FF =  0x000000FF;
	private final ConnectorContext context;
	private HandshakeV10 v10 = null;
	
	public DumpConnector(ConnectorContext context) {
		this.context = context;
	}
	
	SocketReader reader = null;
	SocketWriter writer = null;
	
	@Override
	public void connection(String host, int port) throws ConnectionException {
		try {
			Socket socket = new Socket("localhost", 3306);// host, port);//socketFactory.createSocket(host, port);
			
			reader = new SocketReader(socket.getInputStream());
			writer = new SocketWriter(socket.getOutputStream());
			
			ReadablePacket p = reader.buildPacket();
			
			v10 = new HandshakeV10(p);
		} catch (IOException e) { // FIXME
			logger.error("", e);
		}
	}
	
	public static void main(String[] args) throws ConnectionException {
		DumpConnector connector = new DumpConnector(null);
		connector.connection("localhost", 3306);
		connector.login("root", "root");
		connector.dumpBinlog();
		
			try {
				connector.run();
			} catch (IOException e) {
				logger.error("", e);
			}
	}

	@Override 
	public void login(String username, String password) throws ConnectionException {
		HandshakeResponse response = new HandshakeResponse(writer);
		
		try {
			// FIXME
			byte[] pwd = MySQLUtils.password41OrLater(password.getBytes(), v10.auth_plugin_data_part);
			
			response.setUsername(username.getBytes())
				.setAuth_response(pwd)
				.setCapabilityFlags((v10.getCapabilities() & CapabilityFlags.CLIENT_SECURE_CONNECTION | CapabilityFlags.CLIENT_PROTOCOL_41 ))
				.setMax_packet_size(0)
				.setCharacter_size(v10.getCharacter_set())
				.flush();
				
			
			GenericPacket packet = reader.getResponsePacket();
		} catch (IOException e) {
			logger.error("", e);
		}
	}

	@Override
	public void dumpBinlog() throws ConnectionException {
		DumpBinLog binlog = new DumpBinLog(writer);
		try {
			binlog.setServerId(3456)
				.setBinLogPos(4)
				.setFilename("mysql-bin.000021")
				.flush();
			
			GenericPacket p = reader.getResponsePacket();
			System.out.println(p instanceof OKPacket);
		} catch (IOException e) {
			logger.error("", e);
		}
	}
	
	public void run() throws IOException {
		EventContext context = new EventContext();
		while(reader.hasNext()) {
			BasicReader packet = reader.buildPacket();
			packet.readFixedIntS1();
			
			EventHeader h = new EventHeaderParser().parser(context, reader);
			
			Event e = EventMaker.parser(context, h.typeCode, reader);
			System.out.println(e);
		}
	}

	public final DumpBinLog readPacket() {
		
		return null;
	}

	@Override
	public void write(ReadablePacket packet) throws ConnectionException {
		
	}

	@Override
	public ReadablePacket read() throws ConnectionException {
		return null;
	}

	@Override
	public void write(WritablePcaket packet) throws ConnectionException {
		// TODO Auto-generated method stub
		
	}
}
