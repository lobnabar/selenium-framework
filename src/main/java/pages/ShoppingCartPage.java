package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShoppingCartPage extends PageBase
{

	public ShoppingCartPage(WebDriver driver) {
		super(driver);
		
	}
	@FindBy(css = "td.remove-from-cart input")
	WebElement removeCheckBtn;
	
	@FindBy(name = "updatecart")
	 WebElement updateCartBtn;
	
	@FindBy(css = "input.qty-input valid")
	public WebElement quantityTxt;
	
	@FindBy(css = "td.subtotal")
	public WebElement totalLbl;
	@FindBy(id = "checkout")
	public WebElement checkoutBtn;
	
	@FindBy(id = "termsofservice")
	public WebElement agreeCheckbox;
	
	
	//public void RemoveProductFromCart() {
		//clickButton(removeCheckBtn);
		//clickButton(updateCartBtn);
	//}
	public void RemoveProductFromCart() {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    wait.until(ExpectedConditions.presenceOfElementLocated(
	        By.cssSelector("input[name='removefromcart']")
	    ));

	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].click();", removeCheckBtn);

	    wait.until(ExpectedConditions.elementToBeClickable(updateCartBtn));
	    updateCartBtn.click();
	}
	public void UpdateProductQuantityInCart(String quantity) throws InterruptedException {
	  
		clearText(quantityTxt);
		sendKeys(quantityTxt, quantity);
	    clickButton(updateCartBtn);
	}
	
    public void openCheckoutPage() { 
        clickButton(agreeCheckbox);
    	clickButton(checkoutBtn);
    }
}	   
						
	
	
	
	
	
	
	
	
	
	
	
	

