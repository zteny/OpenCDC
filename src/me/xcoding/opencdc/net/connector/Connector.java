package me.xcoding.opencdc.net.connector;

import me.xcoding.opencdc.mysql.protocol.ReadablePacket;
import me.xcoding.opencdc.mysql.protocol.WritablePcaket;

// this is a class, not interface
public interface Connector {
	// private ConnectorContext = null;
	
//	Connector getInstance();
	
	void connection(String host, int port) throws ConnectionException;
	
	void login(String usernme, String password) throws ConnectionException;
	
	void write(ReadablePacket packet) throws ConnectionException;
	
	ReadablePacket read() throws ConnectionException;

	void write(WritablePcaket packet) throws ConnectionException;
	
//	void query(String query) throws ConnectionException;
	
//	void dumpBinlog() throws ConnectionException;
	
//	void 
}
