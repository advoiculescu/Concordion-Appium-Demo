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
@Extensions({ MyCssExtension.class, TimestampFormatterExtension.class })
public class CRUDTestFixture {

	@Extension
	public final StoryboardExtension storyboard = new StoryboardExtension().setScreenshotTaker(null);

	// Declaramos las activities (interfaces)
	PeopleActivity peopleActivity;
	ContactEditorActivity contactEditorActivity;
	ContactDetailActivity contactDetailActivity;

	// Arranca el driver con los parámetros designados y configura la toma de
	// capturas de pantalla
	public void setUp(String version, String deviceName, String platformName, String appiumServerURL)
			throws MalformedURLException {
		Driver.startDriver(version, deviceName, platformName, "http://127.0.0.1:4723/wd/hub");
		storyboard.setScreenshotTaker(new AppiumScreenshotTaker(Driver.driver()));
		storyboard.setTakeScreenshotOnCompletion(false);
	}

	// Espera 3 segundos y devuelve el nombre de la actividad actual
	public String getActivity() throws InterruptedException {
		// Esperamos el cambio de Activity (no es posible sincronizar)
		Thread.sleep(3000);
		return Driver.getCurrentActivity();
	}

	// Hace click en nuevo contacto y añade una captura al storyboard
	public void clickNewContact() throws Exception {
		peopleActivity = new PeopleActivity();
		peopleActivity.clickNewContact();
		storyboard.addScreenshot("Hacemos click en nuevo contacto",
				"Debería mostrar la lista de contactos o en su defecto un mensaje indicando que no existen contactos.");
	}

	// Introduce los datos del contacto (nombre y teléfono) y añade una captura
	// al storyboard
	public void setContactData(String name, String phone) throws Exception {
		contactEditorActivity = new ContactEditorActivity();
		contactEditorActivity.setNameField(name);
		contactEditorActivity.setPhoneField(phone);
		storyboard.addScreenshot("Rellenamos los campos con los datos introducidos",
				"Vemos la pantalla de edición de contacto");
	}

	// Hace click en el botón Done
	public void clickDone() throws Exception {
		contactEditorActivity.clickDoneButton();
	}

	// Devuelve el nombre del contacto seleccionado
	public String getName() throws Exception {
		contactDetailActivity = new ContactDetailActivity();
		// Espermos dos segundos porque cuando se realiza la modificación de
		// datos del contacto y se vuelve a los detalles del contacto, los datos
		// dentro de los elementos tardan en actualizarse
		Thread.sleep(2000);
		return contactDetailActivity.getName();
	}

	// Añade una captura al storyboard y devuelve el número de telefono del
	// contacto seleccionado
	public String getPhone() throws Exception {
		storyboard.addScreenshot("Verificamos creación correcta",
				"Debería mostrar la pantalla de detalles del contacto con los datos introducidos en la creación.");
		return contactDetailActivity.getPhone();
	}

	// Hace click en el botón Atrás
	public void clickBack() throws Exception {
		contactDetailActivity.clickBackButton();
	}

	// Devuelve True o False dependiendo de si el contacto existe en la lista
	public boolean verifyContact(String name) throws Exception {
		return peopleActivity.verifyListedContact(name);

	}

	// Seleccionamos el contacto parametrizado
	public void clickContact(String name) throws Exception {
		storyboard.addScreenshot("Hacemos click en el contacto listado",
				"Debería mostrar la lista de contactos incluyendo nuestro contacto recién creado.");
		peopleActivity.clickListedContact(name);
	}

	// Hacemos click en las opciones
	public void clickOptions() throws Exception {
		contactDetailActivity.clickOptionsButton();
	}

	// Hacemos click en el botón Edit
	public void clickEdit() throws Exception {
		contactDetailActivity.clickEditOption();
	}

	// Hacemos click en el botón delete
	public void clickDelete() throws Exception {
		contactDetailActivity.clickDeleteOption();
	}

	// Devuelve el mensaje que aparece al pulsar Delete y añade una captura al
	// storyboard
	public String getDeleteMessage() {
		storyboard.addScreenshot("Eliminamos el contacto", "Vemos la pantalla de confirmación de eliminación.");
		return contactDetailActivity.getDeleteMessage();
	}
	
	// Hace click en el botón Ok, tras pulsar delete
	public void clickDeleteOk() throws Exception {
		contactDetailActivity.clickDeleteOkOption();
	}
	
	// Cerramos el storyboard y también el driver
	public void tearDown() {
		storyboard.addSectionBreak("");
		Driver.close();
	}
}