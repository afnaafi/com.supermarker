package com.supermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.supermarket.base.Base;
import com.supermarket.constants.Constants;
import com.supermarket.pages.HomePage;
import com.supermarket.pages.LoginPage;

public class HomeTest extends Base {
	HomePage homepage;
	LoginPage loginpage;

	@Test(groups = "Regression")
	public void verify_textOfBox() {
		loginpage = new LoginPage(driver);
		loginpage.login();
		homepage = new HomePage(driver);
		homepage.get_TextsOfBoxes();
		String expectedtext = Constants.expectedBoxOneText;
		String actualtext = homepage.get_textOfElement();
		Assert.assertEquals(expectedtext, actualtext);
	}

	@Test(priority = 10)
	public void verify_ColorOfBox1() {
		loginpage = new LoginPage(driver);
		loginpage.login();
		homepage = new HomePage(driver);
		String expectedcolor = Constants.expectedColorOfBox1;
		String actualcolor = homepage.get_colourofbox();
		Assert.assertEquals(expectedcolor, actualcolor);
	}

	@Test(invocationCount = 2)
	public void verify_linkOfBox1Functionality() {
		loginpage = new LoginPage(driver);
		loginpage.login();
		homepage = new HomePage(driver);
		homepage.clickOnLink();
		Assert.assertTrue(homepage.isManageContentPageHeadingPresent(Constants.expectedManageContentPageText));

	}

	@Test
	public void verify_UrlOfBox1Link() {
		loginpage = new LoginPage(driver);
		loginpage.login();
		homepage = new HomePage(driver);
		homepage.clickOnLink();
		String expectedurl = Constants.expectedLinkurl;
		String actualurl = homepage.currentUrl();
		Assert.assertEquals(expectedurl, actualurl);
	}

}
