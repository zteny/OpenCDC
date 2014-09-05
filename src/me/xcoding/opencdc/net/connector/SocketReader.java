package me.xcoding.opencdc.net.connector;

import java.io.IOException;
import java.io.InputStream;

import me.xcoding.opencdc.mysql.protocol.ReadablePacket;
import me.xcoding.opencdc.net.packet.generic.EOFPacket;
import me.xcoding.opencdc.net.packet.generic.ErrPacket;
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
	private int limit = 0;
	
	private int head = 0, tail = 0;
	private static final int bufferSize = 8192 << 2;
	public SocketReader(InputStream in) {
		super(8192 << 2);
		this.in = in;
		this.end = 0;
	}
	
	/**
	 * Binlog Network Stream 
	 * @return
	 * @throws IOException 
	 */
	public ReadablePacket buildPacket() throws IOException {
		if(end >= tail) {
			hasMore();
		}
		offset = end;
		int length = this.readFixedIntT3();
//		int sequenceId =
				this.readFixedIntT1();
	
		end = offset + length;
		return this;
	}
	
	public GenericPacket getResponsePacket() throws IOException {
		if(end >= tail) {
			hasMore();
		}
		
		offset = end;
		int length = this.readFixedIntT3();
//		int sequenceId = 
				this.readFixedIntT1();
		
		end = offset + length;
		int header = this.readFixedIntT1(); 
		
		switch(header) {
			case 0x0000 : { // OKPacket
				OKPacket packet = OKPacket.parser(this);
				return packet;
			} 
			case 0x00FE : { // EOFPacket
				EOFPacket packet = EOFPacket.parser(this);
				return packet;
			} 
			case 0x00FF : { // ERRPacket
				ErrPacket packet = ErrPacket.parser(this);
				return packet;
			}
			default : {
//				throw new xxExcpetion();
			}
		}
		return null;
	}
	
	public void setLimit(int length) {
		limit = length + offset;
	}
	
	@Override
	public boolean hasMore() throws IOException {
		if(bufferSize - tail < 8192) {
			System.arraycopy(buffer, offset, buffer, 0, (tail = tail - end));
			end = 0; offset = 0; 
		}
		tail += in.read(buffer, tail, 8192);
		offset = end;
		
		return (tail > head);
	}

	@Override
	public boolean hasNext() {
		if(offset >= tail)
			try {
				return hasMore();
			} catch (IOException e) {
			}
		return true;
	}
	
	public void position(int position) {
		this.offset = position;
	}

	@Override
	public int read() throws IOException {
		return 0;
	}

}
