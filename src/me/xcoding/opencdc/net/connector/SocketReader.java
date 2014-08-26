package me.xcoding.opencdc.net.connector;

import java.io.IOException;
import java.io.InputStream;

import me.xcoding.opencdc.mysql.protocol.ReadablePacket;

public class SocketReader extends ReadablePacket {
	private final InputStream in;
	
	public SocketReader(InputStream in) {
		this.in = in;
	}
	
	public ReadablePacket buildPacket() {
		return this;
	}
	
	@Override
	public boolean hasMore() throws IOException {
		return false;
	}

	@Override
	public boolean hasNext() {
		return false;
	}

	@Override
	public int read() throws IOException {
		return 0;
	}

}
