package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.BookMarksPage;
import pageObject.HomePage;
import pageObject.LocationPage;
import pageObject.LoginPage;

public class Test4 extends BaseTest {

	@Test (description="Select bookmarks -> view details of the first link, and make sure details are the same")
	public void tc_20TestBookmarksLink() {

		driver.get("https://www.shichor.co.il/en");
		HomePage hp = new HomePage(driver);
		hp.goToLoginPage();
		LoginPage lgp = new LoginPage(driver);
		lgp.login("jsarit82@gmail.com", "Perfecttrip");
		hp.openBookmark();
		BookMarksPage bp = new BookMarksPage(driver);
		String expectedName = bp.getFirstTripTitle();
		int expectedPrice = bp.getFirstTripPrice();
		String currentWindow = bp.getCurrentWindowHandler();
		bp.viewTripDetils();
		LocationPage lp = new LocationPage(driver);
		lp.moveToNewWindow();
		String actualName = lp.getPageTitle();
		int actualPrice = lp.getEstimatedPrice();
		Assert.assertTrue(expectedName.contains(actualName), "the city names do not match");
		Assert.assertEquals(actualPrice, expectedPrice, "the prices do not match");
		// driver.close();
		lp.returnToMainWindow(currentWindow);

	}

	@Test (description=" Select bookmarks , remove item and make sure it was removed")
	public void tc_21TestRemoveItemFromBookmarks() {
		BookMarksPage bp = new BookMarksPage(driver);
		int before = bp.getNumberOfTrips();
		bp.deleteFirstTrip();
		bp.waitUntilNumberOfTripsIsEqual(before - 1);
		int after = bp.getNumberOfTrips();
		Assert.assertEquals(after, (before - 1), "the trip was not deleted from bookmarks");

	}

}
