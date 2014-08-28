package me.xcoding.opencdc.net.packet.generic;

import me.xcoding.opencdc.mysql.protocol.ReadablePacket;

/**
 * <b> Err_Packet</b>
 * 
 * @author Teny Zh(zh.Teny.1@gmail.com)
 * @see http://dev.mysql.com/doc/internals/en/packet-ERR_Packet.html
 */
public class ErrPacket extends GenericPacket {
	public static final int header = 0x000000FF;
	public final int errorCode;
	public final String sqlState;
	public final String errorMessage;
	
	private ErrPacket(int errorCode, String sqlState, String errorMessage) {
		this.errorCode = errorCode;
		this.sqlState = sqlState;
		this.errorMessage = errorMessage;
	}
	
	public static final ErrPacket parser(ReadablePacket p) {
		return new ErrPacket(
				p.readFixedIntT2(),
				p.readStringFixLen(6),
				p.readStringEOF()
			);
	}
}
