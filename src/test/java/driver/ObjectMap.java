package driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;

import io.appium.java_client.MobileBy;

public class ObjectMap {
	Properties properties;

	/**
	 * Instancia la clase ObjectMap
	 * 
	 * @param mapFile
	 */
	public ObjectMap(String mapFile) {
		properties = new Properties();
		try {
			FileInputStream in = new FileInputStream(mapFile);
			properties.load(in);
			in.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Lee el archivo definido en el método ObjectMap y obtiene el locator
	 * (localizador) en base al nombre del elemento pasado por parámetro.
	 * 
	 * 
	 * @param logicalElementName
	 * @return Un objeto de tipo By, que incluye el valor del locator.
	 * @throws Exception
	 */
	public By getLocator(String logicalElementName) throws Exception {
		// Leemos el valor usando el nombre lógico como clave
		// (obtenemos todo lo que está a la derecha del simbolo = )
		String locator = properties.getProperty(logicalElementName);

		// Dividimos el tipo y el valor del locator
		String locatorType = locator.split(">")[0];
		String locatorValue = locator.split(">")[1];

		// Devuelve una instancia de clase By basada en el tipo de locator
		if (locatorType.toLowerCase().equals("id"))
			return MobileBy.id(locatorValue);
		else if (locatorType.toLowerCase().equals("name"))
			return MobileBy.name(locatorValue);
		else if ((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class")))
			return MobileBy.className(locatorValue);
		else if ((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag")))
			return MobileBy.tagName(locatorValue);
		else if ((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link")))
			return MobileBy.linkText(locatorValue);
		else if (locatorType.toLowerCase().equals("partiallinktext"))
			return MobileBy.partialLinkText(locatorValue);
		else if ((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css")))
			return MobileBy.cssSelector(locatorValue);
		else if (locatorType.toLowerCase().equals("xpath"))
			return MobileBy.xpath(locatorValue);
		else if (locatorType.toLowerCase().equals("accessibilityid"))
			return MobileBy.AccessibilityId(locatorValue);
		else
			throw new Exception("Tipo de locator '" + locatorType + "' no definido!");
	}

	/**
	 * Devuelve el tipo de locator (localizador).
	 * 
	 * @param logicalElementName
	 * @return
	 * @throws Exception
	 */
	public String getLocatorType(String logicalElementName) throws Exception {
		String locator = properties.getProperty(logicalElementName);
		String locatorType = locator.split(">")[0];
		return locatorType;
	}

	/**
	 * Devuelve el valor del locator (localizador).
	 * 
	 * @param logicalElementName
	 * @return
	 * @throws Exception
	 */
	public String getLocatorValue(String logicalElementName) throws Exception {
		String locator = properties.getProperty(logicalElementName);
		String locatorValue = locator.split(">")[1];
		return locatorValue;
	}
}
