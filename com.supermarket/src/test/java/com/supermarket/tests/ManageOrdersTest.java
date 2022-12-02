package com.supermarket.tests;



import org.testng.Assert;
import org.testng.annotations.Test;

import com.supermarket.base.Base;
import com.supermarket.constants.Constants;
import com.supermarket.pages.HomePage;
import com.supermarket.pages.LoginPage;
import com.supermarket.pages.ManageContentPage;
import com.supermarket.pages.ManageOrdersPage;
import com.supermarket.utilities.GeneralUtilities;

public class ManageOrdersTest extends Base {
	ManageOrdersPage managaeorderpage;
	HomePage homepage;
	LoginPage loginpage;
	ManageContentPage managecontentpage;
	GeneralUtilities generalutilities = new GeneralUtilities();

	@Test(groups = "Regression")
	public void VerifyChangeDeliveryStatusFunctionality() {
		loginpage = new LoginPage(driver);
		loginpage.login();
		managaeorderpage = new ManageOrdersPage(driver);
		managaeorderpage.clickOnPage();
		String actualstatus = managaeorderpage.changeDeliveryStatus("399", "Out For Delivery");
		Assert.assertEquals(Constants.EXPECTEDSTATUS, actualstatus);
	}

	@Test(priority = 2)
	public void verifyChangeDeliveryDateFunctionality() {
		loginpage = new LoginPage(driver);
		loginpage.login();
		managaeorderpage = new ManageOrdersPage(driver);
		managaeorderpage.clickOnPage();
		managaeorderpage.changeDeliveryDate("399", "30-11-2022");
		Assert.assertTrue(managaeorderpage.successAlert());

	}

	@Test(groups = "Regression",priority = 1)
	public void verifyAssignDeliveryBoyFunctionality() {
		loginpage = new LoginPage(driver);
		loginpage.login();
		managaeorderpage = new ManageOrdersPage(driver);
		managaeorderpage.clickOnPage();
		managaeorderpage.assignDeliveryBoy("380", "arya");
		Assert.assertTrue(managaeorderpage.successAlert());
	}
	@Test(priority = 5)
	public void verifyViewDetailsFunctionality() {
		loginpage = new LoginPage(driver);
		loginpage.login();
		managaeorderpage = new ManageOrdersPage(driver);
		managaeorderpage.clickOnPage();
		managaeorderpage.viewDetails("380");
		Assert.assertTrue(managaeorderpage.detailsDisplayed());
	}
}
