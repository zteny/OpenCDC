package me.xcoding.opencdc.mysql.protocol.column;

/**
 * 
 * @author Teny Zh(zh.Teny.1@gmail.com)
 *
 */
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
		
		sb.append("{type=").append(ColumnType.NAMES[type]).append(", value=");
		if(value != null && value.getClass().isArray()) {
			sb.append(new String((byte[])value)).append("}"); // FIXME
		} else {
			sb.append(String.valueOf(value)).append("}");
		}
		
		return sb.toString();
	}
}
