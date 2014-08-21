package me.xcoding.opencdc.mysql.protocol;

import java.io.IOException;
import java.io.InputStream;

import me.xcoding.opencdc.mysql.protocol.BasicReader;

public abstract class ReadablePacket extends InputStream implements BasicReader {
	protected final byte[] buffer = new byte[4 << 20];
	
	protected int end = 4;
	protected int offset = 0;
	
	@Override
	public int readFixedIntT1() {
		return (buffer[offset++] & FF);
	}
	
	@Override
	public int readFixedIntS1() {
		return buffer[offset++];
	}

	@Override
	public int readFixedIntT2() {
		return (buffer[offset++] & FF) | ((buffer[offset++] & FF) << 8);
	}

	@Override
	public int readFixedIntS2() {
		return (buffer[offset++] & FF) | ((buffer[offset++]) << 8);
	}
	
	@Override
	public int readFixedIntT3() {
		return (buffer[offset++] & FF) | ((buffer[offset++] & FF) << 8) 
				| ((buffer[offset++] & FF) << 16);
	}
	
	@Override
	public int readFixedIntS3() {
		return (buffer[offset++] & FF) | ((buffer[offset++] & FF) << 8) 
				| ((buffer[offset++]) << 16);
	}

	@Override
	public int readFixedIntT4() {
		return (buffer[offset++] & FF) | ((buffer[offset++] & FF) << 8)
				| ((buffer[offset++] & FF) << 16) | ((buffer[offset++] & FF) << 24);
	}

	@Override
	public long readFixedIntT6() {
		int v1 = (buffer[offset++] & FF) | ((buffer[offset++] & FF) << 8)
				| ((buffer[offset++] & FF) << 16);
		
		int v2 = (buffer[offset++] & FF) | ((buffer[offset++] & FF) << 8)
				| ((buffer[offset++] & FF) << 16);
		
		return v1 | (v2 << 24);
	}
	
	@Override
	public long readFixedIntS6() {
		int v1 = (buffer[offset++] & FF) | ((buffer[offset++] & FF) << 8)
				| ((buffer[offset++] & FF) << 16);
		
		int v2 = (buffer[offset++] & FF) | ((buffer[offset++] & FF) << 8)
				| ((buffer[offset++]) << 16);
		
		return v1 | (v2 << 24);
	}

	@Override
	public long readFixedIntT8() {
		int v1 = (buffer[offset++] & FF) | ((buffer[offset++] & FF) << 8)
				| ((buffer[offset++] & FF) << 16) | ((buffer[offset++] & FF) << 24);
		int v2 = (buffer[offset++] & FF) | ((buffer[offset++] & FF) << 8)
				| ((buffer[offset++] & FF) << 16) | ((buffer[offset++] & FF) << 24);
		
		return v1 | (v2 << 32);
	}

	@Override
	public long readLenEncInt() {
		int type = buffer[offset++] & FF;
		
		if(type < 0xFB) {
			return type;
		} else if(type < 0xFC) {
			return (buffer[offset++] & FF) | ((buffer[offset++] & FF) << 8); 
		} else if(type < 0xFD) {
			return (buffer[offset++] & FF) | ((buffer[offset++] & FF) << 8)
					| ((buffer[offset++] & FF) << 16);
		} else if(type < 0xFE) {
			return this.readFixedIntT8();
		} else {
			return -1;
		}
	}

	@Override
	public String readStringLenEnc() {
		int len = (int) readLenEncInt();
		
		return readStringVarLen(len);
	}

	@Override
	public String readStringNull() {
		int start = offset;
		while(buffer[offset++]!='0');
		
		byte[] b = new byte[offset - start];
		System.arraycopy(buffer, start, b, 0, offset - start);
		
		return new String(b);
	}

	@Override // FIXME EOF
	public String readStringEOF() {
		int len = end - offset;
		
		byte[] b = new byte[len];
		System.arraycopy(buffer, offset, b, 0, end - offset);
		offset = end;
		
		return new String(b);
	}

	@Override
	public String readStringFixLen(int length) {
		byte[] b = new byte[length];
		System.arraycopy(buffer, offset, b, 0, length);
		
		offset = offset + length;
		return new String(b);
	}

	@Override
	public String readStringVarLen(int length) {
		byte[] b = new byte[length];
		System.arraycopy(buffer, offset, b, 0, length);
		
		offset = offset + length;
		return new String(b);
	}

	@Override
	public byte[] readBytesEOF() {
		int len = end - offset;
		
		byte[] b = new byte[len];
		System.arraycopy(buffer, offset, b, 0, end-offset);
		offset = end;
		
		return b;
	}

	@Override
	public byte[] readBytesNull() {
		int start = offset;
		while(buffer[offset++]!='0');
		
		byte[] b = new byte[offset - start];
		System.arraycopy(buffer, start, b, 0, offset - start);
		offset = offset + start;
		
		return b;
	}

	@Override
	public byte[] readBytesLenEnc() {
		int len = (int) this.readLenEncInt();
		
		byte[] b = new byte[len];
		System.arraycopy(buffer, offset, b, 0, len);
		offset = offset + len;
		
		return b;
	}

	@Override
	public byte[] readBytesVarLen(int length) {
		byte[] b = new byte[length];
		System.arraycopy(buffer, offset, b, 0, length);
		offset = offset + length;
		
		return b;
	}

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

}
