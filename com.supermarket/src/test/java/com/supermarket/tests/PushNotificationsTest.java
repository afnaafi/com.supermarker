package com.supermarket.tests;

import org.testng.annotations.Test;

import com.supermarket.base.Base;
import com.supermarket.pages.LoginPage;
import com.supermarket.pages.PushNotificationsPage;
import com.supermarket.utilities.Excel;

public class PushNotificationsTest extends Base {
	LoginPage loginpage;
	PushNotificationsPage pnpage;
	Excel excel = new Excel();

	@Test
	public void getPushNotificationsPage() {
		String userName = "admin";
		String password = "admin";
		loginpage = new LoginPage(driver);
		pnpage = new PushNotificationsPage(driver);
		loginpage.login(userName, password);
		pnpage.get_PushNotificationsPage();
	}

	@Test
	public void send_Message() {
		String userName = "admin";
		String password = "admin";
		loginpage = new LoginPage(driver);
		pnpage = new PushNotificationsPage(driver);
		loginpage.login(userName, password);
		pnpage.get_PushNotificationsPage();
		excel.setExcelFile("PushNotificationData", "MessageToDeliveryBoy");
		String title = excel.get_CellData(0, 0);
		String description = excel.get_CellData(0, 1);
		pnpage.enter_Message(title, description);
		
	}

}
