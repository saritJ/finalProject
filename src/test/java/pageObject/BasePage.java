package pageObject;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	Actions action;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 10);
		js = (JavascriptExecutor) driver;
		action = new Actions(driver);
	}

	public void fillText(WebElement el, String text) {
		wait.until(ExpectedConditions.visibilityOf(el));
		el.clear();
		highlightElement(el, "green");
		el.sendKeys(text);
	}

	public void click(WebElement el) {
		wait.until(ExpectedConditions.visibilityOf(el));
		highlightElement(el, "green");
		el.click();

	}

	public void clear(WebElement el) {
		wait.until(ExpectedConditions.visibilityOf(el));
		highlightElement(el, "red");
		el.clear();

	}

	public String getText(WebElement el) {
		wait.until(ExpectedConditions.visibilityOf(el));
		highlightElement(el, "blue");
		return el.getText();
	}

	public void sleep(long mills) {
		try {
			Thread.sleep(mills);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/* Select an option from select element by index */
	public void selectOptionIndex(WebElement el, int option) {
		Select sel = new Select(el);
		sel.selectByIndex(option);
	}

	/* Click "OK" in an alert */
	public void alertApprove() {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	/* Hover above element */
	public void hoverAboveElement(WebElement el) {
		highlightElement(el, "orange");
		action.moveToElement(el).build().perform();
	}

	/*
	 * Call this method with your element and a color like (red,green,orange etc...)
	 */
	protected void highlightElement(WebElement element, String color) {
		// keep the old style to change it back
		String originalStyle = element.getAttribute("style");
		String newStyle = "background-color:yellow; border: 1px solid " + color + ";" + originalStyle;
		;

		// Change the style
		js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '"
				+ newStyle + "');},0);", element);

		// Change the style back after few miliseconds
		js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '"
				+ originalStyle + "');},400);", element);

	}

	public void scrollDown(int pixelesToScroll) {
		js.executeScript("window.scrollBy(0," + pixelesToScroll + ")");
	}

	/* New Windows methods */
	public String getCurrentWindowHandler() {
		return driver.getWindowHandle();
	}

	public void moveToNewWindow() {
		for (String handler : driver.getWindowHandles()) {
			driver.switchTo().window(handler);
		}
	}

	public void returnToMainWindow(String windowHandler) {
		driver.close();
		driver.switchTo().window(windowHandler);
	}

	public void dragAndDrop(WebElement el, int xPos, int yPos) {
		highlightElement(el, "yellow");
		action.dragAndDropBy(el, xPos, yPos).perform();
	}

	/* Convert String in format 1,800� to number 1800 */
	protected int getNumber(String value) {
		value = value.replace("₪", "");
		value = value.replace(",", "");
		return Integer.parseInt(value);
	}

}