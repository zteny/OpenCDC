package me.xcoding.opencdc.net;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicBoolean;

import me.xcoding.opencdc.binlog.EventFilter;
import me.xcoding.opencdc.binlog.EventMaker;
import me.xcoding.opencdc.binlog.event.Event;
import me.xcoding.opencdc.binlog.event.EventHeader;
import me.xcoding.opencdc.binlog.parser.EventHeaderParser;
import me.xcoding.opencdc.mysql.protocol.HandshakeV10;
import me.xcoding.opencdc.net.connector.ConnectionException;
import me.xcoding.opencdc.net.connector.IDumpConnector;
import me.xcoding.opencdc.net.connector.SocketReader;
import me.xcoding.opencdc.net.connector.SocketWriter;
import me.xcoding.opencdc.net.connector.intern.DumpConnector;
import me.xcoding.opencdc.net.packet.ERRPacket;
import me.xcoding.opencdc.utils.Maker;

// 在我看来，所有的上下文都应该是唯一的，至少是对线程唯一。
public class ConnectorContext {
	private HandshakeV10 v10 = null;
	private IDumpConnector connector = new DumpConnector(this);
	
	// socket i/o
	private SocketReader reader = null;
	private SocketWriter writer = null; 

	// login info
	private int port = 3306;
	private String host = "locahost";
	private String username = "root";
	private String password = "root";
	
	/**  position in the binlog-file to start the stream with */
	private int logPos = 4;
	/** server id of this slave */
	private int serverId = 6789; 
	/** filename of the binlog on the master */
	private String logName = "mysql-bin.000001";
	
	private boolean crc32 = false;
	
	private final AtomicBoolean isRunning = new AtomicBoolean(false);
	
	public static void main(String[] args) throws Exception {
		new ConnectorContext().start();
	}
	
	public void start() throws ConnectionException {
		// connection to msyql
		connector.connection(host, port);
		
		// login
		connector.login(username, password);
		
		// get xin and xout wrapper
//		this.setXin(connector.getXin());
//		this.setXout(connector.getXout());
		
		// send dump command
		connector.dumpBinlog();
		
		// new Thead(Task()).start();
		thread.run();
	}
	
	void stop() {
		isRunning.compareAndSet(true, false);
	}
	
	void disconnection() {
		
	}
	
	EventFilter filter = new EventFilter();
	EventHeaderParser headerParser = new EventHeaderParser();
//	StartEventV3Parser v3Parser = new StartEventV3Parser();
	void doParse() {
		while(isRunning.get()) {
//			try {
//				ReadablePacket packet = connector.read();
				
//				int status = packet.read(); // packet_header
//				if(status != 0) {
////					System.out.println("failure!\n" + ERRPacket.builder(packet.getBody()));
//					throw new ConnectionException(ERRPacket.builder(packet.getBody()).toString());
//				}
//				
//				EventHeader header = headerParser.parser(null, packet);
//				
//				// do deal head body
//				System.out.println("typeCode = " + header.typeCode);
//				
//				// filter
////				if(filter.isFilter(header.typeCode)) {
////					continue;
////				}
//
//				// 怎么样解析每个Event了。
////				if(header.typeCode != 19) continue ;
//				Event event = EventMaker.parser(header, header.typeCode, packet);
//				
//				System.out.println(event);
				
//				ConsutomParser.parser(event);
//			} catch (ConnectionException e) {
//				e.printStackTrace();
//			}
		}
	}
	
	// wrapper
	void setXin(InputStream ois) {
		
	}
	
	// wrapper
	void setXout(OutputStream oos) {
		
	}
	
	private final Runnable thread = new Runnable() {
		
		public void run() {
			isRunning.compareAndSet(false, true);
			doParse();
		};
	};

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isCrc32() {
		return crc32;
	}

	public void setCrc32(boolean crc32) {
		this.crc32 = crc32;
	}

	public Runnable getThread() {
		return thread;
	}

	public IDumpConnector getConnector() {
		return connector;
	}

	public void setConnector(IDumpConnector connector) {
		this.connector = connector;
	}

	public int getLogPos() {
		return logPos;
	}

	public void setLogPos(int logPos) {
		this.logPos = logPos;
	}

	public int getServerId() {
		return serverId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
	}

	public String getLogName() {
		return logName;
	}

	public void setLogName(String logName) {
		this.logName = logName;
	}
}
