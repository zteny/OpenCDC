package me.xcoding.opencdc.net.connector.intern;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.net.SocketFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.xcoding.opencdc.mysql.protocol.BasicReader;
import me.xcoding.opencdc.mysql.protocol.CapabilityFlags;
import me.xcoding.opencdc.mysql.protocol.HandshakeResponse;
import me.xcoding.opencdc.mysql.protocol.ReadablePacket;
import me.xcoding.opencdc.mysql.protocol.WritablePcaket;
import me.xcoding.opencdc.net.ConnectorContext;
import me.xcoding.opencdc.net.HandshakeV10;
import me.xcoding.opencdc.net.connector.ConnectionException;
import me.xcoding.opencdc.net.connector.IDumpConnector;
import me.xcoding.opencdc.net.connector.SocketReader;
import me.xcoding.opencdc.net.connector.SocketWriter;
import me.xcoding.opencdc.net.packet.DumpBinLog;
import me.xcoding.opencdc.utils.MySQLUtils;

public class DumpConnector implements IDumpConnector {
	private Logger logger = LoggerFactory.getLogger(DumpConnector.class);
	private static final SocketFactory socketFactory = null;
	private static final int FF =  0x000000FF;
	private final ConnectorContext context;
	private HandshakeV10 v10 = null;
	private InputStream in = null;
	private OutputStream out = null;
	
	public DumpConnector(ConnectorContext context) {
		this.context = context;
	}
	
	SocketReader reader = null;
	SocketWriter writer = null;
	
	@Override
	public void connection(String host, int port) throws ConnectionException {
		try {
			Socket socket = new Socket("localhost", 3306);// host, port);//socketFactory.createSocket(host, port);
			in = socket.getInputStream();
			out = socket.getOutputStream();
			
			reader = new SocketReader(in);
			writer = new SocketWriter(out);
			
			ReadablePacket p = reader.buildPacket();
			
			v10 = new HandshakeV10(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	byte[] header = new byte[] {4, -126, 0, 0}; // 00 00 82 04
	// CLIENT_SECURE_CONNECTION | CLIENT_PROTOCOL_41 | CLIENT_LONG_FLAG
	@Override 
	public void login(String username, String password) throws ConnectionException {
		byte[] pwd = MySQLUtils.password41OrLater(password.getBytes(), v10.auth_plugin_data_part);
		HandshakeResponse response = new HandshakeResponse();

		int v = CapabilityFlags.CLIENT_SECURE_CONNECTION | CapabilityFlags.CLIENT_PROTOCOL_41 | CapabilityFlags.CLIENT_LONG_FLAG;
//		response.setXx()
		
		try {
			// FIXME
//			writer.write(response.());
			writer.flush();
			
			ReadablePacket packet = reader.buildPacket();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void dumpBinlog() throws ConnectionException {
		
		
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
