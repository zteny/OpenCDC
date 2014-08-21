package me.xcoding.opencdc.mysql.protocol.column;

public class Column {
	public final int type;
	public final Object value;
	
	public Column(int type, Object value) {
		this.type = type;
		this.value = value;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("{type=").append(type).append(", value=")
			.append(String.valueOf(value)).append("}");
		
		return sb.toString();
	}
}
