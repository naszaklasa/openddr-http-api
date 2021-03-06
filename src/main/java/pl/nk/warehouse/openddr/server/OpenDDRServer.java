package pl.nk.warehouse.openddr.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import pl.nk.warehouse.openddr.common.Configuration;

import com.sun.net.httpserver.HttpServer;

/**
 * HTTP server listening on configured port - redirects all requests to OpenDDR API handler.
 * @author w
 * @since 2012.02.06
 *
 */
public class OpenDDRServer {
	private static long translatedUACount;
	
	public static void main(String[] args) throws IOException {
	    InetSocketAddress addr = new InetSocketAddress(Configuration.SERVER_PORT);
	    HttpServer server = HttpServer.create(addr, 0);
	    
	    APIHttpHandler apiHandler = new APIHttpHandler();
	    OpenDDRClient client = new OpenDDRClient();
	    
	    apiHandler.setOpenDDRClient(client);
	    
	    APIMonitoringHandler monitoringHandler = new APIMonitoringHandler();
	    
	    server.createContext("/", apiHandler);
	    server.createContext("/status", monitoringHandler);
	    server.setExecutor(Executors.newCachedThreadPool());
	    server.start();
	    System.out.println("Server is listening on port " + Configuration.SERVER_PORT );
	  }

	public static long getTranslatedUAcount() {
		return translatedUACount;
	}
	
	public static void incrementHandleCount(){
		++translatedUACount;
	}

}
