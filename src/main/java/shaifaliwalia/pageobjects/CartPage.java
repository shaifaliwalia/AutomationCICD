package shaifaliwalia.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import shaifaliwalia.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	private By carttext = By.xpath("//h1[text()='My Cart']");
	
	@FindBy(css=".infoWrap h3")
	private List<WebElement> cartproducts;
	
	@FindBy(xpath="//button[text()='Checkout']")
	private WebElement checkoutbtn;
	
	public Boolean verifyproductdisplay(String Productname) {
	WaitForElementToAppear(carttext);
	Boolean match = cartproducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(Productname));
	return match;
	
	}
	
	public OrderPlacingPage gotocheckoutpage() {
		checkoutbtn.click();
		return new OrderPlacingPage(driver);
	}
}
