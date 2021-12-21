package pageObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ResultsPage extends BasePage {

	@FindBy(css = ".trip-card__overlay-body-wrapper .trip-card__overlay-row >div.trip-card__overlay-row-body")
	List<WebElement> valuesBehindCard;

	@FindBy(css = ".trips-search-list__list>a")
	List<WebElement> tripCards;

	@FindBy(css = ".btn-small.btn-secondary--outlined")
	WebElement showMoreButton;

	@FindBy(css = " div.sr-search-bar--desktop div.search-bar__steps > div:nth-child(2)  span.search-bar__item-value")
	WebElement whenText;

	@FindBy(css = " div.sr-search-bar--desktop div.search-bar__steps > div:nth-child(3)  span.search-bar__item-value")
	WebElement whoText;

	@FindBy(css = " div.sr-search-bar--desktop div.search-bar__steps > div:nth-child(5)  span.search-bar__item-value")
	WebElement howMuchText;

	@FindBy(css = ".cookies-policy-banner__button.btn")
	WebElement acceptCoockiesButton;

	By cardsTitles = By.cssSelector("h3.trip-card__name");

	
	// Constructor
	public ResultsPage(WebDriver driver) {
		super(driver);
	}

	public boolean compareResultsWithPreferences() {
		String locator = ".trip-card__overlay-body > .trip-card__overlay-body-wrapper";
		wait.until(ExpectedConditions.visibilityOf(whoText));
		String[] expectedValues = new String[] { whoText.getText(), whenText.getText(), howMuchText.getText() };
		wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(locator), 4));
		List<WebElement> links = driver.findElements(By.cssSelector(locator));
		int cardCounter = 0;
		boolean match = true;
		for (WebElement card : links) {
			hoverAboveElement(card);
			for (int i = 0; i < 3; i++) {
				String actualValue = getText(valuesBehindCard.get(cardCounter * links.size() + i));
				match = match && (actualValue.equals(expectedValues[i]));
			}
			cardCounter++;
		}
		return match;
	}

	public boolean testShowMoreButton() {
		boolean thereAreMoreResultsToWorkOn = true;
		Set<String> placesSet = new HashSet<String>();
		int counter = 0;
		List<WebElement> shomeMoreButton = driver.findElements(By.cssSelector(".btn-small.btn-secondary--outlined"));
		while (thereAreMoreResultsToWorkOn) {
			thereAreMoreResultsToWorkOn = false;
			List<WebElement> places = driver.findElements(cardsTitles);
			for (int i = counter; i < places.size(); i++) {
				WebElement el = places.get(i);
				String place = getText(el);
				if (!placesSet.contains(place)) {
					placesSet.add(place);
					System.out.println(counter + ". " + place);
					counter++;
				} else {
					return false;
				}
			}
			if (shomeMoreButton.size() > 0) {
				click(shomeMoreButton.get(0));
				thereAreMoreResultsToWorkOn = true;
				scrollDown(400);
				shomeMoreButton = driver.findElements(By.cssSelector(".btn-small.btn-secondary--outlined"));
			}
		}
		System.out.println("total number of places disoplayed is " + counter);
		return true;
	}

	public void closeCoockiesNotification() {
		click(acceptCoockiesButton);
	}

	public List<WebElement> getAllTripLinks() {
		return tripCards;
	}

	public List<String> getListOfTitle() {
		List<String> titles = new ArrayList<String>();
		for (WebElement card : driver.findElements(cardsTitles)) {
			titles.add(getText(card));
		}
		return titles;
	}

	public List<String> getListOfUrls() {
		List<String> urls = new ArrayList<String>();
		for (WebElement card : tripCards) {
			urls.add(card.getAttribute("href"));
		}
		return urls;
	}
}
