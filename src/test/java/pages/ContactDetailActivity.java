package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import driver.Driver;
import driver.ObjectMap;
import io.appium.java_client.MobileElement;

public class ContactDetailActivity {
	private ObjectMap map;
	
	String activityName = ".activities.ContactDetailActivity";

	public ContactDetailActivity() {
		map = new ObjectMap("data\\PageObjects\\ContactDetailActivity.properties");
	}
	
	public boolean isLoaded(){
		if (activityName.equals(Driver.getCurrentActivity())){
			return true;
		}
		else {
			return false;
		}
	}
	
	public String getName() throws Exception{
		WebElement contactName = (MobileElement) (new WebDriverWait(Driver.driver(), 10)).until(new ExpectedCondition<WebElement>() {
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
	
	public String getPhone() throws Exception{
		WebElement contactPhone = Driver.driver().findElement(map.getLocator("contactPhone"));
		return contactPhone.getText();
	}
	
	public void clickBackButton() throws Exception{
		System.out.print("Click en Back (<) ...");
		WebElement backButton = Driver.driver().findElement(map.getLocator("backButton"));
		backButton.click();
		System.out.println("\u001B[32m" + " LISTO" + "\u001B[0m");
	}
	
	public void clickOptionsButton() throws Exception{
		System.out.print("Click en Options (⋮)...");
		WebElement moreOptions = Driver.driver().findElement(map.getLocator("moreOptions"));
		moreOptions.click();
		System.out.println("\u001B[32m" + " LISTO" + "\u001B[0m");
	}
	
	public void clickDeleteOption() throws Exception{
		System.out.print("Click en Options (⋮) -> Delete...");
		WebElement deleteButton = (MobileElement) (new WebDriverWait(Driver.driver(), 10)).until(new ExpectedCondition<WebElement>() {
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
	
	public String getDeleteMessage(){
		WebElement deleteMessage = (MobileElement) (new WebDriverWait(Driver.driver(), 10)).until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				try {
					if (d.findElement(map.getLocator("deleteMessage")).isDisplayed()){
						return d.findElement(map.getLocator("deleteMessage"));
					} else{
						return null;
					}
				} catch (Exception e) {
					return null;
				}
			}
		});
		//System.out.println("El TEXTO ES: "+deleteMessage.getText());
		return deleteMessage.getText();
	}
	
	public void clickDeleteOkOption() throws Exception{
		System.out.print("Click en OK (Delete Contact)...");
		WebElement deleteOkButton = Driver.driver().findElement(map.getLocator("deleteOkButton"));
		deleteOkButton.click();
		System.out.println("\u001B[32m" + " LISTO" + "\u001B[0m");
	}
	
	public void clickEditOption() throws Exception{
		System.out.print("Click en Options (⋮) -> Edit...");
		WebElement editButton = (MobileElement) (new WebDriverWait(Driver.driver(), 10)).until(new ExpectedCondition<WebElement>() {
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
