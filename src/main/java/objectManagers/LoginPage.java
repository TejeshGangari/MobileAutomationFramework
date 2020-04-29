package objectManagers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;

public class LoginPage {

	private LoginPage loginPage;
	
	public LoginPage(AppiumDriver<WebElement> driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="")
	public WebElement login;
	
}
