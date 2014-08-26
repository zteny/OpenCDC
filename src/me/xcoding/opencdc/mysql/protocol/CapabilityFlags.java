package me.xcoding.opencdc.mysql.protocol;

public interface CapabilityFlags {
	public static final int CLIENT_LONG_PASSWORD 	= 0x00000001; 	// new more secure passwords
	public static final int CLIENT_FOUND_ROWS 		= 0x00000002; 	// Found instead of affected rows
	public static final int CLIENT_LONG_FLAG 		= 0x00000004; 	// Get all column flags
	public static final int CLIENT_CONNECT_WITH_DB 	= 0x00000008; 	// One can specify db on connect
	public static final int CLIENT_NO_SCHEMA 		= 0x00000010; 	// Don't allow database.table.column
	public static final int CLIENT_COMPRESS 		= 0x00000020; 	// Can use compression protocol
	public static final int CLIENT_ODBC 			= 0x00000040; 	// Odbc client
	public static final int CLIENT_LOCAL_FILES 		= 0x00000080; 	// Can use LOAD DATA LOCAL
	public static final int CLIENT_IGNORE_SPACE		= 0x00000100; 	// Ignore spaces before ''
	public static final int CLIENT_PROTOCOL_41 		= 0x00000200; 	// New 4.1 protocol
	public static final int CLIENT_INTERACTIVE 		= 0x00000400; 	// This is an interactive client
	public static final int CLIENT_SSL 				= 0x00000800; 	// Switch to SSL after handshake
	public static final int CLIENT_IGNORE_SIGPIPE 	= 0x00001000; 	// IGNORE sigpipes
	public static final int CLIENT_TRANSACTIONS 	= 0x00002000; 	// Client knows about transactions
	public static final int CLIENT_RESERVED 		= 0x00004000; 	// Old flag for 4.1 protocol 
	public static final int CLIENT_SECURE_CONNECTION = 0x00008000; 	// New 4.1 authentication
	public static final int CLIENT_MULTI_STATEMENTS = 0x00010000;	// Enable/disable multi-stmt support
	public static final int CLIENT_MULTI_RESULTS 	= 0x00020000;	// Enable/disable multi-results
	public static final int CLIENT_PS_MULTI_RESULTS = 0x00040000; 	// Multi-results in PS-protocol
	public static final int CLIENT_PLUGIN_AUTH	 	= 0x00080000; 	// Client supports plugin authentication
	public static final int CLIENT_SSL_VERIFY_SERVER_CERT = 0x00100000;
	public static final int CLIENT_REMEMBER_OPTIONS = 0x00200000;
}
