package me.xcoding.opencdc.net.connector;

import me.xcoding.opencdc.net.packet.Packetable;
import me.xcoding.opencdc.net.packet.ReadablePacket;

// this is a class, not interface
public interface Connector {
	// private ConnectorContext = null;
	
//	Connector getInstance();
	
	void connection(String host, int port) throws ConnectionException;
	
	void login(String usernme, String password) throws ConnectionException;
	
	void write(Packetable packet) throws ConnectionException;
	
	ReadablePacket read() throws ConnectionException;
	
//	void query(String query) throws ConnectionException;
	
//	void dumpBinlog() throws ConnectionException;
	
//	void 
}
