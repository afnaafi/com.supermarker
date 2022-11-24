package com.supermarket.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.supermarket.utilities.GeneralUtilities;
import com.supermarket.utilities.PageUtility;

public class PushNotificationsPage {
	WebDriver driver;
	GeneralUtilities generalutilities;
	PageUtility pageutility;

	@FindBy(xpath = "//a[@href='https://groceryapp.uniqassosiates.com/admin/list-notifications']")
	WebElement pushNotifications;
	@FindBy(xpath = "//input[@id='title']")
	WebElement titleText;
	@FindBy(xpath = "//input[@id='description']")
	WebElement descriptionText;
	@FindBy(xpath = "	//button[@class='btn btn-block-sm btn-info']")
	WebElement sendButton;

	public PushNotificationsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void get_PushNotificationsPage() {
		pushNotifications.click();
	}

	public void enter_Title(String title) {
		titleText.sendKeys(title);
	}

	public void enter_Description(String description) {
		descriptionText.sendKeys(description);
	}

	public void send_Message() {
		sendButton.click();
	}
	public void enter_Message(String title, String description) {
		 enter_Title(title);
		 enter_Description(description);
		 send_Message();
	}

}
