package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

	@FindBy(css = "#email")
	WebElement email;

	@FindBy(css = "#password")
	WebElement password;

	@FindBy(css = ".form-submit")
	WebElement loginButton;

	@FindBy(css = ".form-error")
	WebElement errorMessage;

	@FindBy(css = ".modal-v2__close-button")
	WebElement closeButton;

	// Constructor
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void login(String email, String password) {
		fillText(this.email, email);
		fillText(this.password, password);
		click(loginButton);
	}

	public String getErrorMessage() {
		wait.until(ExpectedConditions.visibilityOf(errorMessage));
		return getText(errorMessage);
	}

	public void closeWindow() {
		click(closeButton);
	}

	public boolean isLoginWindowOpen() {
		try {
			return loginButton.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	public void waitUntilLOginWindowIsClosed() {
		wait.until(ExpectedConditions.invisibilityOf(email));
	}

}
