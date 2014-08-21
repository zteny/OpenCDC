package me.xcoding.test;

import java.net.Socket;
import java.util.zip.CRC32;

public class CRC32Test {

	public static void main(String[] args) throws Exception {
//		byte[] bs = new byte[]{(byte)0x8D,0x56,(byte)0xB8,0x53,(byte)0x1E,0x01,0x00,0x00,0x00,0x2D,0x00,0x00,0x00,0x19,0x01,0x00,0x00,0x00,0x00,0x46,0x00,0x00,0x00,0x00,0x00,0x01,0x00,0x02,0x00,0x02,(byte)0xFF,(byte)0xFC,0x20,0x00,0x00,0x00,0x04,0x74,0x65,0x73,0x74 };
//
//		CRC32 crc = new CRC32();
//		crc.update(bs);
//		long crc32 = crc.getValue();
//		System.out.println(Long.toHexString(crc32));
		// 0x75,0x3F,(byte)0xF6,(byte)0xB1
		
//		Socket socket = null;
//		socket.setReceiveBufferSize(1000);
//		socket.setSendBufferSize(1000);
		
		int a = 63487, b = 32895;
		System.out.println(a | b);
		System.out.println(a & b);
		System.out.println(Integer.toHexString(a));
		System.out.println(Integer.toHexString(b));
		System.out.println(Integer.toHexString(a-b));
	}
}
