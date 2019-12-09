package com.ebay.utilites;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.ebay.logger.EBayLogger;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

/**
 * This class contains methods to create a new session and destroy the session
 * after the test(s) execution is over. Each test extends this class.
 */
public class EBayDriverImpl implements EBayDriver {

	public FileInputStream configFilePath;
	@SuppressWarnings("rawtypes")
	public static AndroidDriver androidDriver = null;
	protected static Properties configProp = new Properties();
	protected File file = new File("");
	
    /**
     * This method will be used to start the Appium server. Calls startAppiumServer method to start
     * the session depending upon your OS(it will be android OS or iOS).
     * 
     * @throws Exception
     *             Unable to start the appium server
     */
	
	@BeforeSuite
	public void startAppiumServer() throws Exception {
		// Here logic need to be implemented to start Appium Server

	}
    /**
     * This method is used to stop the Appium server.Calls stopAppiumServer method to stop
     * session depending upon your OS.
     * 
     * @throws Exception
     *             Unable to stop appium server
     */
	@AfterSuite
	public void stopAppiumServer() throws Exception {
		// Here logic need to be implemented to stop Appium Server

	}
    /**
     * This method creates the driver depending upon the passed parameter
     * (android or iOS) and loads the properties files (config and test data
     * properties files).
     * 
     * @param osName
     *            android (or iOS)
     * @param methodName
     *            - name of the method under execution
     * @throws Exception
     *             issue while loading properties files or creation of driver.
     */

    @BeforeMethod
	@Override
	public void createEBayDriver(String osName, Method methodName) throws Exception {

        // making os as default if nothing passed
        if (!osName.equalsIgnoreCase("android")) {
        	osName = "android";
        }
        
        // Load the log4j properties file
        laodPropertiesFile(osName);
        File propertiesFile = new File(file.getAbsoluteFile() + EBayConstants.LOG4J_FILE_PATH);
        PropertyConfigurator.configure(propertiesFile.toString());
        EBayLogger.info("Log4j has configured ");
        
        if (osName.equalsIgnoreCase("android")) {
            String buildPath = readAppPath(osName);
            androidOSDriver(buildPath, methodName);
            EBayLogger.info("Android driver has been created");
        }
	}

    /**
     * This method is used to quit the driver after the execution of test(s)
     */
	@AfterMethod
	public void shutdownEBayDriver() {
		EBayLogger.info("Shutting down the AndrodDriver .....");
		androidDriver.quit();
	}

    /**
     * This method is used to load the properties file as config and file having test data.
     * 
     * @param platformName
     *            to load specific test data file.
     * @throws Exception
     *             property files are not loaded successfully
     */
	@Override
	public void laodPropertiesFile(String platformName) throws Exception {
		try 
		{
		configFilePath = new FileInputStream(file.getAbsoluteFile() + EBayConstants.CONFIG_FILE_PATH);
		configProp.load(configFilePath);
		}catch(IOException e) {
			EBayLogger.logError(getClass().getName(),"IOException",e.getMessage());
		}
		catch(Exception e) {
			EBayLogger.logError(getClass().getName(),"Exception",e.getMessage());
		}
	}

    /**
     * This method is used to create the android driver
     * 
     * @param appLocationPath
     *            - path to pick the location of the app
     * @param methodName
     *            - name of the method under execution
     * @throws MalformedURLException
     *             Thrown to indicate that a malformed URL has occurred.
     */
	@Override
	public void androidOSDriver(String appLocationPath, Method MethodName) throws MalformedURLException {
		
		File appFilePath = new File(appLocationPath);
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, configProp.getProperty("platformName"));
		desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, configProp.getProperty("deviceName"));
		desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, configProp.getProperty("platformVersion"));
		desiredCapabilities.setCapability(MobileCapabilityType.UDID, configProp.getProperty("udid"));
		desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, configProp.getProperty("noReset"));
		desiredCapabilities.setCapability(MobileCapabilityType.CLEAR_SYSTEM_FILES, configProp.getProperty("clearSystemFiles"));
		desiredCapabilities.setCapability(MobileCapabilityType.APP, appFilePath.getAbsolutePath());
		androidDriver = new AndroidDriver<WebElement>(new URL(configProp.getProperty("URLPath")),desiredCapabilities);
		
		//By default screenOrientation is PORTRAIT
        if (configProp.getProperty("screenOrientation").equalsIgnoreCase("LANDSCAPE")) 
            androidDriver.rotate(ScreenOrientation.LANDSCAPE);
        else if (configProp.getProperty("screenOrientation").equalsIgnoreCase("PORTRAIT"))
        androidDriver.rotate(ScreenOrientation.PORTRAIT);

	}

    /**
     * This method is used to return the app path file location.
     * 
     * @param invokeDriver
     *             this parameter is used to load test data file.
     */
	@Override
	public String readAppPath(String invokePlatformName) {
        String appFilePath = null;
        if (invokePlatformName.equals("android")) {
        	appFilePath = configProp.getProperty("AndroidAppFilePath");
            return appFilePath;
        }
        else {
        	EBayLogger.logError(getClass().getName(), "appFileLoadingError:", "Exception while reading app File");
        }
        return appFilePath;
    }

}
