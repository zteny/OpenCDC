package me.xcoding.opencdc.net.connector;

public interface ISQLConnector extends Connector {

	public void query(String query) throws ConnectionException;

}
