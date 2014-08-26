package me.xcoding.opencdc.utils;

public final class Assert {
	public static final boolean AssertAnd(int obj1, int obj2, int expect) {
		return ((obj1 & obj2) == expect);
	}
	
	public static final boolean AssertOr(int obj1, int obj2, int expect) {
		return ((obj1 | obj2) == expect);
	}
	
	public static final boolean AssertAnd(long obj1, long obj2, int expect) {
		return ((obj1 & obj2) == expect);
	}
	
	public static final boolean AssertOr(long obj1, long obj2, int expect) {
		return ((obj1 | obj2) == expect);
	}
}
