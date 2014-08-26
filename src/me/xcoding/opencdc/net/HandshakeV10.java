package me.xcoding.opencdc.net;

import java.lang.reflect.Field;

import me.xcoding.opencdc.mysql.protocol.CapabilityFlags;
import me.xcoding.opencdc.mysql.protocol.ReadablePacket;
import me.xcoding.opencdc.utils.IOUtils;

public class HandshakeV10 {
	private int protocal_version = 0;
	private String server_version;
	private int connectin_id = 0;
	private byte[] auth_plugin_data_part_1; // byte[]
	private int filer = 0;
	private int capability_flags_1 = 0;
	private int character_set;
	private int status_flags;
	private int capability_flags_2;
	private int auth_plugin_data_len;
//	private String auth_plugin_data;
	private byte[] auth_plugin_data_part_2; // byte[]
	private String auth_plugin_name;
	public final byte[] auth_plugin_data_part;
	
	
	public HandshakeV10(ReadablePacket packet) {
		protocal_version 		= packet.readFixedIntT1(); 
		server_version			= packet.readStringNull(); // version
		connectin_id 			= packet.readFixedIntT4(); // connection_id
		auth_plugin_data_part_1 = packet.readBytesVarLen(8); // author scramble
		filer 					= packet.readFixedIntT1(); // filer_1 // awayls set [00]
		
		capability_flags_1 		= packet.readFixedIntT2(); // capability_flag_1
		character_set			= packet.readFixedIntT1(); // charset_set
		status_flags 			= packet.readFixedIntT2(); // status_flags
		capability_flags_2 		= packet.readFixedIntT2(); // capabillity_flag_2
		auth_plugin_data_len 	= packet.readFixedIntT1();
		
		// for test
		int capabilities = (capability_flags_1 & 0x0000FFFF) | ((capability_flags_2 & 0x0000FFFF) << 16);
		System.out.println("HandshakeV10.HandshakeV10()" + 
				((capabilities & 0x00080000) == auth_plugin_data_len));
		
		packet.skip(10);
		
		int length = Math.max(13, auth_plugin_data_len - 8);
		if((capabilities & CapabilityFlags.CLIENT_SECURE_CONNECTION) == 0) {
			auth_plugin_data_part_2	= packet.readBytesVarLen(length); // author scramble
		}

		if((capabilities & CapabilityFlags.CLIENT_SECURE_CONNECTION) == 0) {
			auth_plugin_name = packet.readStringNull();
		}
		
		auth_plugin_data_part = IOUtils.add(auth_plugin_data_part_1, auth_plugin_data_part_2);
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(super.toString()).append("{");
		
		Class<?> c = this.getClass();
		Field fs[] = c.getDeclaredFields();
		System.out.println(fs.length);
		
		Field.setAccessible(fs, true);
		try {
			for(Field f : fs) {
				sb.append(f.getName()).append(" = ").append(f.get(this)).append(", \n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}

	public int getProtocal_version() {
		return protocal_version;
	}

	public void setProtocal_version(int protocal_version) {
		this.protocal_version = protocal_version;
	}

	public String getServer_version() {
		return server_version;
	}

	public void setServer_version(String server_version) {
		this.server_version = server_version;
	}

	public int getConnectin_id() {
		return connectin_id;
	}

	public void setConnectin_id(int connectin_id) {
		this.connectin_id = connectin_id;
	}

	public int getFiler() {
		return filer;
	}

	public void setFiler(int filer) {
		this.filer = filer;
	}

	public int getCapability_flag_1() {
		return capability_flags_1;
	}

	public void setCapability_flag_1(int capability_flag_1) {
		this.capability_flags_1 = capability_flag_1;
	}

	public int getCharacter_set() {
		return character_set;
	}

	public void setCharacter_set(int character_set) {
		this.character_set = character_set;
	}

	public int getStatus_flags() {
		return status_flags;
	}

	public void setStatus_flags(int status_flags) {
		this.status_flags = status_flags;
	}

	public int getCapability_flags_2() {
		return capability_flags_2;
	}

	public void setCapability_flags_2(int capability_flags_2) {
		this.capability_flags_2 = capability_flags_2;
	}

	public int getAuth_plugin_data_len() {
		return auth_plugin_data_len;
	}

	public void setAuth_plugin_data_len(int auth_plugin_data_len) {
		this.auth_plugin_data_len = auth_plugin_data_len;
	}

	public String getAuth_plugin_name() {
		return auth_plugin_name;
	}

	public void setAuth_plugin_name(String auth_plugin_name) {
		this.auth_plugin_name = auth_plugin_name;
	}
	
	
}
