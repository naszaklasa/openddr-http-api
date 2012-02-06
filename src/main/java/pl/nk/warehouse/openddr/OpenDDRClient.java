package pl.nk.warehouse.openddr;

import java.util.HashMap;
import java.util.Properties;

import org.w3c.ddr.simple.Service;
import org.w3c.ddr.simple.ServiceFactory;
import org.openddr.simpleapi.oddr.ODDRService;
import org.openddr.simpleapi.oddr.model.ODDRHTTPEvidence;
import org.w3c.ddr.simple.Evidence;
import org.w3c.ddr.simple.PropertyRef;
import org.w3c.ddr.simple.PropertyValue;
import org.w3c.ddr.simple.PropertyValues;
import org.w3c.ddr.simple.exception.NameException;
import org.w3c.ddr.simple.exception.ValueException;

/**
 * OpenDDR API cilent. 1. Loads all required datasources (information about
 * devices)
 * 2. 
 * 
 * @author w
 * 
 */
public class OpenDDRClient {

	private Service identificationService = null;
	
	private PropertyRef vendorRef;
	private PropertyRef modelRef;
	private PropertyRef displayWidthRef;
	private PropertyRef displayHeightRef;
	
	private PropertyRef[] propertyRefs;
	
	Evidence e;
	
	public OpenDDRClient() {
		System.out.println("Initialize filter");
		Properties initializationProperties = new Properties();

		try {
			initializationProperties.load(getClass().getResourceAsStream("/oddr.properties"));
			identificationService = ServiceFactory.newService("org.openddr.simpleapi.oddr.ODDRService", initializationProperties.getProperty(ODDRService.ODDR_VOCABULARY_IRI), initializationProperties);

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}

		try {
			vendorRef = identificationService.newPropertyRef("vendor");
			modelRef = identificationService.newPropertyRef("model");
			displayWidthRef = identificationService.newPropertyRef("displayWidth");
			displayHeightRef = identificationService.newPropertyRef("displayHeight");

		} catch (NameException ex) {
			throw new RuntimeException(ex);
		}

		propertyRefs = new PropertyRef[] { vendorRef, modelRef, displayWidthRef, displayHeightRef };
		
	}

	public HashMap<String, String> getAttributes(String useragent) {

		e = new ODDRHTTPEvidence();
		e.put("User-Agent", useragent);

		HashMap<String, String> hm = new HashMap<String, String>();

		try {
			PropertyValues propertyValues = identificationService.getPropertyValues(e, propertyRefs);
			PropertyValue vendor = propertyValues.getValue(vendorRef);
			PropertyValue model = propertyValues.getValue(modelRef);
			PropertyValue displayWidth = propertyValues.getValue(displayWidthRef);
			PropertyValue displayHeight = propertyValues.getValue(displayHeightRef);

			hm.put("vendor", vendor.getString());
			hm.put("model", model.getString());
			hm.put("displayWidth", displayWidth.getString());
			hm.put("displayHeight", displayHeight.getString());

		} catch (NameException ex) {
			throw new RuntimeException(ex);
		} catch (ValueException ex) {
			throw new RuntimeException(ex);
		}
		
		return hm;
	}

}
