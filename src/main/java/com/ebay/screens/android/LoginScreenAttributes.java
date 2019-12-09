package com.ebay.screens.android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ebay.screens.common.EBayElementMethodsImpl;

/**
 * This class is used to captured Login screen elements  
 */
public class LoginScreenAttributes extends EBayElementMethodsImpl {
	public LoginScreenAttributes(WebDriver webDriver) {
		super(webDriver);
	}
	//Login screen elements name
	public By userName = By.id("com.ebay.mobile:id/edit_text_username");
	public By password = By.id("com.ebay.mobile:id/edit_pwd_temp");
	public By signInButton = By.id("com.ebay.mobile:id/button_sign_in");
	
}
