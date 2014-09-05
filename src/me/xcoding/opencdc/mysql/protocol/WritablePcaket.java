package me.xcoding.opencdc.mysql.protocol;

/**
 * 
 * @author Teny Zh(zh.Teny.1@gmail.com)
 * FIXME 当 buffer超出  8192bytes 时，怎么办？ -- 满则flush()
 */
public abstract class WritablePcaket implements BasicWriter {
	protected final byte[] buffer = new byte[8192];
	protected int end = 8192;
	protected int offset = 4;
	
	@Override
	public void writeBytesVarLen(byte[] value) {
		int v = value.length;
		buffer[offset++] = (byte) (v & FF);
		if(v > 256) {
			buffer[offset++] = (byte) ((v >> 8) & FF);
		} 
		if(v > 0x00FFFFFF) {
			buffer[offset++] = (byte) ((v >> 16) & FF);
		}

		assert !(v > 0x00FFFF);
		
		System.arraycopy(value, 0, buffer, offset, v);
		offset += v;
	}

	@Override
	public void writeBytesTermNul(byte[] value) {
		System.arraycopy(value, 0, buffer, offset, value.length);
		offset += value.length;
		buffer[offset++] = 0;
	}

	@Override
	public void writeBytesLenEnc(byte[] value) {
		int v = value.length;
		if(v < 0x000000FB) {
			buffer[offset++] = (byte) (v & FF);
		} else if(v < 0x00FC0000) {
			buffer[offset++] = (byte) 0xFC;
			buffer[offset++] = (byte) (v & FF);
			buffer[offset++] = (byte) ((v >> 8) & FF);
		} else if(v < 0xFD000000) { // FIXME
			buffer[offset++] = (byte) 0xFD;
			buffer[offset++] = (byte) (v & FF);
			buffer[offset++] = (byte) ((v >>  8) & FF);
			buffer[offset++] = (byte) ((v >> 16) & FF);
		}
		
		System.arraycopy(value, 0, buffer, offset++, v);
		offset += v;
	}
	
	@Override
	public void writeFixLenIntT1(int value) {
		buffer[offset++] = (byte) (value & FF);
	}

	@Override
	public void writeFixLenIntT2(int value) {
		buffer[offset++] = (byte) (value & FF);
		buffer[offset++] = (byte) ((value >> 8) & FF);
	}

	@Override
	public void writeFixLenIntT3(int value) {
		buffer[offset++] = (byte) (value & FF);
		buffer[offset++] = (byte) ((value >>  8) & FF);
		buffer[offset++] = (byte) ((value >> 16) & FF);
	}

	@Override
	public void writeFixLenIntT4(int value) {
		buffer[offset++] = (byte) (value & FF);
		buffer[offset++] = (byte) ((value >>  8) & FF);
		buffer[offset++] = (byte) ((value >> 16) & FF);
		buffer[offset++] = (byte) ((value >> 24) & FF);
	}

	@Override
	public void writeFixLenIntT6(long value) {
		int v1 = (int) (value & 0x00FFFFFF);
		int v2 = (int) (value >> 24) & 0x00FFFFFF;
		
		buffer[offset++] = (byte) (v1 & FF);
		buffer[offset++] = (byte) ((v1 >>  8) & FF);
		buffer[offset++] = (byte) ((v1 >> 16) & FF);
		
		buffer[offset++] = (byte) (v2 & FF);
		buffer[offset++] = (byte) ((v2 >>  8) & FF);
		buffer[offset++] = (byte) ((v2 >> 16) & FF);
	}

	@Override
	public void writeFixLenIntT8(long value) {
		int v1 = (int) (value & 0xFFFFFFFF);
		int v2 = (int) (value >> 32) & 0xFFFFFFFF;
		
		buffer[offset++] = (byte) (v1 & FF);
		buffer[offset++] = (byte) ((v1 >>  8) & FF);
		buffer[offset++] = (byte) ((v1 >> 16) & FF);
		buffer[offset++] = (byte) ((value >> 24) & FF);
		
		buffer[offset++] = (byte) (v2 & FF);
		buffer[offset++] = (byte) ((v2 >>  8) & FF);
		buffer[offset++] = (byte) ((v2 >> 16) & FF);
		buffer[offset++] = (byte) ((value >> 24) & FF);
		
	}

	@Override
	public void writeBytes(byte[] value) {
		System.arraycopy(value, 0, buffer, offset, value.length);
		offset += value.length;
	}

	@Override
	public void writeBytes(byte[] value, int length) {
		System.arraycopy(value, 0, buffer, offset, length);
		offset += length;
	}

	@Override
	public void writeBytes(byte[] value, int offset, int length) {
		System.arraycopy(value, offset, buffer, this.offset, length);
		this.offset += length;
	}

	public void setSequence(int sequence) {
		buffer[3] = (byte) (sequence & FF);
	}
	
}
