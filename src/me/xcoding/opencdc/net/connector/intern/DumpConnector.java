package me.xcoding.opencdc.net.connector.intern;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.net.SocketFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.or.common.util.MySQLConstants;
import com.google.code.or.common.util.MySQLUtils;

import me.xcoding.opencdc.net.ConnectorContext;
import me.xcoding.opencdc.net.HandshakeV10;
import me.xcoding.opencdc.net.connector.ConnectionException;
import me.xcoding.opencdc.net.connector.IDumpConnector;
import me.xcoding.opencdc.net.packet.BinlogPacket;
import me.xcoding.opencdc.net.packet.Packetable;
import me.xcoding.opencdc.net.packet.ReadablePacket;
import me.xcoding.opencdc.net.packet.WritablePacket;

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
	
	@Override
	public void connection(String host, int port) throws ConnectionException {
		try {
			Socket socket = new Socket("localhost", 3306);// host, port);//socketFactory.createSocket(host, port);
			in = socket.getInputStream();
			out = socket.getOutputStream();
			
			int v = in.read() | (in.read() & 0xFF) << 8 | (in.read() & 0xFF) << 16;
			in.read(); 
			byte[] b = new byte[v];
			in.read(b, 0, v);
			
			v10 = new HandshakeV10(b);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	byte[] header = new byte[] {4, -126, 0, 0};
	@Override
	public void login(String username, String password) throws ConnectionException {
		byte[] pwd = MySQLUtils.password41OrLater(password.getBytes(), v10.auth_plugin_data_part);
		WritablePacket packet = new WritablePacket();

		int v = MySQLConstants.CLIENT_SECURE_CONNECTION | MySQLConstants.CLIENT_PROTOCOL_41 | MySQLConstants.CLIENT_LONG_FLAG;
		
		packet.setSequence(1);
		// capability_flags : 4
		packet.writeInt(v);
		// max_packet_size : 4
		packet.writeInt(0);
		packet.writeByte(v10.getCharacter_set());
		// reserved : 23 
		packet.writeBytes(new byte[23]);
		// username
		packet.writeString(username); // end by 0
		// password
		packet.writeByte(pwd.length);
		packet.writeBytes(pwd);
		
		this.write(packet);
		
		ReadablePacket rp = this.read();
		int header = rp.readByte();
		if(header == 0) {
			// logger
		}else if(header == -1) {
			StringBuffer sb = new StringBuffer("login in mysql fail!");
			sb.append(", error_code=").append(rp.readVIntD2());
			rp.skip(1);
			sb.append(", sql_state=").append(new String(rp.readBytes(5)));
			sb.append(", error_message=").append(new String(rp.readBytesEOF())).append(".");
			
			throw new ConnectionException(sb.toString());
		}
	}

	@Override
	public void write(Packetable packet) throws ConnectionException {
		try {
			out.write(packet.toBytes());
			out.flush();
		} catch (IOException e) {
			throw new ConnectionException(e);
		}
	}
	
	@Override
	public ReadablePacket read() throws ConnectionException {
		try {
			ReadablePacket packet = new ReadablePacket();
			
			// TODO
			int _len = 0;
			int len = in.read() | ((in.read() & FF) << 8) | ((in.read() & FF) << 16);
			int sequenceId = in.read(); // sequenceId
			System.out.println("sequenceId = " + sequenceId);
			
			byte[] b = new byte[len];
			while(_len < len) {
				_len += in.read(b, _len, len - _len);
			}
			packet.setBody(b);
			return packet;
		} catch (Exception e) {
			throw new ConnectionException(e);
		}
	}

	@Override
	public void dumpBinlog() throws ConnectionException {
		WritablePacket packet = new WritablePacket();
		packet.setSequence(0);
		packet.writeByte(MySQLConstants.COM_BINLOG_DUMP);	// 1 

		packet.writeInt(context.getLogPos());		// 4 logPos
		packet.writeChar(1);						// 2 logFla 
		packet.writeInt(context.getServerId());		// 4 sersId
		packet.writeString(context.getLogName());	// binlogName
		
		this.write(packet);
		
//		ReadablePacket p = this.read(); // EOF Packet
	}

	public final BinlogPacket readPacket() {
		
		return null;
	}
}
