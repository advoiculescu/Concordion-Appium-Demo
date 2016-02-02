package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import driver.Driver;
import driver.ObjectMap;
import io.appium.java_client.MobileElement;

public class ContactEditorActivity {
	/**
	 * 
	 * El objeto objectMap, que lee el archivo .properties con los locators.
	 */
	private ObjectMap map;

	/**
	 * Nombre de la activity actual. (Una activity es una interfaz de una app
	 * Android)
	 */
	String activityName = ".activities.ContactEditorActivity";

	/**
	 * Instancia el objeto objectMap para leer los locators desde el archivo
	 * .properties
	 */
	public ContactEditorActivity() {
		map = new ObjectMap("src\\test\\resources\\pageObjects\\ContactEditorActivity.properties");
	}

	/**
	 * Verifica si la activity se muestra actualmente en la aplicación
	 * 
	 * @return True o False dependiendo si se muestra dicha activity
	 */
	public boolean isLoaded() {
		if (activityName.equals(Driver.getCurrentActivity())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Sincroniza e introduce el nombre pasado por parámetro en el campo Name.
	 * 
	 * @param name
	 * @throws Exception
	 */
	public void setNameField(String name) throws Exception {
		System.out.print("Insertando " + name + " en 'Name'...");
		WebElement nameField = (MobileElement) (new WebDriverWait(Driver.driver(), 10))
				.until(new ExpectedCondition<WebElement>() {
					public WebElement apply(WebDriver d) {
						try {
							return d.findElement(map.getLocator("nameField"));
						} catch (Exception e) {
							return null;
						}
					}
				});
		nameField.sendKeys(name);
		System.out.println("\u001B[32m" + " LISTO" + "\u001B[0m");
	}

	/**
	 * Introduce el número de teléfono pasado por parámetro en el campo Phone.
	 * 
	 * @param phone
	 * @throws Exception
	 */
	public void setPhoneField(String phone) throws Exception {
		System.out.print("Insertando " + phone + " en 'Phone'...");
		WebElement phoneField = Driver.driver().findElement(map.getLocator("phoneField"));
		phoneField.sendKeys(phone);
		System.out.println("\u001B[32m" + " LISTO" + "\u001B[0m");
	}

	/**
	 * Pulsa el botón Done situado en la parte superior izquierda de la pantalla.
	 * 
	 * @throws Exception
	 */
	public void clickDoneButton() throws Exception {
		System.out.print("Click en 'Done'...");
		WebElement doneButton = Driver.driver().findElement(map.getLocator("doneButton"));
		doneButton.click();
		System.out.println("\u001B[32m" + " LISTO" + "\u001B[0m");
	}
}
