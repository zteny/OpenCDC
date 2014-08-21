package me.xcoding.opencdc.mysql.protocol.column;

import java.util.Arrays;

public class ColumnList {
	private static final int[] bitmask = new int[] {
		1<<0, 1<<1, 1<<2, 1<<3, 1<<4, 1<<5, 1<<6, 1<<7
	};
	private final Column[] columns; // before
	
	private final int numberOfColumns;
	
	private final byte[] nullBitmask;
	private final byte[] columnTypeDef;
	private final byte[] columnMetaDef;

	private static final int FF = 0x000000FF;
	private final ColumnDef columnDef = new ColumnDef(0, 0);
	
 	public ColumnList(byte[] columnTypeDef, byte[] columnMetaDef, byte[] nullBitmask) {
 		this.nullBitmask = nullBitmask;
		this.columnTypeDef = columnTypeDef;
		this.columnMetaDef = columnMetaDef;

		this.numberOfColumns = columnTypeDef.length;
		this.columns = new Column[numberOfColumns];
	}
 	
 	// FIXME 如此之挫，求改。
 	boolean[] _null; 
 	public ColumnList init(byte[] columnsPresent, byte[] nullBitmap) {
 		_null = new boolean[columnsPresent.length << 3];
 		
 		int c = 0;
 		for(byte b : nullBitmap) {
 			for(int pos=0; pos<8; pos++, c++) {
 				_null[c] = ((b & bitmask[pos]) == 1);
 			}
 		}
 		
 		return this;
 	}
 	
 	public boolean isNull() {
 		// check border?
 		return _null[curColumn];
 	} 
  	
 	
 	public boolean isNull(int cIndex) {
 		// check border?
 		return _null[cIndex];
 	} 
  	
 	int curColumn = -1;
 	public boolean hasNext() {
 		return ++curColumn < numberOfColumns;
 	}
 	
 	public ColumnDef next() {
 		return columnDef.valueOf(
 				(columnTypeDef[curColumn] & 0x1F),
 				parser()
 			);
 	}
 	
 	public void add(Column column) {
 		columns[curColumn] = column;
 	}
 	
 	public void add(int type, Object value) {
 		add(new Column(type, value));
 	}
 	
 	public Column[] toArray() {
 		return columns;
 	}
 	
 	private int offset = 0;
 	private int parser() {
 		int v = MetaDataParser.lengthOf[columnTypeDef[curColumn] & 0x1F];
 		if(v == 0) {
 			return 0;
 		}
 		return v==1?(columnMetaDef[offset++] & FF) : 
 			((columnMetaDef[offset++] & FF) | ((columnMetaDef[offset++] & FF) << 8)); 
 	}
 	
 	@Override
 	public String toString() {
 		return Arrays.toString(columns);
 	}
}
