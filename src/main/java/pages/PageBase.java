package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PageBase {

    protected WebDriver driver;
    public JavascriptExecutor jse;
    public PageBase(WebDriver driver) {
    	this.driver = driver;
    	PageFactory.initElements(driver, this);
    }
    public Select select;
    public Actions action;
    protected  void clickButton(WebElement element) {
        element.click(); 
    }
       

    protected void sendKeys(WebElement element, String city) {
        element.clear();
        element.sendKeys(city);
    }

    protected String getText(WebElement element){ 
    
        return element.getText();
    }
    public void scrollToBottom() {
    
    jse.executeScript("scrollBy(0,2500)");
    }
    public void clearText (WebElement element) {
    
        element.clear();
    }
}
