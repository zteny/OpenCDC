package me.xcoding.opencdc.net.packet;

import me.xcoding.opencdc.io.Writable;

public class WritablePacket extends Packet implements Writable{
	private static final int FF = 0x000000FF;
	private byte[] buffer = new byte[8192];
	private int offset = 4;
//	private int length = 0;
	private int head = 4;
	private int tail = 8192;
	
	@Override
	public void setBody(byte[] body) {
		if(isAvailable(body.length)) {
			enLarge();
		}
		System.arraycopy(body, 0, buffer, offset, body.length);
		
		offset += body.length; 
	}

	@Override
	@Deprecated
	public byte[] getBody() {
		return null;
	}

	@Override
	@Deprecated
	public void setLength(int length) {
		buffer[0] = (byte) (length & 0xFF);
		buffer[1] = (byte) ((length >>  8) & 0xFF);
		buffer[2] = (byte) ((length >> 16) & 0xFF);
	}

	@Override
	@Deprecated
	public int getLength() {
		int length = (buffer[0] & FF) | ((buffer[1] >> 8) & FF) |
			((buffer[2] >> 16) & FF);
		return length;
	}

	@Override
	public void setSequence(int sequence) {
		buffer[3] = (byte) (sequence & 0xFF);
	}

	@Override
	public int getSequence() {
		return buffer[3];
	}

	@Override
	public void setCommandPhase(int commandPhase) {
		buffer[4] = (byte) (commandPhase & 0xFF);
	}

	@Override
	public int getCommandPhase() {
		return buffer[4];
	}

	@Override
	public byte[] toBytes() {
		int length = offset - head;
		buffer[0] = (byte) (length & 0xFF);
		buffer[1] = (byte) ((length >>  8) & 0xFF);
		buffer[2] = (byte) ((length >> 16) & 0xFF);
		
		byte[] bs = new byte[offset];
		System.arraycopy(buffer, 0, bs, 0, offset);
		
		return bs;
	}

	@Override
	public void writeByte(int i) {
		buffer[offset++] = (byte) (i & FF);
	}

	@Override
	public void writeByte(byte b) {
		buffer[offset++] = b;
	}

	@Override
	public void writeChar(int c) {
		buffer[offset++] = (byte) (c & FF);
		buffer[offset++] = (byte) ((c >> 8 ) & FF);
	}

	@Override
	public void writeShort(int s) {
		buffer[offset++] = (byte) (s & FF);
		buffer[offset++] = (byte) ((s >> 8 ) & FF);
		buffer[offset++] = (byte) ((s >> 16 ) & FF);
	}

	@Override
	public void writeInt(int i) {
		buffer[offset++] = (byte) (i & FF);
		buffer[offset++] = (byte) ((i >> 8 ) & FF);
		buffer[offset++] = (byte) ((i >> 16 ) & FF);
		buffer[offset++] = (byte) ((i >> 24 ) & FF);
	}

	@Override
	public void write5(long l) {
		buffer[offset++] = (byte) (l & FF);
		buffer[offset++] = (byte) ((l >> 8 ) & FF);
		buffer[offset++] = (byte) ((l >> 16 ) & FF);
		buffer[offset++] = (byte) ((l >> 24 ) & FF);
		buffer[offset++] = (byte) ((l >> 32 ) & FF);
	}

	@Override
	public void write6(long l) {
		buffer[offset++] = (byte) (l & FF);
		buffer[offset++] = (byte) ((l >> 8 ) & FF);
		buffer[offset++] = (byte) ((l >> 16 ) & FF);
		buffer[offset++] = (byte) ((l >> 24 ) & FF);
		buffer[offset++] = (byte) ((l >> 32 ) & FF);
		buffer[offset++] = (byte) ((l >> 40 ) & FF);
	}

	@Override
	public void writeLong(long l) {
		buffer[offset++] = (byte) (l & FF);
		buffer[offset++] = (byte) ((l >> 8 ) & FF);
		buffer[offset++] = (byte) ((l >> 16 ) & FF);
		buffer[offset++] = (byte) ((l >> 24 ) & FF);
		buffer[offset++] = (byte) ((l >> 32 ) & FF);
		buffer[offset++] = (byte) ((l >> 40 ) & FF);
		buffer[offset++] = (byte) ((l >> 48 ) & FF);
		buffer[offset++] = (byte) ((l >> 56 ) & FF);
	}

	@Override
	public void writeBytes(byte[] bytes) {
		if(isAvailable(bytes.length)) {
			enLarge();
		}
		System.arraycopy(bytes, 0, buffer, offset, bytes.length);
		offset += bytes.length;
	}

	@Override
	public void writeBytes(byte[] bytes, int offset, int length) {
		if(isAvailable(length)) {
			enLarge();
		}
		System.arraycopy(bytes, offset, buffer, this.offset, length);
		offset += length;
	}

	@Override
	public void writeString(String str) {
		byte[] b = str.getBytes();
		
		if(isAvailable(b.length + 1)) {
			enLarge();
		}
		System.arraycopy(b, 0, buffer, offset, b.length);
		offset += b.length;
		
		buffer[offset++] = 0; // 写个 0 哈，嘿嘿
	}
	
	private boolean isAvailable(int length) {
		return ((offset + length) > tail);
	}
	
	private void enLarge() {
		tail += 8192;
		byte[] _buffer = new byte[tail];
		
		System.arraycopy(buffer, 0, _buffer, 0, offset);
	}
}
