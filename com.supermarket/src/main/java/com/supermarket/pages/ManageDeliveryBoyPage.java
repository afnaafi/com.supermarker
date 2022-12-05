package com.supermarket.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.supermarket.utilities.GeneralUtilities;
import com.supermarket.utilities.PageUtility;
import com.supermarket.utilities.WaitUtility;

public class ManageDeliveryBoyPage {
	WebDriver driver;
	GeneralUtilities generalutilities;
	PageUtility pageutility;
	WaitUtility waitutility;

	@FindBy(xpath = "//li//a[@href='https://groceryapp.uniqassosiates.com/admin/list-deliveryboy']")
	private WebElement DeliveryBoyPage;
	@FindBy(xpath = "//div[@class='col-sm-12']//a[@class='btn btn-rounded btn-danger']")
	private WebElement newButton;
	@FindBy(xpath = "//div[@class='col-sm-12']//a[@class='btn btn-rounded btn-primary']")
	private WebElement searchButton;
	@FindBy(id = "name")
	private WebElement nameField;
	@FindBy(id = "email")
	private WebElement emailField;
	@FindBy(id = "username")
	private WebElement usernameField;
	@FindBy(id = "password")
	private WebElement passwordField;
	@FindBy(className = "btn-danger")
	private WebElement saveButton;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	private WebElement createNewSuccessAlert;
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement alreadyExistAlert;
	@FindBy(xpath = "//input[@id='un']")
	private WebElement searchNameField;
	@FindBy(xpath = "//button[@class='btn btn-block-sm btn-danger']")
	private WebElement searchUserButton;

	public ManageDeliveryBoyPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickDeliveryBoyPage() {
		DeliveryBoyPage.click();
	}

	public void createNewDeliveryBoy(String name, String email, String userName, String password) {
		generalutilities = new GeneralUtilities(driver);
		pageutility = new PageUtility(driver);
		newButton.click();
		nameField.sendKeys(name);
		emailField.sendKeys(email);
		usernameField.sendKeys(userName);
		passwordField.sendKeys(password);
		waitutility=new WaitUtility(driver);
		waitutility.waitForElementToBeVisible(20, "//button[@class='btn btn-danger']");
		pageutility.scroll_And_Click(saveButton);
	}

	public boolean createNewSuccess() {
		generalutilities = new GeneralUtilities(driver);
		return generalutilities.is_Displayed(createNewSuccessAlert);
	}

	public boolean isDuplicationPermitted() {
		generalutilities = new GeneralUtilities(driver);
		return generalutilities.is_Displayed(alreadyExistAlert);
	}

	public void clickSearch() {
		searchButton.click();
	}

	public void searchUser(String name) {
		searchNameField.sendKeys(name);
		searchUserButton.click();
	}

	public String IsSearchedUserAvailable(String name) {
		generalutilities = new GeneralUtilities(driver);
		WebElement searchedUsersList = driver.findElement(By.xpath("//tr//td[text()='" + name + "']"));
		return generalutilities.get_TextOfElement(searchedUsersList);
	}

}
