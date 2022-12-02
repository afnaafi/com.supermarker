package com.supermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.supermarket.base.Base;
import com.supermarket.pages.LoginPage;
import com.supermarket.pages.PushNotificationsPage;
import com.supermarket.utilities.Excel;

public class PushNotificationsTest extends Base {
	LoginPage loginpage;
	PushNotificationsPage pnpage;
	Excel excel = new Excel();
	SoftAssert softassert;

	@Test
	public void verifyPushNotificationsPageDisplay() {
		String userName = "admin";
		String password = "admin";
		loginpage = new LoginPage(driver);
		pnpage = new PushNotificationsPage(driver);
		loginpage.login(userName, password);
		pnpage.get_PushNotificationsPage();
		softassert = new SoftAssert();
		softassert.assertTrue(pnpage.descriptionTextDisplay());
		softassert.assertTrue(pnpage.titleTextDisplay());
		softassert.assertTrue(pnpage.sendButtonDisplay());
		softassert.assertTrue(pnpage.resetButtonDisplay());
		softassert.assertAll();

	}

	@Test(groups="Smoke")
	public void verifySend_MessageFunctionality() {
	
		loginpage = new LoginPage(driver);
		pnpage = new PushNotificationsPage(driver);
		loginpage.login();
		pnpage.get_PushNotificationsPage();
		excel.setExcelFile("PushNotificationData", "MessageToDeliveryBoy");
		String title = excel.get_CellData(0, 0);
		String description = excel.get_CellData(0, 1);
		pnpage.enter_Message(title, description);
		Assert.assertTrue(pnpage.messageSendSuccess());
	}

}
