package me.xcoding.opencdc.net.packet;

/**
 * 
 * @author Teny ZH (zh.Teny.1@gmail.com)
 *
 */
public interface Packetable {
	void setBody(byte[] body);
	
	byte[] getBody();

	void setLength(int length);
	
	int getLength();
	
	/** 
	 * In the command phase, 
	 * the client sends a command packet with the sequence-id [00]
	 */
	void setSequence(int sequence);
	
	int getSequence();
	
	void setCommandPhase(int commandPhase);
	
	int getCommandPhase();
	
	byte[] toBytes();
}
