package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WherePage extends BasePage {

	@FindBy(css = ".search-direction__select:nth-child(3)")
	WebElement to;

	@FindBy(css = ".rs-select__menu-list > div:nth-child(1)")
	WebElement firstOptionInDorectionDropdown;

	// Constructor
	public WherePage(WebDriver driver) {
		super(driver);
	}

	public void selectBestForMe() {
		click(to);
		sleep(2000);
		click(firstOptionInDorectionDropdown);

	}

}
