package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
	@FindBy(css = ".app-header__row > .nav > button.nav__item")
	private WebElement signInButton;

	@FindBy(css = ".app-header__row > .nav> .nav__item.nav__item-dropdown")
	private WebElement usernameLabel;

	@FindBy(css = ".nav__dropdown-menu > a:nth-child(1)")
	private WebElement userProfile;

	@FindBy(css = ".nav__dropdown-menu > a:nth-child(2)")
	private WebElement bookMarks;

	@FindBy(css = ".nav .nav__dropdown-menu > button")
	private WebElement logOutButton;

	@FindBy(css = ".confirm-modal__buttons > .btn-secondary")  
	private WebElement logOutApprove;

	@FindBy(css = ".search-bar__section .search-bar__item-value:nth-child(2)")
	private WebElement whereButton;

	@FindBy(css = ".search-bar__item-value:nth-child(2)")
	private WebElement whereValue;

	@FindBy(css = ".search-bar__row .search-bar__button-body")
	private WebElement searchButton;

	@FindBy(css = "button.popular-destinations-list__item:nth-child(1)")
	private WebElement popularDestinations;

	@FindBy(css = ".trip-card.hero-featured-trips__themed-trips:nth-child(1)")
	private WebElement secondTripCard;

	@FindBy(css = ".nav>a:nth-child(1)")
	private WebElement destinationsLink;

	@FindBy(css = ".nav>a:nth-child(2)")
	private WebElement magazineLink;

	@FindBy(css = ".home-hero__tooltip")
	private WebElement infoTooltip;

	@FindBy(css = ".tippy-content")
	private WebElement toolTipContent;

	// Constructor
	public HomePage(WebDriver driver) {
		super(driver);
	}

	public void goToLoginPage() {
		click(signInButton);
	}

	public void clickSearchButton() {
		wait.until(ExpectedConditions.visibilityOf(searchButton));
		click(searchButton);
	}

	public String getUsername() {
		return usernameLabel.getText();
	}

	private void openUserNameMenu() {
		wait.until(ExpectedConditions.visibilityOf(usernameLabel));
		hoverAboveElement(usernameLabel);
	}

	public void goToUserProfile() {
		openUserNameMenu();
		click(userProfile);
	}

	public void logOut() {
		openUserNameMenu();
		click(logOutButton);
		click(logOutApprove);
	}

	public boolean checkIfSignInButtonExist() {
		if (signInButton.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public void clickOnWhere() {
		click(whereButton);
	}

	public String waitUntillWhereTexthasValue(String value) {
		try {
			wait.until(ExpectedConditions.textToBePresentInElement(whereValue, value));
		} catch (Exception e) {
			System.out.println("the text didnt appear after 10 seconds");
		}
		return getText(whereValue);
	}

	public void selectFirstPopularDestination() {
		wait.until(ExpectedConditions.visibilityOf(popularDestinations));
		click(popularDestinations);
	}

	public String getFirstPopularDestinationName() {
		return getText(popularDestinations);
	}

	public void selectTrip() {
		click(secondTripCard);
	}

	public void selectDestinationsLink() {
		click(destinationsLink);
	}

	public void selectMagazineLink() {
		click(magazineLink);
	}

	public String getToolTip() {
		hoverAboveElement(infoTooltip);
		return getText(toolTipContent);
	}

	public void openBookmark() {
		openUserNameMenu();
		click(bookMarks);
	}
}
