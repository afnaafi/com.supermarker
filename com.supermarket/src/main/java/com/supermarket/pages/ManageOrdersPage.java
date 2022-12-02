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

public class ManageOrdersPage {
	WebDriver driver;
	GeneralUtilities generalutilities;
	PageUtility pageutility;
	WaitUtility waitutility;

	@FindBy(xpath = "//li//a[@href='https://groceryapp.uniqassosiates.com/admin/list-order']")
	WebElement manageOrdersPageLink;
	@FindBy(xpath = "//tr//td[text()='671']")
	WebElement orderId;
	@FindBy(xpath = "//tr//td//a[text()='Change Status']")
	WebElement changeStatusOfAll;
	@FindBy(xpath = "Change Delivery Date")
	WebElement changeDeliveryDate;
	@FindBy(xpath = "//tr[1]//td//a[text()='Assign Delivery Boy']")
	WebElement assignDeliveryBoy;
	@FindBy(xpath = "//div[@class='row invoice-info']")
	WebElement viewDetailsOfOrder;

	public ManageOrdersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnPage() {
		manageOrdersPageLink.click();
	}

	public String changeDeliveryStatus(String orderId, String status) {
		List<String> orderIdS = new ArrayList<String>();
		generalutilities = new GeneralUtilities(driver);
		orderIdS = generalutilities.get_textofElements("//tbody//tr//td[1]");
		int pos = 0;
		for (pos = 0; pos < orderIdS.size(); pos++) {
			if (orderIdS.get(pos).equals(orderId)) {
				pos++;
				break;
			}
		}
		WebElement changeStatus = driver.findElement(By.xpath("//tr[" + pos + "]//td//a[text()='Change Status']"));
		pageutility = new PageUtility(driver);
		pageutility.scroll_And_Click(changeStatus);
		WebElement selectOrderIdStatus = driver
				.findElement(By.xpath("//select[@onchange='show_cancel(" + orderId + ")']"));
		pageutility.Select_ByValue(selectOrderIdStatus, status);
		WebElement updateButton = driver.findElement(By.xpath(
				"(//form[@action='https://groceryapp.uniqassosiates.com/admin/Order/update_status']//button[@name='Update_st'])["
						+ pos + "]"));
		updateButton.click();
		driver.navigate().back();
		WebElement statusChanged = driver.findElement(By.xpath("//tr[" + pos + "]//td[6]//span[1]"));
		return generalutilities.get_TextOfElement(statusChanged);
	}

	public void changeDeliveryDate(String orderId, String date) {
		List<String> orderIdS = new ArrayList<String>();
		generalutilities = new GeneralUtilities(driver);
		orderIdS = generalutilities.get_textofElements("//tbody//tr//td[1]");
		int pos = 0;
		for (pos = 0; pos < orderIdS.size(); pos++) {
			if (orderIdS.get(pos).equals(orderId)) {
				pos++;
				break;
			}
		}
		WebElement changeDeliveryDate = driver
				.findElement(By.xpath("//tr[" + pos + "]//td//a[text()='Change Delivery Date']"));
		pageutility = new PageUtility(driver);
		pageutility.scroll_And_Click(changeDeliveryDate);
		WebElement clickDate = driver
				.findElement(By.xpath("(//input[@class='jquery-datepicker__input datepicker1'])[" + pos + "]"));
		clickDate.sendKeys(date);
		WebElement updateButton = driver
				.findElement(By.xpath("//tbody//tr[" + pos + "]//td[6]//a[2]//following::button[2]"));
		updateButton.click();
	}

	public void assignDeliveryBoy(String orderId, String name) {
		List<String> orderIdS = new ArrayList<String>();
		generalutilities = new GeneralUtilities(driver);
		orderIdS = generalutilities.get_textofElements("//tbody//tr//td[1]");
		int pos = 0;
		for (pos = 0; pos < orderIdS.size(); pos++) {
			if (orderIdS.get(pos).equals(orderId)) {
				pos++;
				break;
			}
		}
		WebElement assignDeliveryBoy = driver
				.findElement(By.xpath("(//div//a[text()='Assign Delivery Boy'])[" + pos + "]"));
		pageutility = new PageUtility(driver);
		pageutility.scroll_And_Click(assignDeliveryBoy);
		WebElement deliveryBoyName = driver.findElement(By.xpath("(//select[@id='delivery_boy_id'])[" + pos + "]"));
		pageutility.Select_ByVisibleText(deliveryBoyName, name);
		WebElement updateButton = driver.findElement(By.xpath("(//button[@name='assign_del'])[" + pos + "]"));
		waitutility = new WaitUtility(driver);
		waitutility.waitForElementToBeClickable(3, "(//button[@name='assign_del'])[" + pos + "]");
		updateButton.click();
		WebElement UpadateDeliveryBoySuccess = driver
				.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible']"));
		generalutilities.is_Displayed(UpadateDeliveryBoySuccess);
	}

	public boolean successAlert() {
		WebElement upadteSuccessAlert = driver
				.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible']"));
		return generalutilities.is_Displayed(upadteSuccessAlert);
	}

	public void viewDetails(String orderId) {
		List<String> orderIdS = new ArrayList<String>();
		generalutilities = new GeneralUtilities(driver);
		orderIdS = generalutilities.get_textofElements("//tbody//tr//td[1]");
		int pos = 0;
		for (pos = 0; pos < orderIdS.size(); pos++) {
			if (orderIdS.get(pos).equals(orderId)) {
				pos++;
				break;
			}
		}
		WebElement viewdetails = driver.findElement(By.xpath("(//a[text()='View'])[" + pos + "]"));
		pageutility = new PageUtility(driver);
		pageutility.scroll_And_Click(viewdetails);

	}

	public boolean detailsDisplayed() {
		generalutilities = new GeneralUtilities(driver);
		return generalutilities.is_Displayed(viewDetailsOfOrder);
	}
}