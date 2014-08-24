package me.xcoding.opencdc.io;

import java.io.IOException;
import java.io.InputStream;

import me.xcoding.opencdc.mysql.protocol.BasicReader;
import me.xcoding.opencdc.mysql.protocol.ReadablePacket;

public class XReader extends ReadablePacket {
	private static final int bufferSize = 4 << 20;
	private final InputStream in;
	private int tail = 0;
	
	public XReader(InputStream in) {
		offset = 4;
		this.in = in;
	}
	
	@Override
	public boolean hasMore() throws IOException {
		System.out.println("consumer size = " + (end - offset));
		if(end >= tail) {
			fill();
		}
		return tail > end;
	}
	
	public BasicReader initPacket() throws IOException {
		offset = end;
		skip(9);
		int length = read() | (read() << 8) | (read() << 16) | (read() << 24);
		skip(-13);
		
		System.out.println("length = " + length);
		while(tail - end < length) {
			fill();
		}
		
		end = end + length;
		
		return this;
	}
	
	public int skip(int length) {
		return (offset = offset + length);
	}
	
	public int back(int length) {
		return (offset = offset - length);
	}
	
	private void fill() throws IOException {
		if(bufferSize - tail < 512) {
			System.arraycopy(buffer, tail, buffer, 0, bufferSize - tail);
			tail = bufferSize - tail;
		}
		
		tail += in.read(buffer, tail, bufferSize - tail);
	}
	
//	private void fill(int length) throws IOException {
//		fill();
//		if(tail - end < length) {
//			fill();
//		}
//	}
	
	@Override
	public int read() throws IOException {
		if(end > tail) {
			fill();
		}
		return (buffer[offset++] & FF);
	}

	@Override
	public boolean hasNext() {
		return false;
	}

}
