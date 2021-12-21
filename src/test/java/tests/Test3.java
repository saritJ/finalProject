package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.ArticlePage;
import pageObject.BudgetPage;
import pageObject.DestinationPage;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MagazinePage;
import pageObject.ProfilePage;
import pageObject.ThemePage;
import pageObject.LocationPage;

public class Test3 extends BaseTest {

	@Test(description = "Make sure total costs change when changing the budget slider")
	public void tc_14() {
		driver.get("https://www.shichor.co.il/en");
		HomePage hp = new HomePage(driver);
		hp.selectTrip();
		ThemePage tp = new ThemePage(driver);
		tp.clickOnFirstCard();
		LocationPage lp = new LocationPage(driver);
		lp.clickManageBudget();
		BudgetPage bp = new BudgetPage(driver);
		int allCostsBefore = bp.getAllCostValue();
		int totalBefore = bp.getAllCostValue();
		bp.slide();
		int allCostsAfter = bp.getAllCostValue();
		int totalAfter = bp.getAllCostValue();
		bp.applyChanges();
		Assert.assertTrue(allCostsAfter > allCostsBefore);
		Assert.assertTrue(totalAfter > totalBefore);
	}

	@Test(description = "Selecting 'save trip' when logging in will give a success message")
	public void tc_15() {
		LocationPage lp = new LocationPage(driver);
		lp.saveTrip();
		LoginPage lgp = new LoginPage(driver);
		lgp.login("jsarit82@gmail.com", "Perfecttrip");
		String actual = lp.getSuccessMessageContent();
		String expected = "Trip was successfully saved to the bookmarks! You can continue work with it on Bookmarks";
		Assert.assertEquals(actual, expected);
	}

	@Test(description = "Select car rental -> make sure you are redirected to the right page (rent4less web site)")
	public void tc_16TestCarRental() {
		driver.get("https://www.shichor.co.il/en");
		HomePage hp = new HomePage(driver);
		hp.selectDestinationsLink();
		DestinationPage dp = new DestinationPage(driver);
		dp.carRental();
		dp.moveToNewWindow();
		String url = driver.getCurrentUrl();
		boolean contained = url.contains("rent4less");
		Assert.assertTrue(contained);
	}

	@Test(description = "Selects 'magazine' from menu and see the articles match the links")
	public void tc_17TestMagazineLinks() {
		driver.get("https://www.shichor.co.il/en");
		HomePage hp = new HomePage(driver);
		hp.selectMagazineLink();
		MagazinePage mp = new MagazinePage(driver);
		String expectedTitle = mp.getTitle();
		String expectedCity = mp.getCityName();
		mp.clickOnCardToTest();
		ArticlePage ap = new ArticlePage(driver);
		String actualTitle = ap.getArticleTitle();
		Assert.assertEquals(actualTitle, expectedTitle,
				"The title in the article doesn't match the title that was selected in magazine page");
		ap.createTrip();
		LocationPage lp = new LocationPage(driver);
		String actualplanningCity = lp.getPageTitle(expectedCity);
		Assert.assertEquals(actualplanningCity, expectedCity,
				"The city in the link doesn't match the city that was in the article");
	}

	@Test(description = "Change user's 'trip purpose' to 'family trip' and make sure change is displayed")
	public void tc_18UpdateUserTripPurpose() {
		HomePage hp = new HomePage(driver);
		hp.goToUserProfile();
		ProfilePage pf = new ProfilePage(driver);
		String actualPurpose = pf.editUserPreferences();
		String expectedParpose = "Family trip";
		Assert.assertEquals(actualPurpose, expectedParpose, "the parpose of the trip was not updated succesfully.");
	}

	@Test(description = " Test information tooltip content")
	public void tc_19TestToolTip() {
		driver.get("https://www.shichor.co.il/en");
		HomePage hp = new HomePage(driver);
		String expectedString = "Choose a destination, trip purpose, budget and interests and get a complete travel plan, with daily routes for a perfect vacation. Also, you can let our AI choose a destination for you, based on your personal interests";
		String actualString = hp.getToolTip();
		Assert.assertEquals(actualString, expectedString, "the content of the tool tip does not match the expected");
	}

}
