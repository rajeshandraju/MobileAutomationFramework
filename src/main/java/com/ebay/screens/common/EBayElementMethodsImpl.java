package com.ebay.screens.common;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.NoSuchElementException;

import com.ebay.logger.EBayLogger;
import com.ebay.utilites.EBayDriverImpl;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class EBayElementMethodsImpl extends EBayDriverImpl implements EBayElementMethods {

	WebDriver webDriver = null;
	public final int timeOut = Integer.getInteger(configProp.getProperty("timeOut"));

	public EBayElementMethodsImpl(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	/**
	 * This method is used to load the new page
	 *
	 * @param locator element to be found
	 */
	@Override
	public void click(By locator) {
		try {
			WebElement element = webDriver.findElement(locator);
			if (element.isDisplayed()) {
				element.click();
			} else {
				EBayLogger.info(locator + "Element not found on this page");
			}
		} catch (TimeoutException e) {
			EBayLogger.logError(this.getClass().getName(), "click", "element not clicked " + locator);
			throw new NoSuchElementException(e.getMessage());
		}
	}

	/**
	 * This method is used to wait for an element to be visible
	 *
	 * @param targetElement element to be visible
	 * @return true if element is visible else throws TimeoutException
	 */

	@Override
	public Boolean waitForVisibility(By targetElement) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver, timeOut);
			wait.until(ExpectedConditions.visibilityOfElementLocated(targetElement));
			return true;
		} catch (TimeoutException e) {
			System.out.println("Element is not visible: " + targetElement);
			throw new TimeoutException(e.getMessage());

		}
	}

	/**
	 * Method to wait for an element until it is invisible
	 *
	 * @param targetElement element to be invisible
	 * @return true if element gets invisible else throws TimeoutException
	 */
	@Override
	public Boolean waitForInvisibility(By targetElement) {
		try {
			WebDriverWait wait = new WebDriverWait(webDriver, timeOut);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(targetElement));
			return true;
		} catch (TimeoutException e) {
			EBayLogger.logError(getClass().getName(), "", "Element is visible " + targetElement + e.getMessage());
			throw new TimeoutException();

		}
	}

	/**
	 * Method to tap on the screen on provided coordinates
	 *
	 * @param xPosition x coordinate to be tapped
	 * @param yPosition y coordinate to be tapped
	 */
	@Override
	public void tap(double xPosition, double yPosition) {
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		HashMap<String, Double> tapObject = new HashMap<String, Double>();
		tapObject.put("startX", xPosition);
		tapObject.put("startY", yPosition);
		js.executeScript("mobile: tap", tapObject);
	}

	/**
	 * This method is used to verify whether an element is existing on screen
	 *
	 * @param targetElement element to be present
	 * @return true if element is present else throws exception
	 * @throws InterruptedException Thrown when a thread is waiting, sleeping, or
	 *                              otherwise occupied, and the thread is
	 *                              interrupted, either before or during the
	 *                              activity.
	 */
	@Override
	public Boolean isElementPresent(By targetElement) throws InterruptedException {
		Boolean isPresent = webDriver.findElements(targetElement).size() > 0;
		return isPresent;
	}

	/**
	 * This method is used to find an element
	 *
	 * @param locator element to be found
	 * @return WebElement if found else throws NoSuchElementException
	 */
	@Override
	public WebElement findElement(By locator) {
		try {
			WebElement element = webDriver.findElement(locator);
			return element;
		} catch (NoSuchElementException e) {
			EBayLogger.logError(this.getClass().getName(), "findElement", "Element not found" + locator);
			throw new NoSuchElementException(e.getMessage());
		}
	}

	/**
	 * This method is used to find all the elements of specific locator
	 *
	 * @param locator element to be found
	 * @return return the list of elements if found else throws
	 *         NoSuchElementException
	 */
	@Override
	public List<WebElement> findElements(By locator) {
		try {
			List<WebElement> element = webDriver.findElements(locator);
			return element;
		} catch (NoSuchElementException e) {
			EBayLogger.logError(this.getClass().getName(), "findElements", "element not found" + locator);
			throw new NoSuchElementException(e.getMessage());
		}
	}

	/**
	 * This method is used to get the alert text message
	 *
	 * @return message text which is displayed
	 */
	@Override
	public String getAlertText() {
		try {
			Alert alert = webDriver.switchTo().alert();
			String alertText = alert.getText();
			return alertText;
		} catch (NoAlertPresentException e) {
			throw new NoAlertPresentException();
		}
	}
	/**
	 * This method is used to swipe on the screen on provided coordinates
	 *
	* @param startX   - start X coordinate to be tapped
	* @param endX     - end X coordinate to be tapped
	* @param startY   - start y coordinate to be tapped
	* @param endY     - end Y coordinate to be tapped
	* @param duration duration to be tapped
	*/
	@Override
	public void swipe(double startX, double startY, double endX, double endY, double duration) {
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		HashMap<String, Double> swipeObject = new HashMap<String, Double>();
		// swipeObject.put("touchCount", 1.0);
		swipeObject.put("startX", startX);
		swipeObject.put("startY", startY);
		swipeObject.put("endX", endX);
		swipeObject.put("endY", endY);
		swipeObject.put("duration", duration);
		js.executeScript("mobile: swipe", swipeObject);
	}

	/**
	 * This method is used to scroll down on screen from java-client 6
	 *
	 * @param swipeTimes       number of times swipe operation should work
	 * @param durationForSwipe time duration of a swipe operation
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void scrollDown(int swipeTimes, int durationForSwipe) {
		Dimension dimension = webDriver.manage().window().getSize();

		for (int i = 0; i <= swipeTimes; i++) {
			int start = (int) (dimension.getHeight() * 0.5);
			int end = (int) (dimension.getHeight() * 0.3);
			int x = (int) (dimension.getWidth() * .5);

			new TouchAction((AppiumDriver) webDriver).press(PointOption.point(x, start))
					.moveTo(PointOption.point(x, end))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(durationForSwipe))).release().perform();
		}
	}

	/**
	 * This method is used to scroll up on screen from java-client 6
	 *
	 * @param swipeTimes       number of times swipe operation should work
	 * @param durationForSwipe time duration of a swipe operation
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void scrollUp(int swipeTimes, int durationForSwipe) {

		Dimension dimension = webDriver.manage().window().getSize();

		for (int i = 0; i <= swipeTimes; i++) {
			int start = (int) (dimension.getHeight() * 0.3);
			int end = (int) (dimension.getHeight() * 0.5);
			int x = (int) (dimension.getWidth() * .5);

			new TouchAction((AppiumDriver) webDriver).press(PointOption.point(x, start))
					.moveTo(PointOption.point(x, end))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(durationForSwipe))).release().perform();
		}

	}

}
