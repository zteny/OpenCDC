package me.xcoding.opencdc.net.connector;

public interface IDumpConnector extends Connector {
	void dumpBinlog() throws ConnectionException;
}
