package com.supermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.supermarket.base.Base;
import com.supermarket.base.DataProviders;
import com.supermarket.pages.LoginPage;
import com.supermarket.pages.ManageDeliveryBoyPage;
import com.supermarket.utilities.GeneralUtilities;

public class ManageDeliveryBoyTest extends Base {
	LoginPage loginpage;
	GeneralUtilities generalutilities = new GeneralUtilities();
	SoftAssert softassert;
	ManageDeliveryBoyPage manageDeliveryBoy;

	@Test(priority=1)
	public void verifyCreateNewDeliverBoyFunctionality() {
		loginpage = new LoginPage(driver);
		loginpage.login();
		manageDeliveryBoy = new ManageDeliveryBoyPage(driver);
		manageDeliveryBoy.clickDeliveryBoyPage();
		String name = "Joy" + generalutilities.get_RandomNumber();
		String email = "joy@gmail.com";
		String userName = name;
		String Password = "joy";
		manageDeliveryBoy.createNewDeliveryBoy(name, email, userName, Password);
		Assert.assertTrue(manageDeliveryBoy.createNewSuccess());
	}

	@Test(groups="Regression")
	public void verifyCreateDeliverBoyWithSameUserNameFunctionality() {
		loginpage = new LoginPage(driver);
		loginpage.login();
		manageDeliveryBoy = new ManageDeliveryBoyPage(driver);
		manageDeliveryBoy.clickDeliveryBoyPage();
		String name = "Joy5";
		String email = "joy@gmail.com";
		String userName = name;
		String Password = "joy";
		manageDeliveryBoy.createNewDeliveryBoy(name, email, userName, Password);
		Assert.assertTrue(manageDeliveryBoy.isDuplicationPermitted());
	}

	@Test(groups="Smoke",dependsOnMethods = {"verifyCreateNewDeliverBoyFunctionality"})
	public void searchCreatedDeliveryBoyFunctionality() {
		loginpage = new LoginPage(driver);
		loginpage.login();
		manageDeliveryBoy = new ManageDeliveryBoyPage(driver);
		manageDeliveryBoy.clickDeliveryBoyPage();
		String name = "Philip" + generalutilities.get_RandomNumber();
		String email = "philip@gmail.com";
		String userName = name;
		String Password = "philip";
		manageDeliveryBoy.createNewDeliveryBoy(name, email, userName, Password);
		manageDeliveryBoy.clickSearch();
		manageDeliveryBoy.searchUser(name);
		Assert.assertEquals(name, manageDeliveryBoy.IsSearchedUserAvailable(name));

	}

	@Test(dataProvider = "managedeliveryboydetails", dataProviderClass = DataProviders.class)
	public void verifyCreateDeliveryBoyFunctionality(String name, String email, String Password) {
		loginpage = new LoginPage(driver);
		loginpage.login();
		manageDeliveryBoy = new ManageDeliveryBoyPage(driver);
		manageDeliveryBoy.clickDeliveryBoyPage();
		String userName = name;
		manageDeliveryBoy.createNewDeliveryBoy(name, email, userName, Password);
		Assert.assertTrue(manageDeliveryBoy.createNewSuccess());

	}

}
