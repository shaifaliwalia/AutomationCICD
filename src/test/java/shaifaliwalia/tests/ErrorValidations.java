package shaifaliwalia.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import shaifaliwalia.TestComponents.BaseTest;
import shaifaliwalia.pageobjects.CartPage;
import shaifaliwalia.pageobjects.ConfirmationPage;
import shaifaliwalia.pageobjects.LoginPage;
import shaifaliwalia.pageobjects.OrderPlacingPage;
import shaifaliwalia.pageobjects.OrdersPage;
import shaifaliwalia.pageobjects.ShoppingItemPage;

public class ErrorValidations extends BaseTest{
	String productname = "ZARA COAT 3"; 
	@Test(groups= {"ErrorHandling"},retryAnalyzer = shaifaliwalia.TestComponents.Retry.class)
	public void loginnegative() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.loginapplication("walia1.shaifali8@gmail.com", "Shaifali@123");
		Assert.assertEquals("Incorrect email or password.",loginpage.geterrormsg());
	}	
	
	
	@Test
	public void placeorder1() throws IOException,InterruptedException
	{	
		
		LoginPage loginpage = new LoginPage(driver);
		ShoppingItemPage shoppingitempage = loginpage.loginapplication("walia.shaifali8@gmail.com", "Shaifali@12");
		//List<WebElement> products = shoppingitempage.getproductlist();
		shoppingitempage.getProductByName(productname);
		CartPage cartpage = shoppingitempage.AddItemToCart(productname);
		Boolean match=cartpage.verifyproductdisplay(productname);
		Assert.assertTrue(match);
		OrderPlacingPage orderplacingpage = cartpage.gotocheckoutpage();
		ConfirmationPage confirmationpage = orderplacingpage.selectcountry("India");
		String text = confirmationpage.getconfirmationmessage();
		Assert.assertEquals("THANKYOU FOR THE ORDER.", text);	
	}
	
	@Test(dependsOnMethods = {"placeorder1"})
	public void checkorderlist() {
		LoginPage loginpage = new LoginPage(driver);
		ShoppingItemPage shoppingitempage=loginpage.loginapplication("walia.shaifali8@gmail.com", "Shaifali@12");
		OrdersPage orderpage = shoppingitempage.gotomyorders();
		Assert.assertTrue(orderpage.orderpagedisplay(productname));
	}
	
}