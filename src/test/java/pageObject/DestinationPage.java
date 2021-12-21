package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DestinationPage extends BasePage {

	@FindBy(css = ".destinations-page-services.row a:nth-child(1)")
	private WebElement carRental;

	// Constructor
	public DestinationPage(WebDriver driver) {
		super(driver);
	}

	public void carRental() {
		click(carRental);
	}
}
