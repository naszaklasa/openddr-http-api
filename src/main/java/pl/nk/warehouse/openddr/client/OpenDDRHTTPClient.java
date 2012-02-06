package pl.nk.warehouse.openddr.client;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import pl.nk.warehouse.openddr.common.Configuration;

/**
 * HTTP Client that queries configured server for attributes of given useragent
 * 
 * @author w
 * 
 */
public class OpenDDRHTTPClient {
	
	public static void main(String[] args){
		OpenDDRHTTPClient c = new OpenDDRHTTPClient();
		System.out.println("created");
		String useragent = "Mozilla/5.0%20(iPhone;%20U;%20CPU%20iPhone%20OS%204_2_1%20like%20Mac%20OS%20X;%20pl-pl)%20AppleWebKit/533.17.9%20(KHTML,%20like%20Gecko)%20Version/5.0.2%20Mobile/8C148%20Safari/6533.18.5";
		//String useragent = "Mozilla/5.0%20(Linux;%20U;%20Android%202.3.3;%20pl-pl;%20GT-I9100%20Build/GINGERBREAD)%20AppleWebKit/533.1%20(KHTML,%20like%20Gecko)%20Version/4.0%20Mobile%20Safari/533.1";
		try {
			c.getAttributest(useragent);
			System.out.println("got attributes");
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getAttributest(String useragent) throws ClientProtocolException, IOException {
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet("http://" + Configuration.SERVER_HOST + ":" + Configuration.SERVER_PORT + "/?" + Configuration.UA_PARAMETER + "=" + useragent);
		System.out.println(httpget.getURI());
		HttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();
		
		String returnedJson = null;
		
		if (entity != null) {
			returnedJson = EntityUtils.toString(entity);
		}
		
		System.out.println(returnedJson);
	}

}
