package pages;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import driver.Driver;
import driver.ObjectMap;

public class PeopleActivity {
	/**
	 * El objeto objectMap, que lee el archivo .properties con los locators.
	 */
	private ObjectMap map;

	/**
	 * Nombre de la activity actual. (Una activity es una interfaz de una app
	 * Android)
	 */
	String activityName = ".activities.PeopleActivity";

	/**
	 * Instancia el objeto objectMap para leer los locators desde el archivo
	 * .properties
	 */
	public PeopleActivity() {
		map = new ObjectMap("src\\test\\resources\\pageObjects\\PeopleActivity.properties");
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
	 * Pulsa el botón de crear nuevo contacto, independientemente de si hay
	 * algún contacto listado o no.
	 * 
	 * @throws Exception
	 */
	public void clickNewContact() throws Exception {
		System.out.print("Click en 'Create a new contact'...");
		WebElement newContactButton = null;

		if (Driver.isElementPresent(map.getLocator("newContactButton"))) {
			System.out.print("Click en botón...");
			newContactButton = Driver.driver().findElement(map.getLocator("newContactButton"));
		} else {
			System.out.print("Click en icono...");
			newContactButton = Driver.driver().findElement(map.getLocator("newContactListButton"));
		}
		newContactButton.click();
		System.out.println("\u001B[32m" + " LISTO" + "\u001B[0m");
	}

	/**
	 * Pulsa sobre uno de los contactos listados con cuyo nombre coincida con el
	 * pasado por parámetro.
	 * 
	 * @param name
	 * @throws Exception
	 */
	public void clickListedContact(String name) throws Exception {
		System.out.print("Click en el contacto '" + name + "'...");
		List<WebElement> listaContactos = Driver.driver().findElements(map.getLocator("contactList"));
		if (!listaContactos.isEmpty()) {
			for (WebElement contacto : listaContactos) {
				WebElement contactName = null;
				try {
					contactName = contacto.findElement(map.getLocator("contactName"));
				} catch (NoSuchElementException NSEE) {
				}
				if (name.equals(contactName.getText())) {
					contactName.click();
					break;
				}
			}
		}
		System.out.println("\u001B[32m" + " LISTO" + "\u001B[0m");
	}

	/**
	 * Verifica la existencia de una contacto en la lista de contactos y
	 * devuelve un booleano en función de si dicho contacto existe o no.
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public boolean verifyListedContact(String name) throws Exception {
		System.out.print("Verificando existencia de contacto '" + name + "'...");
		List<WebElement> listaContactos = Driver.driver().findElements(map.getLocator("contactList"));
		if (!listaContactos.isEmpty()) {
			for (WebElement contacto : listaContactos) {
				WebElement contactName = null;
				try {
					contactName = contacto.findElement(map.getLocator("contactName"));
					if (name.equals(contactName.getText())) {
						System.out.println("\u001B[32m" + " TRUE" + "\u001B[0m");
						return true;
					}
				} catch (NoSuchElementException NSEE) {
				}
			}
		}
		System.out.println("\u001B[31m" + " FALSE" + "\u001B[0m");
		return false;
	}

}