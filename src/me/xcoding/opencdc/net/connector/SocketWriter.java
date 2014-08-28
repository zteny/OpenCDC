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
		os.flush();
		
		// clear
		buffer[3] = 0; buffer[4] = 0; offset = 5;
	}
}
