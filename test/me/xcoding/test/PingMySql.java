package me.xcoding.test;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Arrays;

import com.google.code.or.common.glossary.column.StringColumn;
import com.google.code.or.common.util.MySQLConstants;
import com.google.code.or.common.util.MySQLUtils;
import com.google.code.or.net.impl.packet.command.ComBinlogDumpPacket;
import com.google.code.or.net.impl.packet.command.ComQuery;

import me.xcoding.opencdc.io.IOUtils;
import me.xcoding.opencdc.net.HandshakeV10;

public class PingMySql {
	
	public static void main(String[] args) throws Exception {
		PingMySql p = new PingMySql();
		p.connect();
	}
	
	public void connect() throws Exception {
		Socket socket = new Socket("localhost", 3306);
		InputStream in = socket.getInputStream();
		OutputStream out = socket.getOutputStream();
		
		int length = in.read() | in.read() << 8 | in.read() << 16;
		System.out.println(length);
		int sequece = in.read();
		
		int total = 0;
		byte[] body = new byte[length];
		while(length > total) {
			total += in.read(body, total, length - total);
		}
		IOUtils io = new IOUtils(body, 0);
		
//		System.out.println(io.readIntD8());
//		System.out.println(new String(io.readString())); // version
//		System.out.println(io.readIntD32());			 // connection_id
//		System.out.println(new String(io.readString(8))); // auth scramble
//		System.out.println(io.readIntD8()); // filer_1
//		
//		System.out.println(io.readIntD16()); // capability_flag_1
//		System.out.println(io.readIntD8());  // charset_set
//		System.out.println(io.readIntD16()); // status_flags
//		System.out.println(io.readIntD16()); // capabillity_flag_2
//		int ii = io.readIntD8();
//		System.out.println(ii);  // auth
//		System.out.println(new String(io.readString()));
//		
//		io.skip(10);
//		
//		System.out.println(new String(io.readString())); // max(13, length of auth-plugin-data - 8)
//		System.out.println(new String(io.readString()));
		
		HandshakeV10 v10 = new HandshakeV10(body);
		System.out.println(v10);
		
		byte[] password = MySQLUtils.password41OrLater(new byte[]{'r', 'o', 'o', 't'}, v10.auth_plugin_data_part);

OutputStream os = new BufferedOutputStream(out, 128);

		os.write(new byte[]{58, 0, 0, 1}); // 4
		
		os.write(new byte[]{4, -126, 0, 0}); // 4
		
//		os.write(new byte[]{04, 20, 8, 00});
		
		os.write(new byte[]{0, 0, 0, 0}); // 4
		os.write(new byte[]{33}); // 1
		os.write(new byte[23]); //23
		os.write(new byte[]{'r', 'o', 'o', 't', 0}); //5
//		os.write(new byte[]{'r', '0', '0', 't', '0'});
		os.write(new byte[]{20});
		
		os.write(password); // 20
		
		os.flush();
		
//		IOUtils ios = new IOUtils(buffer, offset)
//		int xx = in.read() | in.read() >> 8 |in.read() >> 16 | in.read() >> 24;
//		System.out.println(xx);
//		System.out.println(in.read());
		
		int x = in.read();
		byte[] bss = new byte[x];
		in.read(bss, 0, x);
		
		System.out.println(new String(bss));
		
		
//		final ComQuery command = new ComQuery();
//		command.setSql(StringColumn.valueOf("select * from test.t1 where id < 6".getBytes()));
//		
//		os.write(command.getLength());
//		os.write(new byte[]{0, 0, 1});
//		os.write(command.getPacketBody(), 0, command.getLength());
		

		//		os.write(new byte[]{16 + 11, 0, 0, 0});
		
//		os.write(new byte[]{18});
//		os.write(new byte[]{4, 0, 0, 0});
//		os.write(new byte[]{0, 0});
//		os.write(new byte[]{12, 0, 0, 0});
		
		// 27, 0, 0, 0, 18, 4, 0, 0, 0, 0, 0, -123, 26, 0, 0, 109, 121, 115,
		// 113, 108, 45, 98, 105, 110, 46, 48, 48, 48, 48, 48, 53,
//		IOUtils ios = new IOUtils(new byte[44], 0);
//		ios.writeByte(27);
//		ios.writeByte(0);
//		ios.writeByte(0);
//		ios.writeByte(0);
//		
//		ios.writeByte(18);
//		ios.writeInt(4);		// 8
//		
//		ios.writeByte(0);
//		ios.writeByte(0); 		// 10
//		
//		ios.writeInt(6789);		// 14
//		ios.writeString("mysql-bin.000008");	// 16
//		
//		bss = ios.getBuffer();
//		os.write(bss);
//		os.flush();
//		os.write(0);
//		System.out.println(bss.length);

		
		final ComBinlogDumpPacket command = new ComBinlogDumpPacket();
		command.setBinlogFlag(0);
		command.setServerId(1234);
		command.setBinlogPosition(4);
		command.setBinlogFileName(StringColumn.valueOf("mysql-bin.000008".getBytes("utf-8")));

		os.write(new byte[]{27, 0, 0, 0});
		os.write(command.getPacketBody(), 0, 27);
		
		os.flush();
		
		x = in.read(bss, 0, 3);
//		System.out.println(x + ", " + in.read() + in.read() + in.read() + in.read());
		
		bss = new byte[100];
		in.read(bss, 0, 100);
		
		System.out.println(new String(bss));
		
//		while(true) {
//			x = in.read();
//			System.out.println(x);
//			bss = new byte[x];
//			in.read(bss, 0, x);
//			System.out.println(new String(bss));
//		}
	}
	
	public void dumpBinlog() {
		
		
	}
}
