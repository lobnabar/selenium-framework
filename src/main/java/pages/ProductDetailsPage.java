package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetailsPage extends PageBase
{

	public ProductDetailsPage(WebDriver driver) {
		super(driver);
		
	}
	@FindBy(css="strong.current-item")
	public WebElement productNamebreadCrumb;
	
	@FindBy(css = "button.email-a-friend-button")
	WebElement emailFriendBtn;
	
	@FindBy(css = "span.price-value-4")
	public WebElement prooductPricelbl;
	
	@FindBy(linkText = "Add your review")
    WebElement addReviewLink;
	@FindBy(id = "add-to-wishlist-button-4")
	WebElement addToWishlistBtn;
	@FindBy(css = "div.bar-notification.success")
	private WebElement successNotification;

	@FindBy(css = "div.bar-notification.success a")
	private WebElement wishlistLinkInPopup;
	@FindBy(id="add-to-cart-button-4")
	WebElement addToCartBtn;
	
	public void openSendEmail() 
	{
		clickButton(emailFriendBtn);
	}
	public void openAddReviewPage() 
	{
		clickButton(addReviewLink);
	}
    public void AddProductToWishlist()
    {
        clickButton(addToWishlistBtn);	
    }
    public void openWishlistFromNotification() {

        
        wishlistLinkInPopup.click();
    }
    public void AddToCart()
    {
    	clickButton(addToCartBtn);
    }
}
