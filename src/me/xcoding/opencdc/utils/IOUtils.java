package me.xcoding.opencdc.utils;

public final class IOUtils {
	public static final byte[] add(byte[] b1, byte[] b2) {
		byte[] v = new byte[b1.length + b2.length];
		
		System.arraycopy(b1, 0, v, 0, b1.length);
		System.arraycopy(b2, 0, v, b1.length, b2.length);
		
		return v;
	}
	
	public static final byte[] add(byte[] b1, byte[]... b2) {
		int len = b1.length, offset = len;
		
		for(byte[] _b : b2) {
			len += _b.length;
		}
		
		byte[] b = new byte[len];
		System.arraycopy(b1, 0, b, 0, len);
		
		for(byte[] _b : b2) {
			System.arraycopy(_b, 0, b, offset, _b.length);
			
			offset += _b.length;
		}
		
		return b;
	}
	
	public static void main(String[] args) {
		add(new byte[]{}, new byte[]{});
	}
}
