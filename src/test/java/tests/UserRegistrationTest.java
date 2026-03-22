package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.UserRegistrationPage;
import pages.UserLoginPage;

public class UserRegistrationTest extends TestBase {

    HomePage homeObject;
    UserRegistrationPage registerObject;
    UserLoginPage loginObject;

    String firstName = "Lobna";
    String lastName = "Test";
    String email = "lobna" + System.currentTimeMillis() + "@test.com";
    String password = "123456";

    @Test
    public void userCanRegisterAndLoginSuccessfully() {

        homeObject = new HomePage(driver);
        registerObject = new UserRegistrationPage(driver);
        loginObject = new UserLoginPage(driver);

        // Step 1: Open Registration Page
        homeObject.openRegistrationPage();

        // Step 2: Register
        registerObject.userRegistration(firstName, lastName, email, password);

        Assert.assertTrue(
                registerObject.getSuccessMessage()
                        .contains("Your registration completed")
        );

        // Step 3: Logout after registration
        homeObject.userLogout();

        // Step 4: Open Login Page
        homeObject.openLoginPage();

        // Step 5: Login
        loginObject.userLogin(email, password);

        // Step 6: Verify Login
        Assert.assertTrue(homeObject.logoutDisplayed());
    }

	public static void main(String[] args) {
	
		
	}
}
