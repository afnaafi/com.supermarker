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

	@Test(description = "testcase1")
	public void verify_textOfBox1() {
		loginpage = new LoginPage(driver);
		loginpage.login();
		homepage = new HomePage(driver);
		homepage.get_TextsOfBoxes();
		String expectedtext = Constants.expectedBoxOneText;
		String actualtext = homepage.get_textOfElement();
		Assert.assertEquals(expectedtext, actualtext);
	}

	@Test(description = "testcase2")
	public void verify_ColorOfBox1() {
		loginpage = new LoginPage(driver);
		loginpage.login();
		homepage = new HomePage(driver);
		String expectedcolor = Constants.expectedColorOfBox1;
		String actualcolor = homepage.get_colourofbox();
		Assert.assertEquals(expectedcolor, actualcolor);
	}

	@Test(description = "testcase3")
	public void verify_linkOfBox1IsWorking() {
		loginpage = new LoginPage(driver);
		loginpage.login();
		homepage = new HomePage(driver);
		homepage.clickOnLink();
		Assert.assertTrue(homepage.isManageContentPageHeadingPresent(Constants.expectedManageContentPageText));

	}

	@Test(description = "testcase4", dependsOnMethods = { "verify_linkOfBox1IsWorking" })
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
