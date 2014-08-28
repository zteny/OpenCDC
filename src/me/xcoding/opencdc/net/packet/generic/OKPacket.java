package me.xcoding.opencdc.net.packet.generic;

import me.xcoding.opencdc.mysql.protocol.ReadablePacket;

/**
 * <b>OK_Packet</b>
 * </br>
 * An OK packet is sent from the server to the client to signal 
 * successful completion of a command.</br>
 * </br>
 * NOTE:LenEnc-Int, should be integer, because max value of integer
 * 	more than 100Gi. 
 * 
 * @author Teny Zh(zh.Teny.1@gmail.com)
 * @see http://dev.mysql.com/doc/internals/en/packet-OK_Packet.html
 */
public class OKPacket extends GenericPacket {
	public static final int header = 0x00;
	public final int affectedRows; // FIXME LenEnc-Int
	public final int lastInsertId; // FIXME LenEnc-Int
	        
	public final int statusFlags;
	public final int warnings;
	        
	public final String sessionStateChangeInfo; // FIXME LenEnc-Int
	        
	public final String info; // FIXME LenEnc-Int
	
	private OKPacket(int affectedRows, int lastInsertId, int statusFlags,
			int warnings, String sessionStateChangeInfo, String info) {
		this.affectedRows = affectedRows;
		this.lastInsertId = lastInsertId;
		this.statusFlags = statusFlags;
		this.warnings = warnings;
		this.sessionStateChangeInfo = sessionStateChangeInfo;
		this.info = info;
	}

	public static final OKPacket parser(ReadablePacket p) {
		// sorry, 实在不想写那么长的变量名了，COPY也不想，哼。
		// 看不惯？有想法？ 看不惯，有想法，你 fork me啊！ 哼哼。
		int a = (int) p.readLenEncInt();
		int b = (int) p.readLenEncInt();
		int c = p.readFixedIntT2();
		
		int d = p.readFixedIntT2();
		
		String info = p.readStringLenEnc();
		String ssci = p.readStringLenEnc();
		
		return new OKPacket(
				a, b, c, d, 
				info, ssci
			);
	}
	
}
