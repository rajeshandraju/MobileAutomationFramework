package com.ebay.screens.android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.ebay.screens.common.EBayElementMethodsImpl;

/**
 * This class is used to captured Search screen elements and buttons  
 */
public class SearchScreenAttributes extends EBayElementMethodsImpl{
	
	public SearchScreenAttributes(WebDriver webDriver) {
		super(webDriver);
	}
	
	// Locators of the eBay Search screen
    public By homePageSearchBox = By.id("com.ebay.mobile:id/search_box");
    public By productList = By.id("com.ebay.mobile:id/cell_collection_item");

    public By productDetailsName = By.id("com.ebay.mobile:id/textview_item_name");
    public By productDetailsPrice = By.id("com.ebay.mobile:id/textview_item_price");
    public By productDetailsAddToCart = By.id("com.ebay.mobile:id/button_add_to_cart");
    public By productDetailsViewInCart = By.id("com.ebay.mobile:id/button_view_in_cart");

    // Capturing the Cart Details
    public By cartProductName = By.id("com.ebay.mobile:id/title");
    public By cartProductPrice = By.id("com.ebay.mobile:id/item_price");
    public By cartRemoveProduct = By.id("com.ebay.mobile:id/remove_from_cart_button");

    // Capturing Android widget Button details
    public By removeConfirmation = By.id("android:id/button1");

    public By tvImage = By.xpath("//*[@text = 'LG 65UJ6300 65-inch UHD 4K Smart LED TV']");
    public By tvText = By.xpath("//*[@text = 'LG 65UJ6300 65-inch UHD 4K Smart LED TV");
    public By tvPrice = By.id("com.ebay.mobile:id/textview_item_price");

    public By cartTvText = By.xpath("//*[@text = 'LG 65UJ6300 65-inch UHD 4K Smart LED TV']");
    public By cartTvPrice = By.id("com.ebay.mobile:id/item_price");
    
    //Capturing Payment completion details
    public By continueButton = By.id("Continue");
    public By selectExistingCard = By.id("<radio_btn/>1");
    public By payAnOrder = By.id("Pay");
}
