package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyAccountPage extends PageBase {

    public MyAccountPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "a[href='/customer/changepassword']")
    WebElement ChangePasswordLink;

    @FindBy(id = "OldPassword")
    WebElement oldPassword;

    @FindBy(id = "NewPassword")
    WebElement newPassword;

    @FindBy(id = "ConfirmNewPassword")
    WebElement confirmPassword;

    @FindBy(css = "button.change-password-button")
    WebElement ChangepasswordBtn;

    @FindBy(css = "div.bar-notification.success p.content")
    WebElement successMessage;
    @FindBy(css="span.close")
    WebElement closeNotification;
    public void openChangePasswordPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(ChangePasswordLink));
        ChangePasswordLink.click();
    }
    

    public void changePassword(String oldPass, String newPass) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(oldPassword));

        oldPassword.sendKeys(oldPass);
        newPassword.sendKeys(newPass);
        confirmPassword.sendKeys(newPass);
        ChangepasswordBtn.click();
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }
    public void closeSuccessNotification() {
        closeNotification.click();
    }
}
