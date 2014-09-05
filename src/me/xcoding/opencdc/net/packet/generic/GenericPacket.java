package me.xcoding.opencdc.net.packet.generic;

import java.lang.reflect.Field;
import java.util.Arrays;

public abstract class GenericPacket {
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(super.toString()).append("{");
		
		Class<?> c = this.getClass();
		Field fs[] = c.getDeclaredFields();
		
		Field.setAccessible(fs, true);
		try {
			for(Field f : fs) {
				sb.append(f.getName()).append(" = ");
				if(f.getType().isArray()) {
					sb.append(Arrays.toString((byte[])f.get(this))).append(", \n");
				} else {
					sb.append(f.get(this)).append(", \n");
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}
}
