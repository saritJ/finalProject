package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SightPage extends BasePage {

	@FindBy(css = ".poi-details__title")
	WebElement title;

	@FindBy(css = ".poi-details__description")
	WebElement description;

	@FindBy(css = ".show-more a")
	WebElement showMoreDescription;

	// Constructor
	public SightPage(WebDriver driver) {
		super(driver);
	}

	public String getTitle() {
		return getText(title);
	}

	public String getTabName() {
		return driver.getTitle();
	}

	public boolean doesDescriptionContainsWord(String word) {
		click(showMoreDescription);
		if (getText(description).toUpperCase().contains(word.toUpperCase())) {
			return true;
		} else {
			return false;
		}
	}

}
