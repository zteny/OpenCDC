package me.xcoding.test.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class SocketClient {
	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("localhost", 5566);
		socket.setSendBufferSize(50);
		socket.setReceiveBufferSize(50);
		
//		SocketAddress socketAddress = InetSocketAddress.createUnresolved("127.0.0.1", 5566);
		
//		socket.connect();
		
		InputStream is = socket.getInputStream();
		OutputStream os = socket.getOutputStream();
		
		System.out.println(socket.getReceiveBufferSize());
		
		new Thread(new XIN(is)).start();
//		new Thread(new XOUT(os)).start();`
	}
}
class XIN implements Runnable {
	private final InputStream in;
	
	XIN(InputStream in) {
		this.in = in;
	}

	@Override
	public void run() {
		while(true) {
			try {
				byte[] bs = new byte[200];
				
				System.out.println(in.read(bs, 0, 100));
				
				TimeUnit.SECONDS.sleep(2);
			} catch (Exception e){
				
			}
		}
	}
}

class XOUT implements Runnable {
	public XOUT(OutputStream out) {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
