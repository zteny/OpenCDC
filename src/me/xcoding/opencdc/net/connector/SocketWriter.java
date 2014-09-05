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
		int v = offset - 4;
		
		buffer[0] = (byte) (v & FF);
		buffer[1] = (byte) ((v >>  8) & FF);
		buffer[2] = (byte) ((v >> 16) & FF);
		
		os.write(buffer, 0, offset);
		os.flush();

		// clear commandPhase, offset.
		buffer[3] = 0; offset = 4;
	}
}
