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
 * devices) 2.
 * 
 * @author w
 * 
 */
public class OpenDDRClient {

	private Service identificationService = null;
	Properties initializationProperties;

	private PropertyRef vendorRef;
	private PropertyRef modelRef;
	private PropertyRef displayWidthRef;
	private PropertyRef displayHeightRef;
	private PropertyRef marketingNameRef;
	private PropertyRef deviceOSVersionRef;
	private PropertyRef deviceOSRef;
	private PropertyRef fullFlashSupportRef;
	private PropertyRef mobileBrowserRef;
	private PropertyRef wifiRef;
	private PropertyRef releaseDateRef;
	private PropertyRef mobileBrowserVersionRef;
	private PropertyRef inputDevicesRef;
//	private PropertyRef parentRef;
	private PropertyRef idRef;

	private PropertyRef[] propertyRefs;

	Evidence e;

	public OpenDDRClient() {
		System.out.println("Initializing API");
		initializationProperties = new Properties();

		try {
			initializationProperties.load(getClass().getClassLoader().getResourceAsStream("oddr.properties"));
			identificationService = ServiceFactory.newService("org.openddr.simpleapi.oddr.ODDRService", initializationProperties.getProperty(ODDRService.ODDR_VOCABULARY_IRI), initializationProperties);

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}

		try {
			vendorRef = identificationService.newPropertyRef("vendor");
			modelRef = identificationService.newPropertyRef("model");
			displayWidthRef = identificationService.newPropertyRef("displayWidth");
			displayHeightRef = identificationService.newPropertyRef("displayHeight");
			marketingNameRef = identificationService.newPropertyRef("marketing_name");
			deviceOSVersionRef = identificationService.newPropertyRef("device_os_version");
			deviceOSRef = identificationService.newPropertyRef("device_os");
			fullFlashSupportRef = identificationService.newPropertyRef("full_flash_support");
			mobileBrowserRef = identificationService.newPropertyRef("mobile_browser");
			wifiRef = identificationService.newPropertyRef("wifi");
			releaseDateRef = identificationService.newPropertyRef("release_date");
			mobileBrowserVersionRef = identificationService.newPropertyRef("mobile_browser_version");
			inputDevicesRef = identificationService.newPropertyRef("inputDevices");
			idRef = identificationService.newPropertyRef("id");
//			parentRef = identificationService.newPropertyRef("parentId");

		} catch (NameException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}

		propertyRefs = new PropertyRef[] { vendorRef, modelRef, displayWidthRef, displayHeightRef, marketingNameRef, deviceOSVersionRef, deviceOSRef, fullFlashSupportRef, mobileBrowserRef, wifiRef, releaseDateRef, mobileBrowserVersionRef, inputDevicesRef, idRef };

		System.out.println("API initialized");
	}

	public HashMap<String, String> getAttributes(String useragent) {

		System.out.println("new query, ua:" + useragent);

		e = new ODDRHTTPEvidence();
		e.put("User-Agent", useragent);

		HashMap<String, String> hm = new HashMap<String, String>();

		try {
			PropertyValues propertyValues = identificationService.getPropertyValues(e, propertyRefs);
			PropertyValue vendor = propertyValues.getValue(vendorRef);
			PropertyValue model = propertyValues.getValue(modelRef);
			PropertyValue displayWidth = propertyValues.getValue(displayWidthRef);
			PropertyValue displayHeight = propertyValues.getValue(displayHeightRef);
			PropertyValue marketingName = propertyValues.getValue(marketingNameRef);
			PropertyValue deviceOSVersion = propertyValues.getValue(deviceOSVersionRef);
			PropertyValue deviceOS = propertyValues.getValue(deviceOSRef);
			PropertyValue fullFlashSupport = propertyValues.getValue(fullFlashSupportRef);
			PropertyValue mobileBrowser = propertyValues.getValue(mobileBrowserRef);
			PropertyValue wifi = propertyValues.getValue(wifiRef);
			PropertyValue releaseDate = propertyValues.getValue(releaseDateRef);
			PropertyValue mobileBrowserVersion = propertyValues.getValue(mobileBrowserVersionRef);
			PropertyValue inputDevices = propertyValues.getValue(inputDevicesRef);
			PropertyValue id = propertyValues.getValue(idRef);
//			PropertyValue parent = propertyValues.getValue(parentRef);

			if (vendor.exists()) {
				hm.put("vendor", vendor.getString());
			}
			if (model.exists()) {
				hm.put("model", model.getString());
			}
			if (displayWidth.exists()) {
				hm.put("displayWidth", displayWidth.getString());
			}
			if (displayHeight.exists()) {
				hm.put("displayHeight", displayHeight.getString());
			}
			if (marketingName.exists()) {
				hm.put("marketingName", marketingName.getString());
			}
			if (deviceOSVersion.exists()) {
				hm.put("deviceOSVersion", deviceOSVersion.getString());
			}
			if (deviceOS.exists()) {
				hm.put("deviceOS", deviceOS.getString());
			}
			if (fullFlashSupport.exists()) {
				hm.put("fullFlashSupport", fullFlashSupport.getString());
			}
			if (mobileBrowser.exists()) {
				hm.put("mobileBrowser", mobileBrowser.getString());
			}
			if (wifi.exists()) {
				hm.put("wifi", wifi.getString());
			}
			if (releaseDate.exists()) {
				hm.put("releaseDate", releaseDate.getString());
			}
			if (mobileBrowserVersion.exists()) {
				hm.put("mobileBrowserVersion", mobileBrowserVersion.getString());
			}
			if (inputDevices.exists()) {
				hm.put("inputDevices", inputDevices.getString());
			}
			if (id.exists()) {
				hm.put("id", id.getString());
			}
//			if (parent.exists()) {
//				hm.put("parent", parent.getString());
//			}
			

		} catch (NameException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} catch (ValueException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return hm;

	}

}
