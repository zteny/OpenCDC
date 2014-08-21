package me.xcoding.opencdc.binlog.event;

import java.lang.reflect.Field;
import java.util.Arrays;

public abstract class Event {
	@Override
	public String toString() {
		Class<?> c = this.getClass();
		Field fs[] = c.getDeclaredFields();
		Field.setAccessible(fs, true);
		
		StringBuffer sb  = new StringBuffer(super.toString()).append("[");
		try {
			for(Field f : fs) {
				sb.append(f.getName()).append("=");
				if(f.getType().isArray()) {
					sb.append(Arrays.toString((byte[])f.get(this)));
				} else {
					sb.append(f.get(this));
				}
				sb.append(",");
			}
			sb.delete(sb.length()-1, sb.length());
			sb.append("]");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}
}
