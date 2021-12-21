package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfilePage extends BasePage {

	@FindBy(css = ".profile-details__personal-data >h3")
	WebElement username;

	@FindBy(css = ".form-select.css-2b097c-container:nth-child(1)")
	WebElement natureDropDown;

	@FindBy(css = ".rs-select__option:nth-child(2)")
	WebElement familyOption;

	@FindBy(css = ".btn-secondary.btn-shadowed.btn-fluid.btn")
	WebElement saveChangesButton;

	@FindBy(css = ".container:nth-child(2)  button")
	WebElement editPreferences;

	@FindBy(css = ".profile-details__personal-data.row >div:nth-child(2)>.profile-data-item__value")
	WebElement tripPurposeLabel;

	// Constructor
	public ProfilePage(WebDriver driver) {
		super(driver);
	}

	public String getUserFirstName() {
		wait.until(ExpectedConditions.visibilityOf(username));
		String fullName = getText(username);
		String firstName = fullName.split(" ")[0];
		return firstName;
	}

	
	//.form-select.css-2b097c-container:nth-child(1)'
	public String editUserPreferences() {
		click(editPreferences);
		sleep(1000);
		click(natureDropDown);
		click(familyOption);
		click(saveChangesButton);
		return getText(tripPurposeLabel);
	}
}
