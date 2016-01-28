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

	public static void startDriver(String version, String deviceName, String platformName, String appiumServerURL)
			throws MalformedURLException {
		System.out.print("Arrancando driver...");
		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability("VERSION", version); // "4.4"
		capabilities.setCapability("deviceName", deviceName); // "my_android"
		capabilities.setCapability("platformName", platformName); // "Android"

		capabilities.setCapability("appPackage", "com.android.contacts");
		capabilities.setCapability("appActivity", ".activities.PeopleActivity");

		driver = new AndroidDriver<WebElement>(new URL(appiumServerURL), capabilities);
		// "http://127.0.0.1:4723/wd/hub"
		System.out.println("\u001B[32m" + " LISTO" + "\u001B[0m");
	}

	public static WebDriver driver() {
		return driver;
	}

	public static String getCurrentActivity() {
		return ((AndroidDriver<WebElement>) driver).currentActivity();
	}

	public static boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public static void close() {
		// driver.close();
		driver.quit();
	}
}
