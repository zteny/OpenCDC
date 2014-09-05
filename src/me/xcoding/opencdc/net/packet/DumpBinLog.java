package me.xcoding.opencdc.net.packet;

import me.xcoding.opencdc.mysql.CommandPhase;

/**
 * <b>Com_Binlog_Dump</b>
 * </br>
 * 
 * flag -- can right now has one value:
 * <table>
 * <tr><td> Flag </td><td> Description </td></tr>
 * <tr><td> 0x01 </td><td> BINLOG_DUMP_NON_BLOCK </td></tr>
 * </table>
 * 
 * 	if there is no more event to send send a EOF_Packet instead of
 *  blocking the connection
 * 
 * @author Teny Zh(zh.Teny.1@gmail.com)
 * @see http://dev.mysql.com/doc/internals/en/com-binlog-dump.html
 */
public class DumpBinLog {
	private static final int commandPhase = CommandPhase.COM_BINLOG_DUMP;
	private int binLogPos = 0;
	private int flags = 0x01;
	// server id of this slave
	private int serverId;	
	private String filename;
	
	public byte[] toByte() {
		InternWritablePacket packet = new InternWritablePacket();
		
		packet.writeFixLenIntT1(commandPhase);
		packet.writeFixLenIntT4(binLogPos);
		packet.writeFixLenIntT2(flags);
		packet.writeFixLenIntT4(serverId);
		
		// FIXME
		packet.writeBytesTermNul(filename.getBytes());
		
		return packet.toBytes();
	}

	public int getBinLogPos() {
		return binLogPos;
	}

	public int getFlags() {
		return flags;
	}

	public int getServerId() {
		return serverId;
	}

	public String getFilename() {
		return filename;
	}

	public DumpBinLog setBinLogPos(int binLogPos) {
		this.binLogPos = binLogPos;
		return this;
	}
	
	/**
	 * Can right now has one value: 0x01
	 * 
	 * @return BinlogPacket
	 */
	public DumpBinLog setFlags(int flags) {
		this.flags = flags;
		return this;
	}

	/**
	 * Server id of this slave.
	 * 
	 * @param serverId
	 * @return BinlogPacket
	 */
	public DumpBinLog setServerId(int serverId) {
		this.serverId = serverId;
		return this;
	}

	public DumpBinLog setFilename(String filename) {
		this.filename = filename;
		return this;
	}
	
}
