package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.ProductDetailsPage;
import pages.SearchPage;

public class ChangeCurrencyTest extends TestBase
{
   HomePage homeObject;
   ProductDetailsPage detailsObject;
   String productName = "Apple MacBook Pro";
   SearchPage searchObject;
  
   @Test(priority=1)
   public void UserCanChangeCurrency()
   {
      homeObject = new HomePage(driver);
      homeObject.changeCurrency();
      detailsObject = new ProductDetailsPage(driver);
   }
   @Test(priority=2)
   public void UserCanSearchWithAutoSuggest()
   {
	   searchObject = new SearchPage(driver);
	   searchObject.ProductSearchUsingAutoSuggest("MacB");
	   detailsObject = new ProductDetailsPage(driver);
	   Assert.assertEquals(detailsObject.productNamebreadCrumb.getText(),productName);
	   Assert.assertTrue(detailsObject.prooductPricelbl.getText().contains("$"));	
   }
   
   
   
   
   
   
   
}
