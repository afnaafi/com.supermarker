package com.supermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.supermarket.base.Base;
import com.supermarket.pages.AdminUsersPage;
import com.supermarket.pages.HomePage;
import com.supermarket.pages.LoginPage;
import com.supermarket.utilities.GeneralUtilities;

public class AdminUsersTest extends Base {
	AdminUsersPage adminuserspage;
	HomePage homepage;
	LoginPage loginpage;
	GeneralUtilities generalutilities = new GeneralUtilities();
	SoftAssert softassert;

	@Test
	public void verifyNewUserCreated_Success() {
		loginpage = new LoginPage(driver);
		loginpage.login();
		softassert = new SoftAssert();
		adminuserspage = new AdminUsersPage(driver);
		adminuserspage.click_onAdminUsers();
		adminuserspage.click_OnNew();
		String name = "alen" + generalutilities.get_RandomNumber();
		adminuserspage.enterUserName(name);
		adminuserspage.enterPassword("alen");
		adminuserspage.clickSelectUser();
		adminuserspage.selectUserType("staff");
		adminuserspage.click_Onsave();
		softassert.assertTrue(adminuserspage.verifySuccess());
		adminuserspage.click_onSearch();
		adminuserspage.searchUser(name, "staff");
		softassert.assertTrue(adminuserspage.user_IsDisplayed());
		
	}

	@Test
	public void verifyUserDeleting_Successful() {
		loginpage = new LoginPage(driver);
		loginpage.login();
		adminuserspage = new AdminUsersPage(driver);
		adminuserspage.click_onAdminUsers();
		adminuserspage.click_OnNew();
		String name = "jeff" + generalutilities.get_RandomNumber();
		adminuserspage.enterUserName(name);
		adminuserspage.enterPassword("alen");
		adminuserspage.clickSelectUser();
		adminuserspage.selectUserType("staff");
		adminuserspage.click_Onsave();
		adminuserspage.selectNameAndDelete(name);
		softassert = new SoftAssert();
		softassert.assertTrue(adminuserspage.deleteUserSuccessAlert());
	}

	@Test
	public void verifyDeletedUserNotAvailable() {
		loginpage = new LoginPage(driver);
		loginpage.login();
		adminuserspage = new AdminUsersPage(driver);
		adminuserspage.click_onAdminUsers();
		adminuserspage.click_OnNew();
		String name = "jeff" + generalutilities.get_RandomNumber();
		adminuserspage.enterUserName(name);
		adminuserspage.enterPassword("alen");
		adminuserspage.clickSelectUser();
		adminuserspage.selectUserType("staff");
		adminuserspage.click_Onsave();
		adminuserspage.selectNameAndDelete(name);
		adminuserspage.click_onSearch();
		adminuserspage.searchUser(name, "staff");
		softassert = new SoftAssert();
		softassert.assertFalse(adminuserspage.userNotFound());
	}
	@Test
	public void verifyAdminUserDeactivation() {
		loginpage = new LoginPage(driver);
		loginpage.login();
		adminuserspage = new AdminUsersPage(driver);
		adminuserspage.click_onAdminUsers();
		Assert.assertTrue(adminuserspage.deactivateUser("jeff0"));
		
	}
	@Test(description = "test failed selectuser is not available")
	public void searchCreatedUser() {
		loginpage = new LoginPage(driver);
		loginpage.login();
		adminuserspage = new AdminUsersPage(driver);
		adminuserspage.click_onAdminUsers();
		adminuserspage.click_OnNew();
		String name = "johny" + generalutilities.get_RandomNumber();
		adminuserspage.enterUserName(name);
		adminuserspage.enterPassword("johny");
		adminuserspage.clickSelectUser();
		adminuserspage.selectUserType("partner");
		adminuserspage.click_Onsave();
		adminuserspage.click_onSearch();
		adminuserspage.searchUser(name, "partner");
		softassert = new SoftAssert();
		softassert.assertTrue(adminuserspage.user_IsDisplayed());
	}
	@Test
	public void verifyNewCreatedUserAbleToLogin() {
		loginpage = new LoginPage(driver);
		loginpage.login();
		homepage=new HomePage(driver);
		softassert = new SoftAssert();
		adminuserspage = new AdminUsersPage(driver);
		adminuserspage.click_onAdminUsers();
		adminuserspage.click_OnNew();
		String name = "helen" + generalutilities.get_RandomNumber();
		String password="helen";
		adminuserspage.enterUserName(name);
		adminuserspage.enterPassword(password);
		adminuserspage.clickSelectUser();
		adminuserspage.selectUserType("staff");
		adminuserspage.click_Onsave();
		homepage.user_LogOut();
		loginpage.login(name, password);
	}
	
	

}
