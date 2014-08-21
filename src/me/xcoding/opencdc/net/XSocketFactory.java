package me.xcoding.opencdc.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.SocketFactory;

public class XSocketFactory extends SocketFactory {

	@Override
	public Socket createSocket(String host, int port) throws IOException,
			UnknownHostException {
		
		Socket socket = new Socket(host, port);
		socket.getKeepAlive();
//		socket.
		
		return null;
	}

	@Override
	public Socket createSocket(InetAddress host, int port) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Socket createSocket(String host, int port, InetAddress localHost,
			int localPort) throws IOException, UnknownHostException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Socket createSocket(InetAddress address, int port,
			InetAddress localAddress, int localPort) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
