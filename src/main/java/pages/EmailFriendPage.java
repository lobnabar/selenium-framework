package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EmailFriendPage extends PageBase
{

	public EmailFriendPage(WebDriver driver) {
		super(driver);
	
	}
	
	@FindBy(id = "FriendEmail")
	private WebElement emailFriendTxt;
	
	@FindBy(id = "PersonalMessage")
	private WebElement personalMessageTxt;
	
	@FindBy(css = "button[name='send-email']")
	private WebElement sendEmailBtn;
	
	@FindBy(css = ".page-body > .result")
	public WebElement messageNotification;
	public void SendEmailToFriend(String friendEmail, String personalMessage) {
	    // Use sendKeys as defined in your PageBase
	    sendKeys(emailFriendTxt, friendEmail);
	    sendKeys(personalMessageTxt, personalMessage);
	    clickButton(sendEmailBtn);
	}
	
	
	
	
	
	
	
	

}
