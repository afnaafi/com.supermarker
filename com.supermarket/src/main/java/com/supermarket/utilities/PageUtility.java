package com.supermarket.utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class PageUtility {
	WebDriver driver;
	Actions actions;
	JavascriptExecutor js;

	public PageUtility(WebDriver driver) {
		this.driver = driver;
	}

	public void Select_ByIndex(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	public void Select_ByValue(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}

	public void Select_ByVisibleText(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}

	public void click_OnElement(WebElement element) {
		actions = new Actions(driver);
		actions.click(element).build().perform();

	}

	public void scroll_Down() {
		js = (JavascriptExecutor) driver;// explicit type conversion
		js.executeScript("window.scrollBy(0,1000)");
	}

	public void scrollInTo_View(WebElement element) {

		js = (JavascriptExecutor) driver;// explicit type conversion
		js.executeScript("arguments[0].scrollIntoView();", element);

	}

	public boolean is_Visible(String xpath) {
		try {
			driver.findElement(By.xpath(xpath));
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public boolean is_Clicked(WebElement element) {
		try {
			element.click();
			return false;
		} catch (Exception e) {
			return true;
		}
	}

	public void scroll_And_Click(WebElement element)
	{
		int x=0;
		js=(JavascriptExecutor) driver;
	
		while(is_Clicked(element))
		{
			js.executeScript("window.scrollBy(0," + x + ")");
			x=x+20;
			
		}}

	public void clickOn_WebElement(WebElement element) {

		js = (JavascriptExecutor) driver;// explicit type conversion
		js.executeScript("arguments[0].click();", element);
	}

	public void file_Upload(WebElement element, String path) {
		element.sendKeys(path);
	}

	public void file_UploadUsingRobot(WebElement element, String path) throws AWTException {

		actions = new Actions(driver);
		actions.moveToElement(element).click().build().perform();
		Robot robot = new Robot();
		StringSelection ss = new StringSelection(path);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);// copies file to clipboard ie cntrl+c;
		robot.delay(1000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		robot.delay(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

	}

	public void accept_Alert() {
		driver.switchTo().alert().accept();
	}

	public void sendKey_accept_Alert(String message) {
		driver.switchTo().alert().sendKeys(message);
		driver.switchTo().alert().accept();
	}

}
