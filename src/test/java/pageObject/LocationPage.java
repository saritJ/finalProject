package pageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LocationPage extends BasePage {

	@FindBy(css = ".trip-details__routes button.trip-tabs-2__tab")
	private List<WebElement> tabs;

	@FindBy(css = "a.sight-card")
	private List<WebElement> sights;

	@FindBy(css = "button.btn-primary")
	private WebElement saveTrip;

	@FindBy(css = ".trip-suggestion__btn.btn-link")
	private WebElement toAllSightsButton;

	@FindBy(css = ".btn-default.trip-budget-2-top__btn")
	private WebElement manageBudgetButton;

	@FindBy(css = "div.trip-heading__save-btn > button")
	private WebElement saveTripButton;

	@FindBy(css = ".toaster__title")
	private WebElement successMessage;

	@FindBy(css = ".trip-details__data .trip-heading__heading")
	private WebElement pageTitle;

	@FindBy(css = ".Toastify__toast-body>div:nth-child(2)")
	private WebElement moreSuccesscontent;

	@FindBy(css = ".trip-budget-2-top__info-title")
	private WebElement estimatedPrice;

	// Constructor
	public LocationPage(WebDriver driver) {
		super(driver);
	}

	public void clickSaveTrip() {
		wait.until(ExpectedConditions.visibilityOf(saveTrip));
		click(saveTrip);
	}

	public int getEstimatedPrice() {
		String val = getText(estimatedPrice);
		val = val.replace("Estimated cost: ", "");
		return getNumber(val);
	}

	public boolean isSightTabSelected() {
		WebElement sightsTab = tabs.get(0);
		return (sightsTab.getAttribute("class").contains("tab--active"));
	}

	public int getnumberOfLinks() {
		return sights.size();
	}

	public void clickOnFirstLink() {
		click(sights.get(0));
	}

	public String getinkName() {
		return getText(sights.get(0));
	}

	public void clickToAllSights() {
		click(toAllSightsButton);
	}

	public void clickManageBudget() {
		click(manageBudgetButton);
	}

	public void saveTrip() {
		click(saveTripButton);
	}

	public String getSuccessMessageContent() {
		wait.until(ExpectedConditions.visibilityOf(successMessage));
		return (getText(moreSuccesscontent));
	}

	public String getPageTitle(String wantedTest) {
		wait.until(ExpectedConditions.textToBePresentInElement(pageTitle, wantedTest));
		return getText(pageTitle);
	}

	public String getPageTitle() {
		return getText(pageTitle);
	}

}
