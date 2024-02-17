package shaifaliwalia.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import shaifaliwalia.AbstractComponents.AbstractComponent;

public class LoginPage extends AbstractComponent{
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		
		PageFactory.initElements(driver, this);
	}
	
	 @FindBy(id="userEmail")
	 private WebElement Email;
	 
	 @FindBy(id="userPassword")
	 private WebElement Password;
	 
	 @FindBy(id="login")
	 private WebElement Login;
	 
	 @FindBy(css="[class*='flyInOut']")
	 private WebElement Errormsg;
	 
	 
	public ShoppingItemPage loginapplication(String email,String password) {
		Email.sendKeys(email);
		Password.sendKeys(password);
		Login.click();
		return new ShoppingItemPage(driver);
		
	}
	
	 public String geterrormsg() {
		 WaitForWebElementToAppear(Errormsg);
		 return Errormsg.getText();
	 }
}
