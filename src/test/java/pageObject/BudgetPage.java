package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BudgetPage extends BasePage {

	@FindBy(css = ".trip-budget__modal-price")
	WebElement totalValue;

	@FindBy(css = ".trip-budget-2-modal__all-costs>span")
	WebElement allCostsValue;

	@FindBy(css = ".rc-slider-handler-tooltip .rc-slider-handle:nth-child(1)")
	WebElement slider;

	@FindBy(css = ".btn-large.btn-default")
	WebElement applyButton;

	String sliderValueLocator = ".rc-slider-label>span:nth-child(1)";

	// Constructor
	public BudgetPage(WebDriver driver) {
		super(driver);
	}

	public int getTotal() {
		return getNumber(getText(totalValue));
	}

	public int getAllCostValue() {
		return getNumber(getText(allCostsValue));
	}

	public void slide() {
		hoverAboveElement(slider);
		sleep(2000);
		System.out.println(slider.getLocation());
		dragAndDrop(slider, 200, 50);
	}

	public void applyChanges() {
		click(applyButton);
	}

}
