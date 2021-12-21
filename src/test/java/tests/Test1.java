package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.ProfilePage;

public class Test1 extends BaseTest {

	@Test(dataProvider="getData", description="Login with valid credentials")
	public void tc_01loginGood(String email,String password, String expectedErrorMessage) {
		System.out.println("1");
		driver.get("https://www.shichor.co.il/en");
		HomePage hp = new HomePage(driver);
		hp.goToLoginPage();

		LoginPage lp = new LoginPage(driver);
		lp.login(email, password);

		if (!expectedErrorMessage.isEmpty()) {
			Assert.assertEquals(lp.getErrorMessage().toLowerCase(), expectedErrorMessage.toLowerCase());
		} else {
			lp.waitUntilLOginWindowIsClosed();
			String username = hp.getUsername();
			Assert.assertNotNull(username, "login was not succesfull: no user name is diplayed in home page");
		}
	}
	
	
	@DataProvider
	public Object[][] getData(){
		Object[][] myData = {
				{"george@gmail.com","123456", "Unable to log in with provided credentials."},
				{"jsarit82@gmail.com","Perfecttrip", ""},
				
		};
		return myData;
	}

	@Test(description="After successful login, make sure that the user name is displayed in home page")
	public void tc_02checkUserNameAfterLogin() {
		System.out.println("2");
		HomePage hp = new HomePage(driver);
		String actualUsername = hp.getUsername();
		hp.goToUserProfile();
		ProfilePage pp = new ProfilePage(driver);
		String expectedUserName = pp.getUserFirstName();
		Assert.assertEquals(actualUsername, expectedUserName, "the name that is displayed after login is not correct");
	}

	@Test(description="Logout and make sure that user name is not displayed in home page")
	void tc_03logout() {
		System.out.println("3");
		HomePage hp = new HomePage(driver);
		hp.logOut();
		Assert.assertTrue(hp.checkIfSignInButtonExist(), "logout failed: sign in button is not displayed");
	}

}
