package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ThemePage extends BasePage {

	@FindBy(css = ".themed-trips-list  a:nth-child(1)")
	private WebElement firstCard;

	// Constructor
	public ThemePage(WebDriver driver) {
		super(driver);
	}

	public void clickOnFirstCard() {
		click(firstCard);
	}
}
