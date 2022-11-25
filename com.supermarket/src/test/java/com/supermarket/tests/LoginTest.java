package com.supermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.supermarket.base.Base;
import com.supermarket.constants.Constants;
import com.supermarket.pages.LoginPage;
import com.supermarket.utilities.Excel;


public class LoginTest extends Base {
	LoginPage loginpage;
	Excel excel = new Excel();
	SoftAssert softassert;

	@Test
	public void verifyLoginPageDisplay() {
		loginpage = new LoginPage(driver);
		softassert = new SoftAssert();
		softassert.assertTrue(loginpage.username_IsDisplayed());
		softassert.assertTrue(loginpage.password_IsDisplayed());
		softassert.assertTrue(loginpage.rememberMe_IsDispayed());
		softassert.assertTrue(loginpage.signIn_IsDispayed());
	}

	@Test
	public void verifyStafLoginFunctionality() {
		loginpage = new LoginPage(driver);
		excel.setExcelFile("LoginDatas", "ValidLoginCredentials");
		String userName = excel.get_CellData(0, 0);
		String password = excel.get_CellData(0, 1);
		loginpage.login(userName, password);
		String actualUserName = loginpage.get_LoginUsersName();
		Assert.assertEquals(Constants.EXPECTEDUSERNAME, actualUserName);

	}

	@Test
	public void verifyInvalidLoginAlertMessage() {
		loginpage = new LoginPage(driver);
		excel.setExcelFile("LoginDatas", "InvalidLoginCredentilas");
		String userName = excel.get_CellData(0, 0);
		String password = excel.get_CellData(0, 1);
		loginpage.login(userName, password);
		loginpage.alert_Message();
		Assert.assertTrue(loginpage.isInvalidAlertMessagePresent(Constants.INVALIDUSERALERT));
	}

	@Test
	public void verifyRememberMe_IsSelected() {
		loginpage = new LoginPage(driver);
		softassert = new SoftAssert();
		softassert.assertFalse(loginpage.rememberMe_IsSelected());
		loginpage.clickOnRememberMe();
		softassert.assertTrue(loginpage.rememberMe_IsSelected());
	}

	@Test
	public void verifyRememberMeCheckboxText() {
		loginpage = new LoginPage(driver);
		String actualText = loginpage.rememberMeText();
		Assert.assertEquals(Constants.expectedRememberMeText, actualText);
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
