package me.xcoding.opencdc.binlog.event;

import me.xcoding.opencdc.mysql.protocol.column.ColumnList;

/**
 * <b> Rows Event </b>
 * </br>
 * <table> <tr align="center">
 * <td> Event </td> <td> SQL Command </td> <td> rows Contents </td>
 * </tr><tr>
 * <td> WRITE_ROWS_EVENT </td> <td align="center"> INSERT </td> <td> the row data to insert </td>
 * </tr><tr>
 * <td> DELETE_ROWS_EVENT </td> <td align="center"> DELETE </td> <td> as much data as needed to identify a row </td>
 * </tr><tr>
 * <td> UPDATE_ROWS_EVENT </td> <td align="center"> UPDATE </td> <td> as much data as needed to identify a row + the data to change </td>
 * </tr>
 * </table>
 * 
 * </br></br>
 * <ol>
 * <li>Version 0 </li>
 * 		</br>written form MySQL 5.1.0 to 5.1.15</br>
 * <li>Version 1 </li>
 * 		</br>written form MySQL 5.1.15 to 5.6.x</br>
 * <li>Version 2 </li>
 * 		</br>written form MySQL 5.6.x</br>
 * </ol>
 * 
 * 
 * 
 * @author Teny Zh(zh.Teny.1@gmail.com)
 * @see http://dev.mysql.com/doc/internals/en/rows-event.html
 */
public abstract class RowsEvent extends Event {
//	header :
	protected long tableId;
	protected int flags;
	
	protected int extraDataLength;
	protected byte[] extraData;
	
//	body :
	protected int numberOfColumns;
	protected byte[] columnsPresent;
	protected byte[] columnsPresent2;
	protected byte[] nullBitmap;
	
//	rows : 
	protected ColumnList columns1; // after
	protected ColumnList columns2; // before
	public long getTableId() {
		return tableId;
	}
	public int getFlags() {
		return flags;
	}
	public int getExtraDataLength() {
		return extraDataLength;
	}
	public byte[] getExtraData() {
		return extraData;
	}
	public int getNumberOfColumns() {
		return numberOfColumns;
	}
	public byte[] getColumnsPresent() {
		return columnsPresent;
	}
	public byte[] getNullBitmap() {
		return nullBitmap;
	}
	public ColumnList getColumns1() {
		return columns1;
	}
	public ColumnList getColumns2() {
		return columns2;
	}
	public RowsEvent setTableId(long tableId) {
		this.tableId = tableId;
		return this;
	}
	public RowsEvent setFlags(int flags) {
		this.flags = flags;
		return this;
	}
	public RowsEvent setExtraDataLength(int extraDataLength) {
		this.extraDataLength = extraDataLength;
		return this;
	}
	public RowsEvent setExtraData(byte[] extraData) {
		this.extraData = extraData;
		return this;
	}
	public RowsEvent setNumberOfColumns(int numberOfColumns) {
		this.numberOfColumns = numberOfColumns;
		return this;
	}
	public RowsEvent setColumnsPresent(byte[] columnsPresent) {
		this.columnsPresent = columnsPresent;
		return this;
	}
	public RowsEvent setNullBitmap(byte[] nullBitmap) {
		this.nullBitmap = nullBitmap;
		return this;
	}
	public RowsEvent setColumns1(ColumnList columns1) {
		this.columns1 = columns1;
		return this;
	}
	public RowsEvent setColumns2(ColumnList columns2) {
		this.columns2 = columns2;
		return this;
	}
	
}
