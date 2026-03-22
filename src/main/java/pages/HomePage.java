package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class HomePage extends PageBase {

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        jse = (JavascriptExecutor) driver;
        action = new Actions(driver);
    }

    @FindBy(linkText = "Register")
    WebElement registerLink;

    @FindBy(linkText = "Log in")
    WebElement loginLink;

    @FindBy(css = "a.ico-account")
    WebElement myAccountLink;

    @FindBy(xpath = "//a[text()='Log out']")
    WebElement logoutLink;
    
    @FindBy(linkText = "Contact us")
    WebElement contactUsLink;
    
    @FindBy(id = "customerCurrency")
    WebElement currencydrl;
    
    @FindBy(css = "a[href='/computers']")
    WebElement ComputerMenu;
  
    @FindBy(css = "a[href='/notebooks']")
    WebElement NotebooksMenu;
    
    

    public void openRegistrationPage() {
        clickButton(registerLink);
    }

    public void openLoginPage() {
        clickButton(loginLink);
    }


    public void openMyAccountPage() {
        myAccountLink.click();
    }


    public void userLogout() {
        clickButton(logoutLink);
    }

    public boolean logoutDisplayed() 
    {
        return logoutLink.isDisplayed();
    }
    public void openContactUspage()
    {
    	scrollToBottom();
    	clickButton(contactUsLink);
    }
    public void changeCurrency() 
    {
        select = new Select(currencydrl);
        select.selectByVisibleText("US Dollar");
    }
    public void selectNotebooksMenu() 
    {
    	action.moveToElement(ComputerMenu)
        .click(NotebooksMenu)
        .build()
        .perform();
    }
    
}   
