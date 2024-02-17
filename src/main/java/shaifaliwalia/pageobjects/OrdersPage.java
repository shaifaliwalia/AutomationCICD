package shaifaliwalia.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import shaifaliwalia.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent {
	WebDriver driver;
	
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//h1[text()='Your Orders']")
	private WebElement yourorderverify;
	
	@FindBy(xpath="//tr[@class=\"ng-star-inserted\"]/td[2]")
	private List<WebElement> orderlist;
	
	public boolean orderpagedisplay(String productname) {
		WaitForWebElementToAppear(yourorderverify);
		boolean listorder=orderlist.stream().anyMatch(product->product.getText().equalsIgnoreCase(productname));
		return listorder;
		
	}

}
