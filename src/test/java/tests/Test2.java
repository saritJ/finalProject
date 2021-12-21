package tests;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.AllSightsPage;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.ResultsPage;
import pageObject.SightPage;
import pageObject.WherePage;
import pageObject.LocationPage;

public class Test2 extends BaseTest {

	@Test(description = " Select 'best for me' option and see the results match the user preferences")
	public void tc_05testBestForMe() {
		driver.get("https://www.shichor.co.il/en");
		HomePage hp = new HomePage(driver);
		hp.clickOnWhere();
		WherePage wp = new WherePage(driver);
		wp.selectBestForMe();
		String actualValue = hp.waitUntillWhereTexthasValue("Best for me");
		Assert.assertEquals(actualValue, "Best for me");
		hp.clickSearchButton();
		ResultsPage rp = new ResultsPage(driver);
		boolean res = rp.compareResultsWithPreferences();
		Assert.assertTrue(res, "the values on the places cards do not match user's preferences");
	}

	@Test(description = "Select show more trip suggestions and make sure that they are unique (not displayed twice)")
	public void tc_06testShoeMoreButton() {
		ResultsPage rp = new ResultsPage(driver);
		rp.closeCoockiesNotification();
		boolean res = rp.testShowMoreButton();
		Assert.assertTrue(res, "the places in the show more button have already been displayed.");
	}

	@Test(description = "Select 'save trip' option and make sure user must be looged in to continue")
	public void tc_07checkSaveTripWorksCorrect() {
		driver.get("https://www.shichor.co.il/en");
		HomePage hp = new HomePage(driver);
		hp.clickOnWhere();
		hp.selectFirstPopularDestination();
		String popularDestination = hp.getFirstPopularDestinationName();
		hp.waitUntillWhereTexthasValue(popularDestination);
		hp.clickSearchButton();
		LocationPage lp = new LocationPage(driver);
		lp.clickSaveTrip();
		LoginPage lgp = new LoginPage(driver);
		Assert.assertTrue(lgp.isLoginWindowOpen(), "Login window did not open");
		lgp.closeWindow();
		lp.isSightTabSelected();
	}

	@Test(description = "When a new trip suggestion page is displayed, make sure \"sight\" tab is selected by default")
	public void tc_08checkThatDefaultTabIsSights() {
		LocationPage lp = new LocationPage(driver);
		Assert.assertTrue(lp.isSightTabSelected(), "Sight tab is not selected bu default");
	}

	@Test(description = "When a new trip suggestion page is displayed, make sure there are 6  suggestions displayed by default")
	public void tc_09checkDefaultNumberOfLinks() {
		LocationPage lp = new LocationPage(driver);
		Assert.assertEquals(lp.getnumberOfLinks(), 6, "the number of suggestions links is not 6 as expected");
	}

	@Test(description = "When selecting a link make sure it opens in a new tab , tab name contains the word \"Shichor\"")
	public void tc_10checkLinkNewWindow() {
		LocationPage lp = new LocationPage(driver);
		String currentWindow = lp.getCurrentWindowHandler();
		String expectedName = lp.getinkName();
		lp.clickOnFirstLink();
		lp.moveToNewWindow();
		SightPage sp = new SightPage(driver);
		String actualName = sp.getTabName();
		Assert.assertEquals(sp.getTitle(), expectedName);
		expectedName = expectedName + " - Shichor";
		Assert.assertEquals(actualName, expectedName, "the tab name is not in the format as expected.");
		sp.returnToMainWindow(currentWindow);
	}

	@Test(description = "Select \"to all sights\" button, enter a search word, and make sure that the sights displayed contain that word")
	public void tc_11checkLinkNewWindow() {
		LocationPage lp = new LocationPage(driver);
		lp.clickToAllSights();
		AllSightsPage asp = new AllSightsPage(driver);
		asp.moveToNewWindow();
		String wordToSearch = "large";
		asp.searchWord(wordToSearch);
		asp.waitUntillSearchIsFinished();
		String allSightsWindow = asp.getCurrentWindowHandler();
		asp.clickOnFirstLink();
		SightPage sp = new SightPage(driver);
		sp.moveToNewWindow();
		Assert.assertTrue(sp.doesDescriptionContainsWord(wordToSearch),
				"the link description doesn't " + "contain the wanted word: " + wordToSearch);
		sp.returnToMainWindow(allSightsWindow);
	}

	@Test(description = "Select \"must see\" check box and make sure all the sights contain the 'must see' header")
	public void tc_12checkMustSeeTitle() {
		AllSightsPage asp = new AllSightsPage(driver);
		assertTrue(asp.areAllLinkMustSee(), "not all links contain must see");
	}

	@Test(description = " Open 'other' menu, and make sure that all the expected check boxes are displayed")
	public void tc_13checkFiltering() {
		AllSightsPage asp = new AllSightsPage(driver);
		asp.clearSearchWord();
		asp.clickOtherFilter();
		String[] names = new String[] { "Near the city", "Museums and Galleries", "Parks", "Markets",
				"History and Religion", "Other" };
		int i = 0;
		for (String name : asp.getFilteringNames()) {
			Assert.assertEquals(name, names[i]);
			i++;
		}
	}

}
