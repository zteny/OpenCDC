package me.xcoding.test.socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class SocketServer {
	public static void main(String[] args) throws Exception {
		ServerSocket server = new ServerSocket(5566);
		
//		socket.setSendBufferSize(1000);
		
		Socket socket = server.accept();
		server.setReceiveBufferSize(44);
		socket.setSendBufferSize(44);
		
		InputStream is = socket.getInputStream();
		OutputStream os = socket.getOutputStream();
		
		
		while(true ) {
			os.write(new byte[60]);
			
			TimeUnit.SECONDS.sleep(2); 

			os.write(new byte[12]);
		}
	}
}
