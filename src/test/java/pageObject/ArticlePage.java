package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ArticlePage extends BasePage {

	@FindBy(css = "h1.article-page__title")
	private WebElement title;

	@FindBy(css = ".btn-primary.article-create-trip__button")
	private WebElement createTripButton;

	// Constructor
	public ArticlePage(WebDriver driver) {
		super(driver);
	}

	public String getArticleTitle() {
		return getText(title);
	}

	public void createTrip() {
		click(createTripButton);
	}
}
