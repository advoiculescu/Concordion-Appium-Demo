package driver;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class Driver {
	private static AppiumDriver<WebElement> driver;

	/**
	 * Método para arrancar una instancia del driver.
	 * 
	 * @param version
	 * @param deviceName
	 * @param platformName
	 * @param appiumServerURL
	 * @throws MalformedURLException
	 */
	public static void startDriver(String version, String deviceName, String platformName, String appiumServerURL)
			throws MalformedURLException {
		System.out.print("Arrancando driver...");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("VERSION", version); // "4.4"
		capabilities.setCapability("deviceName", deviceName); // "my_android"
		capabilities.setCapability("platformName", platformName); // "Android"
		capabilities.setCapability("appPackage", "com.android.contacts"); // Paquete de la app
		capabilities.setCapability("appActivity", ".activities.PeopleActivity"); // Activity inicial
		driver = new AndroidDriver<WebElement>(new URL(appiumServerURL), capabilities);
		System.out.println("\u001B[32m" + " LISTO" + "\u001B[0m");
	}

	/**
	 * @return driver actual.
	 */
	public static WebDriver driver() {
		return driver;
	}

	/**
	 * @return el nombre de la activity actual.
	 */
	public static String getCurrentActivity() {
		return ((AndroidDriver<WebElement>) driver).currentActivity();
	}

	/**
	 * Verifica si un elemento existe o no en el DOM y devuelve true o false
	 * 
	 * @param by
	 * @return true o false dependiendo de si el elemento existe
	 */
	public static boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	/**
	 * Cierra la instancia del driver.
	 */
	public static void close() {
		// driver.close();
		driver.quit();
	}
}
