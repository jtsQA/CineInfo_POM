package cine.info.utils;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import cine.info.enums.WaitApproach;
import cine.info.enums.WaitTime;

public final class WaitUtils {

	private static WebDriverWait wait;
	private static Duration duration = Duration.ofMillis(WaitTime.EXPLICIT_WAIT.getTime());

	public static WebElement performWait(WaitApproach waitApproach, WebDriver driver, By by) {
		wait = new WebDriverWait(driver, duration);
		WebElement element = null;
		switch (waitApproach) {
			case CLICKABLE:
				element = wait.until(ExpectedConditions.elementToBeClickable(by));
				break;
			case PRESENCE:
				element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
				break;
			case VISIBLE:
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
				break;
			case NONE:
			default:
				element = driver.findElement(by);
				break;
		}
		return element;
	}

	public static List<WebElement> performWaitForList(WaitApproach waitApproach, WebDriver driver, By by) {
		wait = new WebDriverWait(driver, duration);
		List<WebElement> element = null;
		switch (waitApproach) {
			case PRESENCE:
				element = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
				break;
			case VISIBLE:
				element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
				break;
			case NONE:
			default:
				element = driver.findElements(by);
				break;
		}
		return element;
	}

}
