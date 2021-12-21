package pageObject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AllSightsPage extends BasePage {

	@FindBy(css = ".form-input__input--icon")
	private WebElement search;

	@FindBy(css = ".form-input__password-toggle")
	private WebElement searchClearButton;

	@FindBy(css = "div.destination-pois-filters__check-button")
	private WebElement mustSeeCheckBox;

	@FindBy(css = "div.poi-card__header")
	private List<WebElement> listOfcards;

	@FindBy(css = "div.destination-pois-filters   div:nth-child(6)  div.poi-category-dropdown__category-title")
	private WebElement otherFilter;

	@FindBy(css = ".point__label")
	private List<WebElement> checkBoxOptions;

	// Constructor
	public AllSightsPage(WebDriver driver) {
		super(driver);
	}

	public void searchWord(String word) {
		fillText(search, word);
	}

	public void clearSearchWord() {
		click(searchClearButton);
		wait.until(ExpectedConditions.invisibilityOf(searchClearButton));
		wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".poi-card__header"), 10));
	}

	public void waitUntillSearchIsFinished() {
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(".poi-card__header"), 0));
		wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector(".poi-card__header"), 10));
	}

	public void clickOnFirstLink() {
		List<WebElement> listOfSights = driver.findElements(By.cssSelector(".poi-card__header"));
		click(listOfSights.get(0));
	}

	public boolean areAllLinkMustSee() {
		if (!mustSeeCheckBox.getAttribute("class").contains("checked")) {
			click(mustSeeCheckBox);
		}
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("div.poi-card__header"), 0));
		List<WebElement> listOfcards = driver.findElements(By.cssSelector("div.poi-card__header"));
		for (WebElement card : listOfcards) {
			if (!(getText(card).toLowerCase()).startsWith("Must see".toLowerCase())) {
				return false;
			}
		}
		return true;
	}

	public void clickOtherFilter() {
		click(otherFilter);
	}

	public List<String> getFilteringNames() {
		List<String> list = new ArrayList<String>();
		for (WebElement el : checkBoxOptions) {
			list.add(getText(el));
		}
		return list;
	}

}
