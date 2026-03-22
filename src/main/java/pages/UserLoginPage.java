package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserLoginPage extends PageBase {

    public UserLoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "Email")
    WebElement emailTxt;

    @FindBy(id = "Password")
    WebElement passwordTxt;

    @FindBy(css = "button.login-button")
    WebElement loginBtn;

    public void userLogin(String email, String password) {
        sendKeys(emailTxt, email);
        sendKeys(passwordTxt, password);
        clickButton(loginBtn);
    }
}

