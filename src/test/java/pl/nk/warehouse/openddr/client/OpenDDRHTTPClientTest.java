package pl.nk.warehouse.openddr.client;

import org.junit.Before;

import pl.nk.warehouse.openddr.client.OpenDDRHTTPClient;

public class OpenDDRHTTPClientTest {
	OpenDDRHTTPClient client;
	
	@Before
	public void setUp(){
		client = new OpenDDRHTTPClient();
	}

}
