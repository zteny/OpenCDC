package me.xcoding.opencdc.net;

public class Packet {
	private byte[] buffer;
	private int position;
	private int capability;
	
	public Packet(int length) {
		
	}
	
	public Packet() {
		
	}
	
	public Packet(byte[] packet) {
		this.buffer = packet;
		capability = packet.length;
	}
	
}
