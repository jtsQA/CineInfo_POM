package cine.info.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public final class Driver {
	private static WebDriver driver;

	/**
	 * Private constructor to avoid external instantiation
	 */
	private Driver() {
	}

	public static WebDriver getDriver() {
		if (driver == null) {
			driver = new ChromeDriver();
		}
		return driver;
	}

	public static void quitDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

}