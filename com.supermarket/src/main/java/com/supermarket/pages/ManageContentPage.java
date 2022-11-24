package com.supermarket.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.supermarket.utilities.GeneralUtilities;
import com.supermarket.utilities.PageUtility;

public class ManageContentPage {
	WebDriver driver;
	GeneralUtilities generalutilities;
	PageUtility pageutility;

	@FindBy(xpath = "//a[@class='btn btn-rounded btn-danger']")
	WebElement newButton;
	@FindBy(xpath = "//input[@name='main_img']")
	WebElement imageupload;
	@FindBy(xpath = "//input[@id='title']")
	WebElement titleLink;
	@FindBy(xpath = "//div[@class='note-editable card-block']")
	WebElement descriptionLink;
	@FindBy(xpath = "//input[@id='page']")
	WebElement pageLink;
	@FindBy(xpath = "//button[@class='btn btn-danger']")
	WebElement saveButton;
	@FindBy(xpath = "//div[@class='col-sm-6']//h1")
	WebElement manageContentPageHeading;

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

	public void uploadimage() {
		pageutility = new PageUtility(driver);
		pageutility.file_Upload(imageupload,
		System.getProperty("user.dir") + "\\src\\main\\resources\\ExcelFiles\\Noodles.jpg");
		
	}

	public void saveList() {
		pageutility = new PageUtility(driver);
		// pageutility.scrollInTo_View(saveButton);
		// pageutility.click_OnElement(saveButton);
		// saveButton.click();
		pageutility.scroll_And_Click(saveButton);
	}

	public String textOfProductTitle(String title) {
		generalutilities = new GeneralUtilities(driver);
		WebElement text = driver.findElement(By.xpath("//tbody//tr//td[text()='" + title + "']"));
		System.out.println(generalutilities.get_TextOfElement(text));
		return generalutilities.get_TextOfElement(text);
	}
}
//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