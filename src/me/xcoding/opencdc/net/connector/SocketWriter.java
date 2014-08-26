package me.xcoding.opencdc.net.connector;

import java.io.IOException;
import java.io.OutputStream;

import me.xcoding.opencdc.mysql.protocol.WritablePcaket;

public class SocketWriter extends WritablePcaket {
	private final OutputStream os;
	
	public SocketWriter(OutputStream os) {
		this.os = os;
	}

	@Override
	public void flush() throws IOException {
		offset = offset - 5;
		buffer[0] = (byte) (offset & FF);
		buffer[1] = (byte) ((offset >>  8) & FF);
		buffer[2] = (byte) ((offset >> 16) & FF);
		
		os.write(buffer, 0, offset+4);
		os.flush();
		
		// clear
		buffer[3] = 0; buffer[4] = 0; offset = 5;
	}
	
	public void write(WritablePcaket packet) {
		
	}
}
