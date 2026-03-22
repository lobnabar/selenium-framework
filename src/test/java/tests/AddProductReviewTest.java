package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.EmailFriendPage;
import pages.HomePage;
import pages.ProductDetailsPage;
import pages.ProductReviewPage;
import pages.SearchPage;
import pages.UserLoginPage;
import pages.UserRegistrationPage;

public class AddProductReviewTest extends TestBase
{
	//user registration
	HomePage homeObject;
    UserRegistrationPage registerObject;

    String firstName = "Lobna";
    String lastName = "Test";
    String email = "lobna" + System.currentTimeMillis() + "@test.com";
    String password = "123456";
    String productName = "Apple MacBook Pro"; 
	SearchPage searchObject;
    ProductDetailsPage detailsObject;
    UserLoginPage loginObject;
    ProductReviewPage reviewObject;
    
    @Test(priority=1)
    public void userCanRegisterAndLoginSuccessfully() {

        homeObject = new HomePage(driver);
        registerObject = new UserRegistrationPage(driver);

        homeObject.openRegistrationPage();

        registerObject.userRegistration(firstName, lastName, email, password);

        Assert.assertTrue(registerObject.getSuccessMessage().contains("Your registration completed")
        );
        }
    
    //search for product
    @Test(priority=2)
    public void UserCanSearchAutoSuggest()
    {
        searchObject = new SearchPage(driver);
        searchObject.ProductSearchUsingAutoSuggest("MacB");
        detailsObject = new ProductDetailsPage(driver);
        Assert.assertEquals(detailsObject.productNamebreadCrumb.getText(), productName);
}
    
    //add review
    
    @Test(priority=3)
    public void RegisteredUserCanReviewProduct() 
    {
    	detailsObject.openAddReviewPage();
    	reviewObject = new ProductReviewPage(driver);
    	reviewObject.AddProductReview("new review", "the product is very good");
    	Assert.assertTrue(reviewObject.reviewNotification.getText()
    			.contains("Product review is successfully added."));
    }
    
    @Test(priority=4) 
   
    public void RegisteredUserCanLogout() {
    	reviewObject.closeSuccessMessage(); 
        homeObject.userLogout();
    }
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

