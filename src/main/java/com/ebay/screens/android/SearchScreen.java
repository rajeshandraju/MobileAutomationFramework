package com.ebay.screens.android;

import com.ebay.model.Product;

/**
 * This Interface is used to define search screen methods  
 */
public interface SearchScreen {
	public void searchAnItemAndAddtoCart(Product product)throws InterruptedException;
	public boolean verifyProductDescInCart(Product product) throws InterruptedException;
	public void placeAnOrder() throws InterruptedException;

}
