package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.ProductDetailsPage;
import pages.SearchPage;

public class SearchProductUsingAutosugesst extends TestBase
{
    String productName = "Apple MacBook Pro"; 
	SearchPage searchObject;
    ProductDetailsPage detailsObject;
    @Test
    public void UserCanSearchAutoSuggest()
    {
        searchObject = new SearchPage(driver);
        searchObject.ProductSearchUsingAutoSuggest("MacB");
        detailsObject = new ProductDetailsPage(driver);
        Assert.assertEquals(detailsObject.productNamebreadCrumb.getText(), productName);
}
}