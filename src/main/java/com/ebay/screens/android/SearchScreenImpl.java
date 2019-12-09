package com.ebay.screens.android;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.ebay.logger.EBayLogger;
import com.ebay.model.Product;
import org.testng.Assert;
import java.util.Random;

/**
 * This class contains all methods to search an items, add items to the cart and place an order
 * 
 */
public class SearchScreenImpl implements SearchScreen {
	
	SearchScreenAttributes searchScreenAttributes;
	Product product = new Product();
	public SearchScreenImpl(WebDriver webDriver) {
		searchScreenAttributes = new SearchScreenAttributes(webDriver);	
	}
    /**
     * This method is used to search an item
     *
     * @param searchText
     *            - Text to be searched
     */
	@Override
	public void searchAnItemAndAddtoCart(Product product) throws InterruptedException {


        EBayLogger.info(" Search for an Item");
        int elementCount = 0, selectElementNumber = 0;
        try 
        {
            String productNameTxt;
            String productPriceTxt;
            WebElement productName = null; 
            WebElement productPrice = null;
            
            WebElement searchBox = searchScreenAttributes.findElement(searchScreenAttributes.homePageSearchBox);
            WebElement addToCart_btn = searchScreenAttributes.findElement(searchScreenAttributes.productDetailsAddToCart);
            if(searchBox!=null)
            {
                searchBox.click();
                searchBox = searchScreenAttributes.findElement(searchScreenAttributes.homePageSearchBox);
                if(searchBox!=null)
                {
                    searchBox.sendKeys(product.getDescription());   
                    List<WebElement> elements = searchScreenAttributes.findElements(searchScreenAttributes.productList);
                    if(elements!=null)
                    {
                        elementCount = elements.size();
                        if (elementCount > 1) 
                        {
                        	Random random = new Random();
                        	
                            selectElementNumber = random.nextInt(elementCount-1);
                            elements.get(selectElementNumber).click(); 
                            productName = searchScreenAttributes.findElement(searchScreenAttributes.productDetailsName);
                            productPrice = searchScreenAttributes.findElement(searchScreenAttributes.productDetailsName);
                            
                            if(productName != null && productPrice != null)
                            {
                                productNameTxt = productName.getText();
                                productPriceTxt = productPrice.getText();
                                EBayLogger.info("Product Name :" + productNameTxt + "  Prodcut Price :" + productPriceTxt);
                            }
                            else
                            {
                                EBayLogger.info("Product Name & Prodcut Cost are not displayed");
                            }
                        }
                    }
                    else
                    {
                        EBayLogger.info("No Products displayed for the search string " + product.getDescription());
                    }
                }
            }
            if (addToCart_btn != null) {
                addToCart_btn.click();
                EBayLogger.info("Add an item to cart success");
            }
            else{
                EBayLogger.info("Add item to cart failed");
            }
        }
        catch (Exception e) 
        {
            EBayLogger.logError(getClass().getName(), "Search for an item", "Unable to select the product");
        }       	
	}
		
    /**
     * This method is used to search an item with valid product description and add to the cart
     *
     * @param productDesc
     *            - product description will be validated
     */
	@Override
	public boolean verifyProductDescInCart(Product product) throws InterruptedException {

		EBayLogger.info("Verifying the Product Name, Product Price and Product Description");
        
        boolean testresult = false;
        WebElement elementPrice;
        String textOnELement;      
        try {
            List<WebElement> elements = searchScreenAttributes.findElements(searchScreenAttributes.cartProductName);
            for (WebElement element : elements) {
                textOnELement = element.getText();
                if (textOnELement.contains(product.getProductName()) && textOnELement.contains(product.getDescription())) {
                    elementPrice = searchScreenAttributes.findElement(searchScreenAttributes.cartProductPrice);
                    Assert.assertEquals(element.getText(), product.getDescription(), "Verified Product description");
                    Assert.assertEquals(elementPrice.getText(), product.getPrice(), "Verified Product Price");
                    testresult = true;
                    break;
                }
            }

            if (!testresult) {
            	EBayLogger.info("Product details are not correctly displayed in the Cart");
            }
        }
        catch (Exception e) {
        	EBayLogger.logError(getClass().getName(), "Verify Product", "Product Details verification failed");
        }
        return testresult;
    }
	
	/**
     * This method is used to place an order
     */
	@Override
	public void placeAnOrder() throws InterruptedException {
    
		try {
        	searchScreenAttributes.click(searchScreenAttributes.continueButton);
        	searchScreenAttributes.click(searchScreenAttributes.selectExistingCard);
        	searchScreenAttributes.click(searchScreenAttributes.payAnOrder);
        }
        catch (Exception e) {
        	EBayLogger.logError(getClass().getName(), "Place an order", "Unable to make a payment" +e.getMessage());
        	
        }
    }

}
