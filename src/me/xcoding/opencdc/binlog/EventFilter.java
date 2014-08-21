package me.xcoding.opencdc.binlog;

public class EventFilter {
	boolean[] filter  = new boolean[0x0023];
	
	public boolean isFilter(int typecode) {
		return filter[typecode];
	}
}
