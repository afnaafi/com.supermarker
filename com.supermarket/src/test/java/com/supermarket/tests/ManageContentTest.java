package com.supermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.supermarket.base.Base;
import com.supermarket.constants.Constants;
import com.supermarket.pages.HomePage;
import com.supermarket.pages.LoginPage;
import com.supermarket.pages.ManageContentPage;
import com.supermarket.utilities.Excel;
import com.supermarket.utilities.GeneralUtilities;


public class ManageContentTest extends Base {
	HomePage homepage;
	LoginPage loginpage;
	ManageContentPage managecontentpage;
	Excel excel = new Excel();
	GeneralUtilities generalutilities = new GeneralUtilities();

	@Test
	public void verifyUserAbleToCreateNewList() {
		loginpage = new LoginPage(driver);
		loginpage.login();
		homepage = new HomePage(driver);
		homepage.clickOnLink();
		managecontentpage = new ManageContentPage(driver);
		managecontentpage.createNewList();
		excel.setExcelFile("FoodlistPage", "FoodAndDescription");
		String title = excel.get_CellData(0, 0);
		String timeStamp = generalutilities.get_TimeStamp();
		String description = excel.get_CellData(0, 1);
		String page = excel.get_CellData(0, 3);
		title = title + "_" + timeStamp;
		page = page + generalutilities.get_RandomNumber();
		managecontentpage.add_List(title, description, page);
		managecontentpage.uploadimage();
		managecontentpage.saveList();
	}

	@Test
	public void verifyProductTitle() {
		loginpage = new LoginPage(driver);
		loginpage.login();
		homepage = new HomePage(driver);
		homepage.clickOnLink();
		managecontentpage = new ManageContentPage(driver);
		managecontentpage.createNewList();
		excel.setExcelFile("FoodlistPage", "FoodAndDescription");
		String title = excel.get_CellData(0, 0);
		String timeStamp = generalutilities.get_TimeStamp();
		String description = excel.get_CellData(0, 1);
		String page = excel.get_CellData(0, 3);
		title = title + "_" + timeStamp;
		page = page + generalutilities.get_RandomNumber();
		managecontentpage.add_List(title, description, page);
		managecontentpage.uploadimage();
		managecontentpage.saveList();
		String expectedProductTitle = Constants.EXPECTEDPRODUCT + generalutilities.get_RandomNumber();
		String actualProductTitle = managecontentpage.textOfProductTitle(title);
		System.out.println(expectedProductTitle);
		System.out.println(actualProductTitle);
		Assert.assertEquals(expectedProductTitle, actualProductTitle);

	}

	@Test
	public void verifyFooterUpadeDetailsIsSuccess() {
		loginpage = new LoginPage(driver);
		loginpage.login();
		homepage = new HomePage(driver);
		homepage.clickOnLink();
		managecontentpage = new ManageContentPage(driver);
		managecontentpage.clickOnManageFooterPage();
		String address="A-Nation, Z Street";
		String name="fedry"+ generalutilities.get_RandomNumber();
		String email=name+"@gmail.com";
		String phone="884555";
		managecontentpage.UpadateFooterDetails(address, email, phone);
		Assert.assertTrue(managecontentpage.updateSucessAlert());
	}
	@Test
	public void verifyDeleteNewsIsSuccess() {
		loginpage = new LoginPage(driver);
		loginpage.login();
		homepage = new HomePage(driver);
		homepage.clickOnLink();
		managecontentpage = new ManageContentPage(driver);
		managecontentpage.clickOnManageNews();
		managecontentpage.DeleteNews("Stock Clearance");
		Assert.assertTrue(managecontentpage.deleteNewsSuccess());
		
		
	}
}
