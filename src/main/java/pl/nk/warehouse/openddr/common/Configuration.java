package pl.nk.warehouse.openddr.common;

public class Configuration {
	public static int SERVER_PORT = 6522;
	public static String SERVER_HOST = "localhost";
	public static String UA_PARAMETER = "ua";
	
	public static void setServerHost(String serverHost){
		SERVER_HOST = serverHost;
	}
	
}
