package cine.info.utils;

import static cine.info.utils.WaitUtils.performWait;
import static cine.info.utils.WaitUtils.performWaitForList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import cine.info.enums.WaitApproach;

public class PageUtils {

	protected static WebDriver driver;

	public static void setDriver(WebDriver driver) {
		PageUtils.driver = driver;
	}

	public static boolean verifyDisplayed(By by) {
		return performWait(WaitApproach.VISIBLE, driver, by).isDisplayed();
	}

	public static void confirmClick(By by) {
		performWait(WaitApproach.CLICKABLE, driver, by).click();
	}

	public static void sendInputData(By by, String data) {
		WebElement element = performWait(WaitApproach.PRESENCE, driver, by);
		element.clear();
		element.sendKeys(data);
	}

	public static String retrieveData(By by) {
		return performWait(WaitApproach.PRESENCE, driver, by).getText();
	}

	public static void confirmClickFromList(By by, String inputData) {
		List<WebElement> elements = performWaitForList(WaitApproach.PRESENCE, driver, by);
		for (WebElement element : elements) {
			if (element.getText().contains(inputData)) {
				element.click();
				break;
			}
		}
	}

}
