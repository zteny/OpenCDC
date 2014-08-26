package me.xcoding.opencdc.mysql.protocol;

import java.io.IOException;

public class HandshakeV10 extends ReadablePacket {

	@Override
	public boolean hasMore() throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int read() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
