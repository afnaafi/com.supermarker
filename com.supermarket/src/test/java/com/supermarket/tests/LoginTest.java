package com.supermarket.tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.supermarket.base.Base;
import com.supermarket.pages.LoginPage;
import com.supermarket.utilities.Excel;
import com.supermarket.utilities.ScreenShot;

public class LoginTest extends Base {
	LoginPage loginpage;
	Excel excel = new Excel();
	SoftAssert softassert;
	
	

	@Test // TC_01
	public void verifyLoginDisplay() {
		loginpage = new LoginPage(driver);
		softassert=new SoftAssert();
		softassert.assertTrue(loginpage.username_IsDisplayed());
		softassert.assertTrue(loginpage.password_IsDisplayed());
		softassert.assertTrue(loginpage.rememberMe_IsDispayed());
		softassert.assertTrue(loginpage.signIn_IsDispayed());
	}

	@Test // TC_02
	public void verifyStafLoginFunctionality() {
		ScreenShot screenshot =new ScreenShot();
		loginpage = new LoginPage(driver);
		excel.setExcelFile("LoginDatas", "ValidLoginCredentials");
		String userName = excel.get_CellData(0, 0);
		String password = excel.get_CellData(0, 1);
		loginpage.login(userName, password);
		screenshot.takeScreenShot(driver, "afna");

	}

	@Test // TC_03
	public void verifyInvalidloginAlertMessage() {
		loginpage = new LoginPage(driver);
		excel.setExcelFile("LoginDatas", "InvalidLoginCredentilas");
		String userName = excel.get_CellData(0, 0);
		String password = excel.get_CellData(0, 1);
		loginpage.login(userName, password);
		loginpage.alert_Message();
		Assert.assertTrue(loginpage.isInvalidAlertMessagePresent("Invalid Username/Password"));
	}

	@Test // TC_04
	public void verifyRememberMe_IsSelected() {
		loginpage = new LoginPage(driver);
		Assert.assertFalse(loginpage.rememberMe_IsSelected());
		loginpage.clickOnRememberMe();
		Assert.assertTrue(loginpage.rememberMe_IsSelected());
	}
	@Test // TC_04
	public void verifyRememberMeCheckbox() {
		loginpage = new LoginPage(driver);
		
		
	}

	@Test
	public void verifyLogin() {
		String userName = "spniju";
		String password = "spniju";
		String expectedUserName = "Spniju";
		loginpage = new LoginPage(driver);
		loginpage.login(userName, password);
		String actualUserName = loginpage.get_LoginUsersName();
		Assert.assertEquals(actualUserName, expectedUserName);
	}

}
