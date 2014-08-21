package me.xcoding.opencdc.mysql.protocol.column;

public class ColumnDef {
	private int type;
	private int meta;
	
	public ColumnDef(int type, int meta) {
		
	}
	
	ColumnDef valueOf(int type, int meta) {
		this.type = type & 0x001F;
		this.meta = meta;
		return this;
	}
	
	public int getType() {
		return type;
	}
	
	public int getMeta() {
		return meta;
	}
}
