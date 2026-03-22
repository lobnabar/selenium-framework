package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends PageBase
{

	public CheckoutPage(WebDriver driver) {
		super(driver);
		
	}
	@FindBy(id = "BillingNewAddress_FirstName")
	WebElement fnTxt;
	
	@FindBy(id = "BillingNewAddress_LastName")
	WebElement lnTxt;
	
	@FindBy(id = "BillingNewAddress_Email")
	WebElement emailTxt;
	
	@FindBy(id = "BillingNewAddress_CountryId")
	WebElement countryList;
	
	@FindBy(id = "BillingNewAddress_PhoneNumber")
	WebElement phoneTxt;
	
	@FindBy(name = "BillingNewAddress.City")
	WebElement cityTxt;
	
	@FindBy(id="BillingNewAddress_StateProvinceId")
	WebElement stateList;
	
	@FindBy(id = "BillingNewAddress_Address1")
	WebElement addressTxt;
	
	@FindBy(id = "BillingNewAddress_ZipPostalCode")
	WebElement postalTxt;
	
	@FindBy(css = "button.new-address-next-step-button")
	WebElement continueBtn;
	
	@FindBy(id = "shippingoption_1")
	WebElement shippingMethodRdo;
	
	@FindBy(css = "button.shipping-method-next-step-button")
	WebElement continueShippingBtn;
	
	@FindBy(css = "button[onclick='PaymentMethod.save()']")
	WebElement continuePaymentBtn;
	
	@FindBy(css = "button.payment-info-next-step-button")
	WebElement continueInfoBtn;
	
	@FindBy(linkText="Apple MacBook Pro")
	public WebElement productName;
	
	@FindBy(css="button.confirm-order-next-step-button")
	WebElement confirmBtn;
	
	@FindBy(css="h1")
	public WebElement ThankYoulbl;
	
	@FindBy(css="div.title")
	public WebElement successMessage;
	
	@FindBy(linkText ="Click here for order details.")
	private WebElement orderDetailsLink;
	
	
	
	public void RegisteredUserCheckoutProduct(String fN, String lN, 
			 String countryName, String state,String city, String address, String postcode, String phone, String productName) throws InterruptedException {
		
		select = new Select(countryList);
		select.selectByVisibleText(countryName);
		select  = new Select(stateList);
	    select.selectByVisibleText(state);
		sendKeys(cityTxt, city);
		sendKeys(addressTxt, address);
		sendKeys(postalTxt, postcode);
		sendKeys(phoneTxt, phone);
		clickButton(continueBtn);
		clickButton(shippingMethodRdo);
		clickButton(continueShippingBtn);
		Thread.sleep(1000);
		clickButton(continuePaymentBtn);
		Thread.sleep(1000);
		clickButton(continueInfoBtn);
	}
	public void confirmOrder() throws InterruptedException {
		Thread.sleep(1000);
		clickButton(confirmBtn);
	}
	public void viewOrderDetailsPage() {
	    clickButton(orderDetailsLink); // Clicks the "Click here for order details." link
	}
}	
	
	//Assert.assertTrue(productName.isDisplayed());
		
		//Assert.assertTrue(ThankYoulbl.isDisplayed());
		//Thread.sleep(1000);
		//Assert.assertTrue(successMessage.getText().contains("Your order has been successfully processed!"));
		
		
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


