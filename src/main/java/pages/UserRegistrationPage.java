package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserRegistrationPage extends PageBase {

    public UserRegistrationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "FirstName")
    WebElement firstName;

    @FindBy(id = "LastName")
    WebElement lastName;

    @FindBy(id = "Email")
    WebElement email;

    @FindBy(id = "Password")
    WebElement password;

    @FindBy(id = "ConfirmPassword")
    WebElement confirmPassword;

    @FindBy(id = "register-button")
    WebElement registerBtn;

    @FindBy(css = "div.result")
	public WebElement successMessage;
    
    @FindBy(xpath = "//a[text()='Log out']")
    WebElement logoutBtn;

    public void userRegistration(String fName, String lName, String mail, String pass) {
        sendKeys(firstName, fName);
        sendKeys(lastName, lName);
        sendKeys(email, mail);
        sendKeys(password, pass);
        sendKeys(confirmPassword, pass);
        clickButton(registerBtn);
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }

	public void userLogout() {
		clickButton(logoutBtn);
		
	}
}
