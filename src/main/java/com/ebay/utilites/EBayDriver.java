package com.ebay.utilites;

import java.lang.reflect.Method;
import java.net.MalformedURLException;

public interface EBayDriver {
	public void startAppiumServer() throws Exception;
	public void stopAppiumServer() throws Exception;
	public void createEBayDriver(String osName, Method methodName)throws Exception;
	public void shutdownEBayDriver();
	public void laodPropertiesFile(String platformName)throws Exception;
	public void androidOSDriver(String appLocationPath, Method MethodName) throws MalformedURLException;
	public String readAppPath(String invokePlatformName);

}
