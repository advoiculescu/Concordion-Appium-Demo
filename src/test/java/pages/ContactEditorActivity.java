package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import driver.Driver;
import driver.ObjectMap;
import io.appium.java_client.MobileElement;

public class ContactEditorActivity {
	private ObjectMap map;
	
	String activityName = ".activities.ContactEditorActivity";

	public ContactEditorActivity() {
		map = new ObjectMap("data\\PageObjects\\ContactEditorActivity.properties");
	}
	
	public boolean isLoaded(){
		if (activityName.equals(Driver.getCurrentActivity())){
			return true;
		}
		else {
			return false;
		}
	}
	
	public void setNameField(String name) throws Exception{
		System.out.print("Insertando "+name+" en 'Name'...");
		WebElement nameField = (MobileElement) (new WebDriverWait(Driver.driver(), 10)).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				try {
					return d.findElement(map.getLocator("nameField"));
				} catch (Exception e) {
					//e.printStackTrace();
					return null;
				}
			}
		});
		nameField.sendKeys(name);
		System.out.println("\u001B[32m" + " LISTO" + "\u001B[0m");
	}
	
	public void setPhoneField(String phone) throws Exception{
		System.out.print("Insertando "+phone+" en 'Done'...");
		WebElement phoneField = Driver.driver().findElement(map.getLocator("phoneField"));
		phoneField.sendKeys(phone);
		System.out.println("\u001B[32m" + " LISTO" + "\u001B[0m");
	}
	
	public void clickDoneButton() throws Exception{
		System.out.print("Click en 'Done'...");
		WebElement doneButton = Driver.driver().findElement(map.getLocator("doneButton"));
		doneButton.click();
		System.out.println("\u001B[32m" + " LISTO" + "\u001B[0m");
	}
}
