package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.EmailFriendPage;
import pages.HomePage;
import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.UserRegistrationPage;

public class EmailFriendTest extends TestBase
{
	//user registeration
	HomePage homeObject;
    UserRegistrationPage registerObject;

    String firstName = "Lobna";
    String lastName = "Test";
    String email = "lobna" + System.currentTimeMillis() + "@test.com";
    String password = "123456";
    String productName = "Apple MacBook Pro"; 
	SearchPage searchObject;
    ProductDetailsPage detailsObject;
    EmailFriendPage emailObject;
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
    
    //email to friend
    
    @Test(priority=3)
    public void RegisteredUserCanSendProductToFriend() 
    {
    	detailsObject.openSendEmail();
    	emailObject = new EmailFriendPage(driver);
    	emailObject.SendEmailToFriend("aaa@test.com", "hello");
    	Assert.assertTrue(emailObject.messageNotification.getText().contains("Your message has been sent."));
    }
    
    @Test(priority=4) 
    public void RegisteredUserCanLogout() {
        homeObject.userLogout();
    }
}

