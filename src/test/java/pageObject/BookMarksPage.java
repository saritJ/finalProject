package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BookMarksPage extends BasePage {

	@FindBy(css = "button.trip-item__bookmark >.vector-icon")
	private List<WebElement> listOfEraseButtons;

	@FindBy(css = ".trip-item__data-cont.d-flex:nth-child(1)")
	private WebElement firstTripTitle;

	@FindBy(css = ".trip-item__data-title.col-auto.font-weight-medium:nth-child(2)")
	private WebElement firstTripPrice;

	@FindBy(css = ".trip-item__link:nth-child(1)")
	private WebElement firstTripViewButton;

	@FindBy(css = ".confirm-modal__buttons>button:nth-child(2)")
	private WebElement confirmRemoveRequest;

	By listOfTripsLocator = By.cssSelector(".trip-item.col-12.col-md-6");

	// Constructor
	public BookMarksPage(WebDriver driver) {
		super(driver);
	}

	public int getNumberOfTrips() {
		return (driver.findElements(listOfTripsLocator)).size();
	}

	public void deleteFirstTrip() {
		System.out.println("size: " + listOfEraseButtons.size());
		click(listOfEraseButtons.get(0));
		click(confirmRemoveRequest);
	}

	public String getFirstTripTitle() {
		return getText(firstTripTitle);
	}

	public int getFirstTripPrice() {
		String val = getText(firstTripPrice);
		return getNumber(val);
	}

	public void viewTripDetils() {
		click(firstTripViewButton);
	}

	public void waitUntilNumberOfTripsIsEqual(int number) {
		wait.until(ExpectedConditions.numberOfElementsToBe(listOfTripsLocator, number));
	}

}
