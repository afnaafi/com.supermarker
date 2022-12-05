package com.supermarket.pages;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.supermarket.constants.Constants;
import com.supermarket.utilities.GeneralUtilities;
import com.supermarket.utilities.PageUtility;
import com.supermarket.utilities.WaitUtility;

public class LoginPage {
	WebDriver driver;
	Properties prop = new Properties();
	FileInputStream ip;
	GeneralUtilities generalutilities;
	PageUtility pageutility;
	WaitUtility waitutility;

	@FindBy(xpath = "//input[@name='username']")
	@CacheLookup
	private WebElement usernameElement;
	@FindBy(xpath = "//input[@name='password']")
	@CacheLookup
	private WebElement passwordElement;
	@FindBy(xpath = "//button[@class='btn btn-dark btn-block']")
	private WebElement SignInButton;
	@FindBy(xpath = "//div[@class='info']//a[text()=' Admin']")
	private WebElement profileElement;
	@FindBy(xpath = "//div[@class='icheck-dark']//label")
	private WebElement rememberMeElement;
	@FindBy(xpath = "//input[@id='remember']")
	WebElement rememberMeBox;
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement alertMessage;

	public LoginPage(WebDriver driver) {
		try {
			this.driver = driver;
			PageFactory.initElements(driver, this);
			ip = new FileInputStream(Constants.CONFIGURE_FILE_PATH);
			prop.load(ip);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void enterUserName(String username) {
		usernameElement.sendKeys(username);
	}

	public void enterPassword(String password) {
		passwordElement.sendKeys(password);
	}

	public void clickOnRememberMe() {
		rememberMeElement.click();
	}

	public void clickOnSignInButton() {
		SignInButton.click();
	}

	public boolean username_IsDisplayed() {
		generalutilities = new GeneralUtilities(driver);
		return generalutilities.is_Displayed(usernameElement);
	}

	public boolean password_IsDisplayed() {
		generalutilities = new GeneralUtilities(driver);
		return generalutilities.is_Displayed(passwordElement);
	}

	public boolean rememberMe_IsDispayed() {
		generalutilities = new GeneralUtilities(driver);
		return generalutilities.is_Displayed(rememberMeElement);
	}

	public boolean signIn_IsDispayed() {
		generalutilities = new GeneralUtilities(driver);
		return generalutilities.is_Displayed(SignInButton);
	}

	public boolean rememberMe_IsSelected() {
		generalutilities = new GeneralUtilities(driver);
		return generalutilities.is_Selected(rememberMeBox);
	}

	public boolean rememberMe_IsEnabled() {
		generalutilities = new GeneralUtilities(driver);
		return generalutilities.is_Enabled(rememberMeElement);
	}

	public void login() {
		String username = prop.getProperty("UserName");
		String password = prop.getProperty("Password");
		login(username, password);
	}

	public void login(String username, String password) {
		enterUserName(username);
		enterPassword(password);
		clickOnSignInButton();
	}

	public void alert_Message() {
		System.out.println(alertMessage.getText());
	}

	public boolean isInvalidAlertMessagePresent(String expectedText) {
		generalutilities = new GeneralUtilities(driver);
		return generalutilities.is_ExpectedTextPresent(alertMessage, expectedText);
	}

	public String get_LoginUsersName() {
		waitutility = new WaitUtility(driver);
		waitutility.waitForElementToBeVisible(10, "//div[@class='info']//a[text()=' Admin']");
		return profileElement.getText();
	}

	public String rememberMeText() {
		return rememberMeElement.getText();
	}

}
