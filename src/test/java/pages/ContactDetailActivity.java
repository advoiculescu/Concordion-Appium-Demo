package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import driver.Driver;
import driver.ObjectMap;
import io.appium.java_client.MobileElement;

public class ContactDetailActivity {
	/**
	 * El objeto objectMap, que lee el archivo .properties con los locators.
	 */
	private ObjectMap map;

	/**
	 * Nombre de la activity actual. (Una activity es una interfaz de una app
	 * Android)
	 */
	String activityName = ".activities.ContactDetailActivity";

	/**
	 * Instancia el objeto objectMap para leer los locators desde el archivo
	 * .properties
	 */
	public ContactDetailActivity() {
		map = new ObjectMap("data\\PageObjects\\ContactDetailActivity.properties");
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
	 * Sincroniza y devuelve el nombre del contacto actual.
	 * 
	 * @return String con el nombre del contacto
	 * @throws Exception
	 */
	public String getName() throws Exception {
		WebElement contactName = (MobileElement) (new WebDriverWait(Driver.driver(), 10))
				.until(new ExpectedCondition<WebElement>() {
					public WebElement apply(WebDriver d) {
						try {
							return d.findElement(map.getLocator("contactName"));
						} catch (Exception e) {
							return null;
						}
					}
				});
		return contactName.getText();
	}

	/**
	 * Devuelve el número del contacto actual
	 * 
	 * @return String con el número del contacto
	 * @throws Exception
	 */
	public String getPhone() throws Exception {
		WebElement contactPhone = Driver.driver().findElement(map.getLocator("contactPhone"));
		return contactPhone.getText();
	}

	/**
	 * Pulsa el botón atrás (<), situado a la izquierda del nombre del contacto.
	 * 
	 * @throws Exception
	 */
	public void clickBackButton() throws Exception {
		System.out.print("Click en Back (<) ...");
		WebElement backButton = Driver.driver().findElement(map.getLocator("backButton"));
		backButton.click();
		System.out.println("\u001B[32m" + " LISTO" + "\u001B[0m");
	}

	/**
	 * Pulsa el botón opciones (⋮) situado en la parte superior derecha de la
	 * pantalla y despliega las opciones.
	 * 
	 * @throws Exception
	 */
	public void clickOptionsButton() throws Exception {
		System.out.print("Click en Options (⋮)...");
		WebElement moreOptions = Driver.driver().findElement(map.getLocator("moreOptions"));
		moreOptions.click();
		System.out.println("\u001B[32m" + " LISTO" + "\u001B[0m");
	}

	/**
	 * Sincroniza y pulsa la opción "Delete" en el menú de opciones.Este menú
	 * debe estar desplegado.
	 * 
	 * @throws Exception
	 */
	public void clickDeleteOption() throws Exception {
		System.out.print("Click en Options (⋮) -> Delete...");
		WebElement deleteButton = (MobileElement) (new WebDriverWait(Driver.driver(), 10))
				.until(new ExpectedCondition<WebElement>() {
					public WebElement apply(WebDriver d) {
						try {
							return d.findElement(map.getLocator("deleteButton"));
						} catch (Exception e) {
							return null;
						}
					}
				});
		deleteButton.click();
		System.out.println("\u001B[32m" + " LISTO" + "\u001B[0m");
	}

	/**
	 * Sincroniza y devuelve el mensaje de información tras pulsar Delete, tras
	 * verificar que está desplegado
	 * 
	 * @return El texto del mensaje de borrado. Debería ser
	 *         "This contact will be deleted."
	 */
	public String getDeleteMessage() {
		WebElement deleteMessage = (MobileElement) (new WebDriverWait(Driver.driver(), 10))
				.until(new ExpectedCondition<WebElement>() {
					public WebElement apply(WebDriver d) {
						try {
							if (d.findElement(map.getLocator("deleteMessage")).isDisplayed()) {
								return d.findElement(map.getLocator("deleteMessage"));
							} else {
								return null;
							}
						} catch (Exception e) {
							return null;
						}
					}
				});
		return deleteMessage.getText();
	}

	/**
	 * Pulsa el botón OK en el pop-up que aparece tras pulsar delete.
	 * 
	 * @throws Exception
	 */
	public void clickDeleteOkOption() throws Exception {
		System.out.print("Click en OK (Delete Contact)...");
		WebElement deleteOkButton = Driver.driver().findElement(map.getLocator("deleteOkButton"));
		deleteOkButton.click();
		System.out.println("\u001B[32m" + " LISTO" + "\u001B[0m");
	}

	/**
	 * Sincroniza y pulsa el botón Edit en el menú de opciones. Este menú debe estar
	 * desplegado.
	 * 
	 * @throws Exception
	 */
	public void clickEditOption() throws Exception {
		System.out.print("Click en Options (⋮) -> Edit...");
		WebElement editButton = (MobileElement) (new WebDriverWait(Driver.driver(), 10))
				.until(new ExpectedCondition<WebElement>() {
					public WebElement apply(WebDriver d) {
						try {
							return d.findElement(map.getLocator("editButton"));
						} catch (Exception e) {
							return null;
						}
					}
				});
		editButton.click();
		System.out.println("\u001B[32m" + " LISTO" + "\u001B[0m");
	}
}
