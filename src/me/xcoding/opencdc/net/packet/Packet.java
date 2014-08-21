package me.xcoding.opencdc.net.packet;

public abstract class Packet implements Packetable {
	int length;
	int commandPhase;
	int sequence;
	
	byte[] body;
}
