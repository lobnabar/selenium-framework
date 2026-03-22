package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WishlistPage extends PageBase
{

	public WishlistPage(WebDriver driver) {
		super(driver);
		
	}
	@FindBy(css = "product-name")
    public WebElement productCell;


    @FindBy(css = "h1")
    public WebElement whishlistHeader;
    
    @FindBy(name = "updatecart")
    private WebElement updateWishlistBtn;
    
    @FindBy(className="remove-btn")
    private WebElement removeBtn;
    
    @FindBy(css = "div.no-data")
    public WebElement EmptycartLb1;
    
    public void RemoveProductFromWishlist() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(removeBtn));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", removeBtn);

        
    }   
}    
    
	
	
	
	
	
	
	
	
	
	
	
	
	
	

