package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.ProductDetailsPage;
import pages.SearchPage;

public class SearchProductTest extends TestBase
{
    String productName ="Apple MacBook Pro" ;
    SearchPage searchObject;
    ProductDetailsPage detailsObject;
    @Test
    public void UserCanSearchForProduct()
    {
    	searchObject = new SearchPage(driver);
    	detailsObject = new ProductDetailsPage(driver);
    	searchObject.ProductSearch(productName);
    	searchObject.OpenProductDetailPage();
    	Assert.assertTrue(detailsObject.productNamebreadCrumb.getText().equalsIgnoreCase(productName));
    	//Assert.assertEquals(detailsObject.productNamebreadCrumb.getText(), productName);
    }
}
