package me.xcoding.opencdc.net.packet;

import me.xcoding.opencdc.io.Readable;

public class ReadablePacket implements Packetable, Readable {
	private static final long FFl = 0x00000000000000FF;
	private static final long _8F = 0x00000000FFFFFFFF; 
	private static final int FF = 0x000000FF;
	private byte[] buffer = null;// new byte[8192];
	private int offset = 0;
	private int head = 0;
	private int tail = 0;

	@Override
	public void setBody(byte[] body) {
		tail = body.length;
//		if(isAvailable(tail)) {
//			buffer = new byte[tail];
//		}
//		
//		System.arraycopy(body, 0, buffer, 0, tail);
		
		buffer = body;
	}

	@Override
	public byte[] getBody() {
		return buffer;
	}

	@Override
	@Deprecated
	public void setLength(int length) {
		
	}

	@Override
	@Deprecated
	public int getLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Deprecated
	public void setSequence(int sequence) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Deprecated
	public int getSequence() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Deprecated
	public void setCommandPhase(int commandPhase) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Deprecated
	public int getCommandPhase() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public byte[] toBytes() {
		return this.getBody();
	}

	@Override
	public int read() {
		return buffer[offset++];
	}

	@Override
	public int readIntD() {
//		int v = (buffer[offset++] & FF) | ((buffer[offset++] & FF) << 8)
//			| ((buffer[offset++] & FF) << 16) | ((buffer[offset++] & FF) << 24);
		
		int v = (buffer[offset++] & FF);
		v |= ((buffer[offset++] & FF) << 8);
		v |= ((buffer[offset++] & FF) << 16);
		v |= ((buffer[offset++] & FF) << 24);
			
		return v;
	}

	@Override
	public int readVInt1() {
		return (buffer[offset++] & FF);
	}

	@Override
	public int readVIntD2() {
		return (buffer[offset++] & FF) | ((buffer[offset++] & FF) << 8);
	}

	@Override
	public int readVIntD3() {
		return (buffer[offset++] & FF) | ((buffer[offset++] & FF) << 8) |
				((buffer[offset++] & FF) << 16);
	}

	@Override
	public int readInt() {
		int v = ((buffer[offset++] & FF) << 24) | ((buffer[offset++] & FF) << 16)
				| ((buffer[offset++] & FF) << 8) | (buffer[offset++] & FF);
		return v;
	}

	@Override
	public int readVInt2() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int readVInt3() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long readLongD() {
		int v1 = (buffer[offset++] & FF) | ((buffer[offset++] & FF) << 8) | ((buffer[offset++] & FF) << 16) |
				((buffer[offset++] & FF) << 24);
		int v2 = (buffer[offset++] & FF) | ((buffer[offset++] & FF) << 8) | ((buffer[offset++] & FF) << 16) |
				((buffer[offset++] & FF) << 24);
	
		return (v1 & _8F) | ((v2 & _8F) << 32);
	}

	@Override
	public long readLong() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long readVLong5() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long readVLong6() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long readVLongD6() {
		long v = (buffer[offset++] & FFl) | ((buffer[offset++] & FFl) << 8)
			| ((buffer[offset++] & FFl) << 16) | ((buffer[offset++] & FFl) << 24)
			| ((buffer[offset++] & FFl) << 32) | ((buffer[offset++] & FFl) << 40);
		return v;
	}

	@Override
	public long readVLong7() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long readVLongD7() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public long readVLongD8() {
		long v = (buffer[offset++] & FFl) | ((buffer[offset++] & FFl) << 8)
			| ((buffer[offset++] & FFl) << 16) | ((buffer[offset++] & FFl) << 24)
			| ((buffer[offset++] & FFl) << 32) | ((buffer[offset++] & FFl) << 40)
			| ((buffer[offset++] & FFl) << 48) | ((buffer[offset++] & FFl) << 56);
		return 0;
	}

	@Override
	public byte readByte() {
		return buffer[offset++];
	}

	@Override
	public byte[] readBytes() {
		int l = offset;
		for(; buffer[offset++] != 0; l++);
		
		byte b[] = new byte[l];
		System.arraycopy(buffer, offset-l, b, 0, l);
		
		return b;
	}
	
	public byte[] readBytesEOF() {
		int l = tail - offset;
		byte[] b = new byte[l];
		
		System.arraycopy(buffer, offset, b, 0, l);
		offset = tail;
		
		return b;
	}

	@Override
	public byte[] readBytes(int length) {
		byte[] b = new byte[length];
		System.arraycopy(buffer, offset, b, 0, length);
		offset += length;
		return b;
	}
	
	public void skip(int length) {
		offset += length;
	}
	
	public long lenencInt() {
		int type = buffer[offset++] & FF;
		if(type < 0xFB) {
			return type;
		} else if(type == 0xFC) {
			return this.readVIntD2();
		} else if(type == 0xFD) {
			return this.readVIntD3();
		} else if(type == 0xFE) {
			return this.readVLongD8();
		} else {
			throw new RuntimeException();
		}
	}
	
	public String readNulTerminatedString() {
		return new String(this.readBytesEOF());
	}
	
	public String readVariableLengthString(int length) {
		return new String(this.readBytes(length));
	}
	
	/**
	 * @see string.var.leng
	 * @see lenenc_int
	 * @return
	 */
	public String readVariableLengthString() {
		int type = buffer[offset++] & FF;
		int length = 0;
		
		if(type < 0xFB) {
			length = type;
		} else if(type == 0xFC) {
			length = this.readVIntD2();
		} else if(type == 0xFD) {
			length = this.readVIntD3();
		} else { // if(type == 0xFE) {
			// TODO 
			throw new RuntimeException();
		}
		
		byte[] b = new byte[length];
		System.arraycopy(buffer, offset, b, 0, type);
		offset += type;
		return new String(b);
	}
	
	
	private boolean isAvailable(int length) {
		return ((offset + length) > tail);
	}
}