package test;

import java.net.MalformedURLException;

import org.concordion.api.extension.Extension;
import org.concordion.api.extension.Extensions;
import org.concordion.ext.StoryboardExtension;
import org.concordion.ext.TimestampFormatterExtension;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

import driver.AppiumScreenshotTaker;
import driver.Driver;
import pages.ContactDetailActivity;
import pages.ContactEditorActivity;
import pages.PeopleActivity;

@RunWith(ConcordionRunner.class)
@Extensions({ MyCssExtension.class , TimestampFormatterExtension.class})
public class CRUDTestFixture {

	@Extension
	public final StoryboardExtension storyboard = new StoryboardExtension().setScreenshotTaker(null);

	PeopleActivity peopleActivity;
	ContactEditorActivity contactEditorActivity;
	ContactDetailActivity contactDetailActivity;

	public void setUp(String version, String deviceName, String platformName, String appiumServerURL)
			throws MalformedURLException {
		Driver.startDriver(version, deviceName, platformName, "http://127.0.0.1:4723/wd/hub");
		storyboard.setScreenshotTaker(new AppiumScreenshotTaker(Driver.driver()));
	}

	public String getActivity() throws InterruptedException {
		Thread.sleep(2000);
		return Driver.getCurrentActivity();
	}

	public void clickNewContact() throws Exception {
		peopleActivity = new PeopleActivity();
		peopleActivity.clickNewContact();
		storyboard.addScreenshot("Hacemos click en nuevo contacto",
				"Debería mostrar la lista de contactos o en su defecto un mensaje indicando que no existen contactos.");
	}

	public void setContactData(String name, String phone) throws Exception {
		contactEditorActivity = new ContactEditorActivity();
		contactEditorActivity.setNameField(name);
		contactEditorActivity.setPhoneField(phone);
		storyboard.addScreenshot("Rellenamos los campos con los datos introducidos",
				"Vemos la pantalla de edición de contacto");
	}

	public void clickDone() throws Exception {
		contactEditorActivity.clickDoneButton();
	}

	public String getName() throws Exception {
		contactDetailActivity = new ContactDetailActivity();
		Thread.sleep(3000);
		return contactDetailActivity.getName();
	}

	public String getPhone() throws Exception {
		storyboard.addScreenshot("Verificamos creación correcta",
				"Debería mostrar la pantalla de detalles del contacto con los datos introducidos en la creación.");
		return contactDetailActivity.getPhone();
	}

	public void clickBack() throws Exception {
		contactDetailActivity.clickBackButton();
	}

	public boolean verifyContact(String name) throws Exception {
		return peopleActivity.verifyListedContact(name);
		
	}

	public void clickContact(String name) throws Exception {
		storyboard.addScreenshot("Hacemos click en el contacto listado",
				"Debería mostrar la lista de contactos incluyendo nuestro contacto recién creado.");
		peopleActivity.clickListedContact(name);
	}

	public void clickOptions() throws Exception {
		contactDetailActivity.clickOptionsButton();
	}
	
	public void clickEdit() throws Exception {
		contactDetailActivity.clickEditOption();
	}

	public void clickDelete() throws Exception {
		contactDetailActivity.clickDeleteOption();
	}

	public String getDeleteMessage() {
		storyboard.addScreenshot("Eliminamos el contacto",
				"Vemos la pantalla de confirmación de eliminación.");
		return contactDetailActivity.getDeleteMessage();
	}

	public void clickDeleteOk() throws Exception {
		contactDetailActivity.clickDeleteOkOption();
	}

	public void tearDown() {
		storyboard.setScreenshotTaker(null);
		Driver.close();
	}
}