package me.xcoding.opencdc.mysql.protocol;

import java.io.IOException;

import me.xcoding.opencdc.net.connector.SocketWriter;
import me.xcoding.opencdc.net.packet.InternWritablePacket;
import me.xcoding.opencdc.utils.MySQLUtils;

/**
 * <B>Protocol::HandshakeResponse</B></br>
 * </br>
 * Depending on the servers support for the CLIENT_PROTOCOL_41 capability 
 * and the clients understanding of that flag the client has to send either 
 * a Protocol::HandshakeResponse41 or Protocol::HandshakeResponse320.
 * </br></br>
 * <B>Protocol::HandshakeResponse41:</B></br>
 * </br>
 * Handshake Response Packet sent by 4.1+ clients supporting CLIENT_PROTOCOL_41 
 * capability, if the server announced it in its Initial Handshake Packet.
 * Otherwise (talking to an old server) the Protocol::HandshakeResponse320 
 * packet has to be used.
 * 
 * </br>
 * On MySQL 5.5.8 with CLIENT_PROTOCOL_41 CLIENT_PLUGIN_AUTH, CLIENT_SECURE_CONNECTION, 
 * and CLIENT_CONNECT_WITH_DB set, it may look like:
 * 
 * @author Teny Zh(zh.Teny.1@gmail.com)
 * @see http://dev.mysql.com/doc/internals/en/connection-phase-packets.html
 * 
 */
public class HandshakeResponse {
	// FIXME CLIENT_PROTOCOL_41 CLIENT_PLUGIN_AUTH, CLIENT_SECURE_CONNECTION
	private int capabilityFlags = CapabilityFlags.CLIENT_PROTOCOL_41 | // CLIENT_PROTOCOL_41 always set
		CapabilityFlags.CLIENT_PLUGIN_AUTH | CapabilityFlags.CLIENT_SECURE_CONNECTION;
	
	private int max_packet_size;
	private int character_size;
	
	// FIXME should be used byte[] or char[]
	private byte[] username;
//	private byte[] password;
	
	private byte[] auth_response;
	private byte[] database;
	private byte[] auth_plugin_name;

	public WritablePcaket toWriter() throws IOException {
		WritablePcaket writer = new InternWritablePacket();

		boolean withDb = true;
		if(withDb) {
			capabilityFlags ^= CapabilityFlags.CLIENT_CONNECT_WITH_DB;
		}
		
		writer.writeFixLenIntT4(capabilityFlags);
		writer.writeFixLenIntT4(max_packet_size);
		writer.writeFixLenIntT1(character_size);
		
		writer.writeBytes(new byte[23]); //reserved (all [00])
		
		// writer.writeBytesNulTerm
		writer.writeBytes(username); // FIXME 
		
		
		// auth-response
		// writer.writeBytesVarLen   writer.writeBytesLenEnc
		writer.writeFixLenIntT1(20);
		writer.writeBytes(auth_response); //MySQLUtils.password41OrLater(new byte[]{}, new byte[]{}));
		
		// writer.write database
		writer.writeBytes(database); // FIXME
		
		return writer;
	}
	
}
