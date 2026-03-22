package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.UserLoginPage;
import pages.UserRegistrationPage;
import pages.MyAccountPage;

public class MyAccountTest extends TestBase {

    HomePage homeObject;
    UserRegistrationPage registerObject;
    UserLoginPage loginObject;
    MyAccountPage myAccountObject;
    String firstName = "Lobna";
    String lastName = "Test";
    String email = "lobna" + System.currentTimeMillis() + "@test.com";
    String password = "123456";
    String oldPassword = "123456";
    String newPassword = "123457";
    @Test(priority=1)
    public void userCanRegisterAndLoginSuccessfully() {

        homeObject = new HomePage(driver);
        registerObject = new UserRegistrationPage(driver);
        homeObject.openRegistrationPage();
        registerObject.userRegistration(firstName, lastName, email, password);
    }
    
    @Test(priority=2)
    public void userCanChangePasswordSuccessfully() {

        homeObject = new HomePage(driver);
        myAccountObject = new MyAccountPage(driver);
        homeObject.openMyAccountPage();
        myAccountObject.openChangePasswordPage();
        myAccountObject.changePassword(oldPassword, newPassword);

        Assert.assertTrue(
                myAccountObject.getSuccessMessage().contains("Password was changed")
        );
        myAccountObject.closeSuccessNotification();
    }    
        @Test(priority=3)
     public void userCanLogout() {
         homeObject.userLogout();
        }
        @Test(priority=4)
     public void userCanLoginWithNewPassword() {
        loginObject = new UserLoginPage(driver);

        homeObject.openLoginPage();
        loginObject.userLogin(email, newPassword);

        Assert.assertTrue(homeObject.logoutDisplayed());
    }
}

