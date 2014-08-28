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

	public byte[] toWriter() throws IOException {
		InternWritablePacket writer = new InternWritablePacket();

		boolean withDb = true;
		if(withDb) {
			capabilityFlags ^= CapabilityFlags.CLIENT_CONNECT_WITH_DB;
		}
		
		writer.writeFixLenIntT4(capabilityFlags);
		writer.writeFixLenIntT4(max_packet_size);
		writer.writeFixLenIntT1(character_size);
		
		writer.writeBytes(new byte[23]); //reserved (all [00])
		
		writer.writeBytesTermNul(username); // FIXME 
		writer.writeBytesVarLen(auth_response); //MySQLUtils.password41OrLater(new byte[]{}, new byte[]{}));
		
		if(withDb)
			writer.writeBytes(database); // FIXME
		
		return writer.toBytes();
	}

	public int getCapabilityFlags() {
		return capabilityFlags;
	}

	public int getMax_packet_size() {
		return max_packet_size;
	}

	public int getCharacter_size() {
		return character_size;
	}

	public byte[] getUsername() {
		return username;
	}

	public byte[] getAuth_response() {
		return auth_response;
	}

	public byte[] getDatabase() {
		return database;
	}

	public byte[] getAuth_plugin_name() {
		return auth_plugin_name;
	}

	public HandshakeResponse setCapabilityFlags(int capabilityFlags) {
		this.capabilityFlags = capabilityFlags;
		return this;
	}

	public HandshakeResponse setMax_packet_size(int max_packet_size) {
		this.max_packet_size = max_packet_size;
		return this;
	}

	public HandshakeResponse setCharacter_size(int character_size) {
		this.character_size = character_size;
		return this;
	}

	public HandshakeResponse setUsername(byte[] username) {
		this.username = username;
		return this;
	}

	public HandshakeResponse setAuth_response(byte[] auth_response) {
		this.auth_response = auth_response;
		return this;
	}

	public HandshakeResponse setDatabase(byte[] database) {
		this.database = database;
		return this;
	}

	public HandshakeResponse setAuth_plugin_name(byte[] auth_plugin_name) {
		this.auth_plugin_name = auth_plugin_name;
		return this;
	}
	
	
	
}
