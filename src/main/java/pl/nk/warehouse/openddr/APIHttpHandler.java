package pl.nk.warehouse.openddr;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Simple requests handler - if parameter matches configured name, forwards useragent to OpenDDR API client
 * @author w
 *
 */
public class APIHttpHandler implements HttpHandler {
	private OpenDDRClient client;

	public void handle(HttpExchange exchange) throws IOException {
		System.out.println("new request");
		String requestMethod = exchange.getRequestMethod();
		if (requestMethod.equalsIgnoreCase("GET")) {
			Headers responseHeaders = exchange.getResponseHeaders();
			responseHeaders.set("Content-Type", "text/plain");
			exchange.sendResponseHeaders(200, 0);

			OutputStream responseBody = exchange.getResponseBody();

			URI url = exchange.getRequestURI();

			String query = url.getRawQuery();

			String[] params = query.split("=");
			for (int i = 0; i < params.length; ++i) {
				if (params[i].equals(Configuration.UA_PARAMETER)) {
					if (i + 1 < params.length) {
						System.out.println(params[i + 1]);
					}
				}
			}

			responseBody.close();
		}
	}
	
	public void setOpenDDRClient(OpenDDRClient client){
		this.client = client;
	}
}
