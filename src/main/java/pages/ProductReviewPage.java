package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductReviewPage extends PageBase
{

	public ProductReviewPage(WebDriver driver) {
		super(driver);
	}	
	@FindBy(id = "AddProductReview_Title")
	WebElement reviewTitleTxt;
	
	@FindBy(id = "AddProductReview_ReviewText")
	WebElement reviewText;
	
	@FindBy(css = "label[for='AddProductReview_Rating']")
	WebElement rating4RdoBtn;
	@FindBy(id = "add-review") 
	WebElement submitReviewBtn;
	
	@FindBy(css = "div.bar-notification.success p.content")
	public WebElement reviewNotification;
	@FindBy(css = "span.close")
	WebElement closeNotificationBtn;

	public void closeSuccessMessage() {
	    clickButton(closeNotificationBtn);
	}
	public void AddProductReview(String reviewTitle, String reviewMessage) {
		sendKeys(reviewTitleTxt, reviewTitle);
		sendKeys(reviewText, reviewMessage);
		clickButton(rating4RdoBtn);
		clickButton(submitReviewBtn);
		
	}	
		

		
	}


