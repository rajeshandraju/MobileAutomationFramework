package com.ebay.testcases;

import java.util.List;

import org.testng.annotations.Test;

import com.ebay.logger.EBayLogger;
import com.ebay.model.Login;
import com.ebay.model.Product;
import com.ebay.screens.android.LoginVerifyImpl;
import com.ebay.screens.android.SearchScreenImpl;
import com.ebay.utilites.EBayDriverImpl;
import com.ebay.utilites.ReadEBayExcelTestCaseData;

public class EBayTestCaseRun extends EBayDriverImpl{
	   
		SearchScreenImpl searchScreen;
		@SuppressWarnings("rawtypes")
		List eBayTestListData;
		Login login;
		Product product;
	/**
     * This method is used to login into eBay,search an item, 
     * add to cart and place an order
     * 
     * @throws InterruptedException
     *             Thrown when a thread is waiting, sleeping, or otherwise
     *             occupied, and the thread is interrupted, either before or
     *             during the activity.
     */
    @Test
    public void SearchAndPlaceAnOrder() throws InterruptedException {
        EBayLogger.info("OrderPlace_Test_001 - Place an Order Start ");
        try {
        	
        	 eBayTestListData = ReadEBayExcelTestCaseData.getEBayTestData(0);
        	login =  (Login) eBayTestListData.get(0);
        	product = (Product) eBayTestListData.get(1);
        	 
            LoginVerifyImpl loginVerify = new LoginVerifyImpl(androidDriver);
            loginVerify.eBayLogin(login);
        	searchScreen = new SearchScreenImpl(androidDriver);
            searchScreen.searchAnItemAndAddtoCart(product);
           // searchScreen.addAnItemtoCart();
            searchScreen.verifyProductDescInCart(product);
            searchScreen.placeAnOrder();
            EBayLogger.info("OrderPlace_Test_001 - Place an Order End ");
        }
        catch (Exception e) { 
            EBayLogger.logError(getClass().getName(), "OrderPlace_Test_001","We can unable to place an order ");
        }
    }

}
