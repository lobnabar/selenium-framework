package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.WishlistPage;

public class AddProductToWishlistTest extends TestBase 
{
    SearchPage searchPage;
    ProductDetailsPage productDetails;
    WishlistPage wishlistObject;
    String productName = "Apple MacBook Pro";
	
	@Test(priority=1)
	public void UserCanSearchAutoSuggest()
    {
        searchPage = new SearchPage(driver);
        searchPage.ProductSearchUsingAutoSuggest("MacB");
        productDetails = new ProductDetailsPage(driver);
        Assert.assertEquals(productDetails.productNamebreadCrumb.getText(), productName);
    }
	@Test(priority=2)
	public void UserCanAddProductToWishlist() throws InterruptedException {
		productDetails = new ProductDetailsPage(driver);
		productDetails.AddProductToWishlist();
		driver.navigate().to("http://localhost:5000/Wishlist");
		wishlistObject = new WishlistPage(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(wishlistObject.whishlistHeader));


		//Assert.assertTrue(wishlistObject.whishlistHeader.isDisplayed());
		//Assert.assertTrue(wishlistObject.productCell.getText().contains(productName));
		
	}
	@Test(priority=3)
	public void UserCanRemoveProductFromCart() {
		wishlistObject = new WishlistPage(driver);
		wishlistObject.RemoveProductFromWishlist();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.no-data")));


		//Assert.assertTrue(wishlistObject.EmptycartLb1.getText().contains("The wishlist is empty!"));
		
	}
}
