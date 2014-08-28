package me.xcoding.opencdc.net.packet;

import java.io.IOException;

import me.xcoding.opencdc.mysql.protocol.WritablePcaket;

public class InternWritablePacket extends WritablePcaket {

	@Override
	public void flush() throws IOException {
		
	}
	
	public byte[] toBytes() { 
		byte[] bs = new byte[offset];
		
		System.arraycopy(buffer, 0, bs, 0, offset-1);
		offset = 4;
		
		return bs;
	}

}
