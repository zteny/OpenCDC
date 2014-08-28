package me.xcoding.opencdc.net.connector;

import java.io.IOException;
import java.io.InputStream;

import me.xcoding.opencdc.mysql.protocol.ReadablePacket;
import me.xcoding.opencdc.net.packet.generic.GenericPacket;
import me.xcoding.opencdc.net.packet.generic.OKPacket;

/**
 * FIXME 
 * If a MySQL client or server wants to send data, it: </br>
 * 		Splits the data into packets of size (224–1) bytes</br>
 * 		Prepends to each chunk a packet header</br>
 * 
 * @author Teny Zh
 * @see http://dev.mysql.com/doc/internals/en/overview.html
 */
public class SocketReader extends ReadablePacket {
	private final InputStream in;
	
	public SocketReader(InputStream in) {
		this.in = in;
	}
	
	public ReadablePacket buildPacket() {
		return this;
	}
	
	public GenericPacket getResponsePacket() {
		int length = this.readFixedIntT3();
		int seqeunceId = this.readFixedIntT1();
		
		// header indicator
		int header = this.readFixedIntT1(); 
		
		switch(header) {
			case 0x0000 : { // OKPacket
				// setLimit(); 是不是应该有个 max length呢？以免，过度消费。
				OKPacket packet = OKPacket.parser(this);
				return packet;
			} 
			case 0x00FE : { // EOFPacket
				
				break;
			} 
			case 0x00FF : { // ERRPacket
				
				break;
			}
			default : {
//				throw new xxExcpetion();
			}
		}
		return null;
	}
	
	@Override
	public boolean hasMore() throws IOException {
		return false;
	}

	@Override
	public boolean hasNext() {
		return false;
	}

	@Override
	public int read() throws IOException {
		return 0;
	}

}
