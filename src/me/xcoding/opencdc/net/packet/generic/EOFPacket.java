package me.xcoding.opencdc.net.packet.generic;

import me.xcoding.opencdc.mysql.protocol.ReadablePacket;

/**
 * <b> EOF_Packet </b>
 * 
 * </br>
 * <b>Caution</b></br>
 * the EOF packet may appear in places where a Protocol::LengthEncodedInteger 
 * may appear. You must check whether the packet length is less than 9 to make 
 * sure that it is a EOF packet.
 * 
 * @author Teny Zh(zh.Teny.1@gmail.com)
 * @see http://dev.mysql.com/doc/internals/en/packet-EOF_Packet.html
 */
public class EOFPacket extends GenericPacket {
	public static final int header = 0x000000FE;
	
	public final int warningCount;
	public final int statusFlags;

	private EOFPacket(int warningCount, int statusFlags) {
		this.warningCount = warningCount;
		this.statusFlags = statusFlags;
	}
	
	public static final EOFPacket parser(ReadablePacket p) {
		return null;
	} 
}
