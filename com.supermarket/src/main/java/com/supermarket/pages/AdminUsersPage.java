package com.supermarket.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.supermarket.utilities.GeneralUtilities;
import com.supermarket.utilities.PageUtility;

public class AdminUsersPage {
	WebDriver driver;
	PageUtility pageutility;
	GeneralUtilities generalutilities;

	@FindBy(xpath = "//a[@href='https://groceryapp.uniqassosiates.com/admin/list-admin']//p")
	WebElement adminUsersHome;
	@FindBy(xpath = "//div[@class='col-sm-12']//a[@class='btn btn-rounded btn-danger']")
	WebElement newButton;
	@FindBy(xpath = "//select[@id='user_type']")
	WebElement selectUserType;
	@FindBy(xpath = "//button[@name='Create']")
	WebElement saveButton;
	@FindBy(xpath = "//button[@name='Create']/following::a[@type='button']")
	WebElement resetButton;
	@FindBy(xpath = "//input[@id='username']")
	WebElement createUsername;
	@FindBy(xpath = "//input[@id='password']")
	WebElement createPassword;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	WebElement successAlert;
	@FindBy(xpath = "//a[@class='btn btn-rounded btn-primary']")
	WebElement searchButton;
	@FindBy(xpath = "//input[@id='un']")
	WebElement searchUser;
	@FindBy(xpath = "//select[@id='ut']")
	WebElement searchSelectUser;
	@FindBy(xpath = "//button[@name='Search']")
	WebElement userSearchButton;
	@FindBy(xpath = "//div[@class='row']//a[text()='Reset']")
	WebElement userSearchResetButton;
	@FindBy(xpath = "//div[@class='card']//span[@class='badge bg-success']")
	WebElement userAvailable;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	WebElement deleteAlertSucces;
	@FindBy(xpath = "//div[@class='card']//span//center[text()='.........RESULT NOT FOUND.......']")
	WebElement noUserFound;

	public AdminUsersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void click_onAdminUsers() {
		pageutility = new PageUtility(driver);
		pageutility.scrollInTo_View(adminUsersHome);
		adminUsersHome.click();
	}

	public void click_OnNew() {
		newButton.click();
	}

	public void enterUserName(String username) {
		createUsername.sendKeys(username);
	}

	public void enterPassword(String password) {
		createPassword.sendKeys(password);
	}

	public void clickSelectUser() {
		selectUserType.click();
	}

	public void selectUserType(String value) {
		pageutility = new PageUtility(driver);
		pageutility.Select_ByValue(selectUserType, value);
	}

	public void click_Onsave() {
		saveButton.click();
	}

	public void click_OnReset() {
		resetButton.click();
	}

	public boolean verifySuccess() {
		return successAlert.isDisplayed();
	}

	public void click_onSearch() {
		searchButton.click();
	}

	public void searchUser(String username, String Usertype) {
		searchUser.sendKeys(username);
		pageutility = new PageUtility(driver);
		pageutility.Select_ByValue(searchSelectUser, Usertype);
		userSearchButton.click();
	}

	public boolean user_IsDisplayed() {
		generalutilities = new GeneralUtilities(driver);
		return generalutilities.is_Displayed(userAvailable);
	}

	public void selectNameAndDelete(String name) {
		WebElement nameInTable = driver.findElement(By.xpath("//tbody//tr//td[text()='" + name + "']"));
		WebElement deletebutton = driver
				.findElement(By.xpath("//tr//td[text()='" + name + "']/following::i[@class='fas fa-trash-alt']"));
		nameInTable.click();
		deletebutton.click();
		pageutility = new PageUtility(driver);
		pageutility.accept_Alert();

	}

	public boolean deleteUserSuccessAlert() {
		generalutilities = new GeneralUtilities(driver);
		return generalutilities.is_Displayed(deleteAlertSucces);
	}

	public boolean userNotFound() {
		generalutilities = new GeneralUtilities(driver);
		return generalutilities.is_Displayed(noUserFound);
	}

}
