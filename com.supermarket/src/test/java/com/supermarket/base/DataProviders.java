package com.supermarket.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;

import com.supermarket.utilities.GeneralUtilities;

public class DataProviders {
	WebDriver driver;
	GeneralUtilities generlautilities = new GeneralUtilities(driver);

	@DataProvider(name = "managedeliveryboydetails")
	public Object[][] deliverBoyDetails() {
		return new Object[][] {
				{ "Freddy" + generlautilities.get_RandomNumber() + "",
						"freddy" + generlautilities.get_RandomNumber() + "@gmail.com", "freddy" },
				{ "Akash" + generlautilities.get_RandomNumber() + "",
						"akash" + generlautilities.get_RandomNumber() + "@gmail.com", "akash" } };
	}
}
