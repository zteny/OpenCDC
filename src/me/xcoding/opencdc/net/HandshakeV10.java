package me.xcoding.opencdc.net;

import java.lang.reflect.Field;

import me.xcoding.opencdc.io.IOUtils;

public class HandshakeV10 {
	private int protocal_version = 0;
	private String server_version;
	private int connectin_id = 0;
	private byte[] auth_plugin_data_part_1; // byte[]
	private int filer = 0;
	private int capability_flag_1 = 0;
	private int character_set;
	private int status_flags;
	private int capability_flags_2;
	private int auth_plugin_data_len;
//	private String auth_plugin_data;
	private byte[] auth_plugin_data_part_2; // byte[]
	private String auth_plugin_name;
	public final byte[] auth_plugin_data_part;
	
	
	public HandshakeV10(byte[] buffer) {
		IOUtils io = new IOUtils(buffer, 0);
		
		protocal_version = io.readIntD8();
		server_version = new String(io.readString()); // version
		connectin_id = io.readIntD32();			 // connection_id
		auth_plugin_data_part_1 = io.readString(8); // auth scramble
		filer = io.readIntD8(); // filer_1
		
		capability_flag_1 = io.readIntD16(); // capability_flag_1
		character_set = io.readIntD8();  // charset_set
		status_flags = io.readIntD16(); // status_flags
		capability_flags_2 = io.readIntD16(); // capabillity_flag_2
		auth_plugin_data_len = io.readIntD8();
		
		io.skip(10);
		
		auth_plugin_data_part_2 = io.readString(); // max(13, length of auth-plugin-data - 8)
		auth_plugin_name = new String(io.readString());
		
		int v = auth_plugin_data_part_1.length + auth_plugin_data_part_2.length;
		auth_plugin_data_part = new byte[v];
	
		System.arraycopy(auth_plugin_data_part_1, 0, auth_plugin_data_part, 0, auth_plugin_data_part_1.length);
		System.arraycopy(auth_plugin_data_part_2, 0, auth_plugin_data_part, auth_plugin_data_part_1.length, 
				auth_plugin_data_part_2.length);
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(super.toString()).append("{");
		
		Class c = this.getClass();
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
		return capability_flag_1;
	}

	public void setCapability_flag_1(int capability_flag_1) {
		this.capability_flag_1 = capability_flag_1;
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
