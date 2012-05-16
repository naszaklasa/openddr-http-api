package pl.nk.warehouse.openddr.server;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class APIMonitoringHandler implements HttpHandler {

	public void handle(HttpExchange exchange) throws IOException {
		String requestMethod = exchange.getRequestMethod();
		if (requestMethod.equalsIgnoreCase("GET")) {
			Headers responseHeaders = exchange.getResponseHeaders();
			responseHeaders.set("Content-Type", "text/plain");
			exchange.sendResponseHeaders(200, 0);
			OutputStream responseBody = exchange.getResponseBody();
			responseBody.write("OK\n".getBytes());
			responseBody.write("translated useragents:".getBytes());
			responseBody.write(String.valueOf(OpenDDRServer.getTranslatedUAcount()).getBytes());
			responseBody.close();
		}
	}
}
