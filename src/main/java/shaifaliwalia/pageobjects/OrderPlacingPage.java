package shaifaliwalia.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import shaifaliwalia.AbstractComponents.AbstractComponent;

public class OrderPlacingPage extends AbstractComponent {
	WebDriver driver;
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	private WebElement countrybox ;
	
	
	@FindBy(css="span[class='ng-star-inserted']")
	private List<WebElement> countrylist ;
	
	@FindBy(className="action__submit")
	private WebElement submitbtn ;
	
	
	public OrderPlacingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public ConfirmationPage selectcountry(String countryname) {
		countrybox.sendKeys("ind");
	    //WaitForElementToAppear(countrylist);
		WebElement country = countrylist.stream().filter(count->count.getText().equalsIgnoreCase(countryname)).findFirst().orElse(null);
		country.click();
		submitbtn.click();
		return new ConfirmationPage(driver);
	}
}
