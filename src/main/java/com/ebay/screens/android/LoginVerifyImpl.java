package com.ebay.screens.android;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.ebay.logger.EBayLogger;
import com.ebay.model.Login;

public class LoginVerifyImpl implements LoginVerify {

	LoginScreenAttributes loginScreenAttributes;
	public LoginVerifyImpl(WebDriver webDriver) {
		loginScreenAttributes = new LoginScreenAttributes(webDriver);
	}
    /**
     * This method to login to android EBay app
     *
     * @param userName
     *            emailId to be used for login
     * @param pwd
     *            - need to use valid password
     */
	@Override
	public void eBayLogin(Login login) throws InterruptedException {
		try {
			WebElement userSignIn = loginScreenAttributes.findElement(loginScreenAttributes.signInButton);
			userSignIn.click();
			WebElement userNameTxtBox = loginScreenAttributes.findElement(loginScreenAttributes.userName);
			WebElement pwdTxtBox = loginScreenAttributes.findElement(loginScreenAttributes.password);
			if(userNameTxtBox != null)
				userNameTxtBox.clear();						
			if (pwdTxtBox != null)
				pwdTxtBox.clear();
			userNameTxtBox.sendKeys(login.getLastName());
			pwdTxtBox.sendKeys(login.getPassword());
			 userSignIn = loginScreenAttributes.findElement(loginScreenAttributes.signInButton);
             if (userSignIn != null) {
            	 userSignIn.click();
                 EBayLogger.info("User Loggedin Successfully ");
             }
		}catch(Exception e) {
			EBayLogger.logError(getClass().getName(),"UserLogin","User unable to Login into the app" + e.getMessage());
		}
	}
}
