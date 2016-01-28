package pages;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import driver.Driver;
import driver.ObjectMap;

public class PeopleActivity {
	private ObjectMap map;

	String activityName = ".activities.PeopleActivity";

	public PeopleActivity() {
		map = new ObjectMap("data\\PageObjects\\PeopleActivity.properties");
	}

	public boolean isLoaded() {
		if (activityName.equals(Driver.getCurrentActivity())) {
			return true;
		} else {
			return false;
		}
	}

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
					//System.out.print("Click...");
					contactName.click();
					break;
				}
			}
		}
		System.out.println("\u001B[32m" + " LISTO" + "\u001B[0m");
	}

	public boolean verifyListedContact(String name) throws Exception {
		System.out.print("Verificando existencia de contacto '" + name + "'...");
		List<WebElement> listaContactos = Driver.driver().findElements(map.getLocator("contactList"));
		//System.out.println("NUMERO DE Contactos: " + (listaContactos.size()));
		if (!listaContactos.isEmpty()) {
			for (WebElement contacto : listaContactos) {
				WebElement contactName = null;
				try {
					contactName = contacto.findElement(map.getLocator("contactName"));
					if (name.equals(contactName.getText())) {
						System.out.println("\u001B[32m" + " TRUE" + "\u001B[0m");
						return true;
					}
				} catch (NoSuchElementException NSEE) {}
			}
		}
		System.out.println("\u001B[31m" + " FALSE" + "\u001B[0m");
		return false;
	}

}