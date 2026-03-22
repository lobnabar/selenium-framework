package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends PageBase 
{

	public SearchPage(WebDriver driver) {
		super(driver);
		
	}
	@FindBy(id="small-searchterms")
	WebElement searchTextBox;

	@FindBy(className = "search-box-button")
	WebElement searchBtn; 

	
	@FindBy(id="ui-id-1")
	List<WebElement> productList;

	
	@FindBy(partialLinkText = "Apple MacBook")
	WebElement productTitle;

	// --- Methods ---

	public void ProductSearch(String productName) {
	    setTextElementText(searchTextBox, productName);
	    clickButton(searchBtn);
	}

	private void setTextElementText(WebElement textBox, String value) {
	    textBox.clear();
	    textBox.sendKeys(value);
	}

	public void OpenProductDetailPage() {
	    clickButton(productTitle);
	}

	public void ProductSearchUsingAutoSuggest(String searchTxt) {
	    setTextElementText(searchTextBox, searchTxt);
	    
	    productList.get(0).click(); 
	}
	}
