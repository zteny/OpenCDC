package me.xcoding.opencdc.binlog;

import java.nio.charset.Charset;

import me.xcoding.opencdc.mysql.protocol.column.ColumnList;

/**
 * Context of Event
 * @author Teny Zh(zh.Teny.1@gmail.com)
 *
 */
public class EventContext {
	private Charset charset;
	private ColumnList columns1;// = new ColumnList(new byte[]{}, new byte[]{});
	
	private ColumnList columns2;
	private int version;
	
	private String versionName;

	public void valueOf(ColumnList columns1, ColumnList columns2) {
		this.columns1 = columns1;
		this.columns2 = columns2;
	}
	
	public Charset getCharset() {
		return charset;
	}
	
	public int getVersion() {
		return version;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setCharset(Charset charset) {
		this.charset = charset;
	}

	public void setCharset(String charsetName) {
		this.charset = Charset.forName(charsetName);
	}

	public ColumnList getAfterColumns() {
		return columns2;
	}
	
	public ColumnList getBeforeColumns() {
		return columns1;
	}
	
	public void setVersion(int version) {
		this.version = version;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

}
