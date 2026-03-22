package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;

import pages.CheckoutPage;
import pages.HomePage;
import pages.OrderDetailsPage;
import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.ShoppingCartPage;
import pages.UserLoginPage;
import pages.UserRegistrationPage;

public class RegisteredUserCheckoutProduct extends TestBase
{
	HomePage homeObject;
	UserRegistrationPage registerObject;
	UserLoginPage loginObject;
	String productName = "Apple MacBook Pro";
	SearchPage searchObject;
	ProductDetailsPage detailsObject;
	ShoppingCartPage cartPage;
	CheckoutPage checkoutObject;
    OrderDetailsPage orderObject;
	@Test(priority=1,alwaysRun=true)
	public void UserCanRegisterSuccessfully() {
		homeObject = new HomePage(driver);
		homeObject.openRegistrationPage();
		registerObject = new UserRegistrationPage(driver);
		registerObject.userRegistration("lobna", "ali", "lobna" + System.currentTimeMillis() + "@test.com", "123456");
		Assert.assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));
	
	}
	@Test(priority=2)
	public void UserCanSearchWithAutosuggest() {
		searchObject = new SearchPage(driver);
        searchObject.ProductSearchUsingAutoSuggest("Apple Mac");
        detailsObject = new ProductDetailsPage(driver);
        Assert.assertEquals(detailsObject.productNamebreadCrumb.getText(), productName);
	}
	@Test(priority=3)
	public void UserCanAddProductToShoppingCart() throws InterruptedException{
		detailsObject = new ProductDetailsPage(driver);
		detailsObject.AddToCart();
		Thread.sleep(1000);
		driver.navigate().to("http://localhost:5000/cart");
		cartPage = new ShoppingCartPage(driver);
		Assert.assertTrue(cartPage.totalLbl.getText().contains("3,600.00"));
	}
	@Test(priority=4)
	public void UserCanCheckoutProduct() throws InterruptedException {
		checkoutObject = new CheckoutPage(driver);
		cartPage.openCheckoutPage();
		checkoutObject.RegisteredUserCheckoutProduct
		("lobna", "mohamed", "Egypt", "Cairo", "cairo", "monfia street","1234", "01021153436", productName);
		Assert.assertTrue(checkoutObject.productName.isDisplayed());
		Assert.assertTrue(checkoutObject.productName.getText().contains(productName));
		checkoutObject.confirmOrder();
		Assert.assertTrue(checkoutObject.ThankYoulbl.isDisplayed());
		checkoutObject.viewOrderDetailsPage();
		Assert.assertTrue(driver.getCurrentUrl().contains("orderdetails"));
		orderObject = new OrderDetailsPage(driver);
		orderObject.DownloadPDFInvoice();
		orderObject.PrintOrderDetails();
	}   
	    
		
		
	
	@Test(priority=5)
	public void RegisteredUserCanLogout() {


		// use homeObject which is initialized in first test; safer than using registerObject which may be null
		if (homeObject != null) {
			homeObject.userLogout();
		}
	}
}	
