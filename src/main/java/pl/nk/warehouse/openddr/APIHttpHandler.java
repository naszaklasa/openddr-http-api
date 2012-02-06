package pl.nk.warehouse.openddr;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;

import com.google.gson.Gson;
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
						
						String ua = URLDecoder.decode(params[i+1], "UTF-8");
						
						HashMap<String, String> hm = client.getAttributes(ua);
						
						Gson gson = new Gson();
						String result = gson.toJson(hm);
						
						System.out.println(result);
						responseBody.write(result.getBytes());
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
