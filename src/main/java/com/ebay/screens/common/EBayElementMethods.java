package com.ebay.screens.common;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * This Interface is used to define generic screen event methods  
 */
public interface EBayElementMethods {
	public void click(By locator);
	public Boolean waitForVisibility(By targetElement);
	public Boolean waitForInvisibility(By targetElement);
	public Boolean isElementPresent(By targetElement)throws InterruptedException;
	public void tap(double xPosition, double yPosition);
	public WebElement findElement(By locator);
	public List<WebElement> findElements(By locator);
	public String getAlertText();
	public void swipe(double startX, double startY, double endX, double endY, double duration);
	public void scrollDown(int swipeTimes, int durationForSwipe);
	public void scrollUp(int swipeTimes, int durationForSwipe);	
}
