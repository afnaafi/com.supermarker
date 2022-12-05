package com.supermarket.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.supermarket.utilities.GeneralUtilities;
import com.supermarket.utilities.PageUtility;
import com.supermarket.utilities.WaitUtility;

public class ManageContentPage {
	WebDriver driver;
	GeneralUtilities generalutilities;
	PageUtility pageutility;
	WaitUtility waitutility;

	@FindBy(xpath = "//a[@class='btn btn-rounded btn-danger']")
	private WebElement newButton;
	@FindBy(xpath = "//input[@name='main_img']")
	private WebElement imageupload;
	@FindBy(xpath = "//input[@id='title']")
	private WebElement titleLink;
	@FindBy(xpath = "//div[@class='note-editable card-block']")
	private WebElement descriptionLink;
	@FindBy(xpath = "//input[@id='page']")
	private WebElement pageLink;
	@FindBy(xpath = "//button[@class='btn btn-danger']")
	private WebElement saveButton;
	@FindBy(xpath = "//div[@class='col-sm-6']//h1")
	private WebElement manageContentPageHeading;
	@FindBy(xpath = "//li[@class='nav-item']//a[@href='https://groceryapp.uniqassosiates.com/admin/list-page']")
	private WebElement managePages;
	@FindBy(xpath = "//li[@class='nav-item']//a[@href='https://groceryapp.uniqassosiates.com/admin/list-footertext']")
	private WebElement manageFooterText;
	@FindBy(xpath = "//li[@class='nav-item']//a[@href='https://groceryapp.uniqassosiates.com/admin/list-contact']")
	private WebElement manageContact;
	@FindBy(xpath = "//tr//td//a[@href='https://groceryapp.uniqassosiates.com/admin/Footertext/edit?edit=2']")
	private WebElement footerActionButton;
	@FindBy(xpath = "//li[@class='nav-item']//a[@href='https://groceryapp.uniqassosiates.com/admin/list-news']")
	private WebElement manageNews;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	private WebElement deleteNewsSuccessAlert;

	public ManageContentPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean isManageContentPageHeadingPresent(String expectedText) {
		generalutilities = new GeneralUtilities(driver);
		return generalutilities.is_ExpectedTextPresent(manageContentPageHeading, expectedText);
	}

	public void createNewList() {
		newButton.click();
	}

	public void add_List(String title, String description, String page) {
		titleLink.sendKeys(title);
		descriptionLink.sendKeys(description);
		pageLink.sendKeys(page);

	}

	public void uploadimage(String imageName) {
		pageutility = new PageUtility(driver);
		pageutility.file_Upload(imageupload,
				System.getProperty("user.dir") + "\\src\\main\\resources\\ExcelFiles\\images\\" + imageName + ".jpg");

	}

	public void saveList() {
		pageutility = new PageUtility(driver);
		pageutility.scroll_And_Click(saveButton);
	}

	public String textOfProductTitle(String title) {
		generalutilities = new GeneralUtilities(driver);
		WebElement text = driver.findElement(By.xpath("//tbody//tr//td[text()='" + title + "']"));
		waitutility = new WaitUtility(driver);
		waitutility.fluent_Wait(30, 5, "//tbody//tr//td[text()='\" + title + \"']");
		return generalutilities.get_TextOfElement(text);
	}

	public void clickOnManagePages() {
		managePages.click();
	}

	public void clickOnManageFooterPage() {
		manageFooterText.click();
	}

	public void clickOnManageContact() {
		manageContact.click();
	}

	public void clickOnManageNews() {
		manageNews.click();
	}

	public void UpadateFooterDetails(String address, String email, String phone) {
		footerActionButton.click();
		WebElement address1 = driver.findElement(By.tagName("textarea"));
		address1.clear();
		address1.sendKeys(address);
		WebElement email1 = driver.findElement(By.id("email"));
		email1.clear();
		email1.sendKeys(email);
		WebElement phone1 = driver.findElement(By.id("phone"));
		phone1.clear();
		phone1.sendKeys(phone);
		WebElement updateButton = driver.findElement(By.tagName("button"));
		updateButton.click();
	}

	public boolean updateSucessAlert() {
		WebElement updateSuccess = driver.findElement(By.className("alert-dismissible"));
		generalutilities = new GeneralUtilities(driver);
		return generalutilities.is_Displayed(updateSuccess);
	}

	public void DeleteNews(String news) {
		generalutilities = new GeneralUtilities(driver);
		List<String> news1 = new ArrayList<String>();
		news1 = generalutilities.get_TextOfElement("//tr//td[1]");
		int pos = 0;
		for (pos = 0; pos < news1.size(); pos++) {
			if (news1.get(pos).equals(news)) {
				pos++;
				break;
			}
		}
		WebElement deleteButton = driver.findElement(By.xpath("//tr[" + pos + "]//td[2]//a[2]"));
		pageutility = new PageUtility(driver);
		pageutility.scroll_And_Click(deleteButton);
		pageutility.accept_Alert();
	}

	public boolean deleteNewsSuccess() {
		waitutility = new WaitUtility(driver);
		waitutility.waitForElementToBeVisible(20, "//div[@class='alert alert-success alert-dismissible']");
		return deleteNewsSuccessAlert.isDisplayed();
	}

}
