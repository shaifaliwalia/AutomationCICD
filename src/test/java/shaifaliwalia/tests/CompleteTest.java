package shaifaliwalia.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import shaifaliwalia.TestComponents.BaseTest;
import shaifaliwalia.pageobjects.CartPage;
import shaifaliwalia.pageobjects.ConfirmationPage;
import shaifaliwalia.pageobjects.LoginPage;
import shaifaliwalia.pageobjects.OrderPlacingPage;
import shaifaliwalia.pageobjects.ShoppingItemPage;

public class CompleteTest extends BaseTest{
	@Test(dataProvider = "getdata",groups= {"purchaseorder"})
	public void placeorder(HashMap<String,String> input) throws IOException,InterruptedException
	{
		
//		WebDriver driver = new ChromeDriver();
//		driver.get("https://rahulshettyacademy.com/client");
		//String productname = "ZARA COAT 3"; 
		
		
		LoginPage loginpage = new LoginPage(driver);
		ShoppingItemPage shoppingitempage = loginpage.loginapplication(input.get("email"),input.get("password"));
		//List<WebElement> products = shoppingitempage.getproductlist();
		shoppingitempage.getProductByName(input.get("productname"));
		CartPage cartpage = shoppingitempage.AddItemToCart(input.get("productname"));
		Boolean match=cartpage.verifyproductdisplay(input.get("productname"));
		Assert.assertTrue(match);
		OrderPlacingPage orderplacingpage = cartpage.gotocheckoutpage();
		ConfirmationPage confirmationpage = orderplacingpage.selectcountry("India");
		String text = confirmationpage.getconfirmationmessage();
		AssertJUnit.assertEquals("THANKYOU FOR THE ORDER.", text);
		
	} 
	
	@DataProvider
	public Object[][] getdata() throws IOException {
		
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "walia.shaifali8@gmail.com");
//		map.put("password", "Shaifali@12");
//		map.put("productname", "ZARA COAT 3");
//		
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map.put("email", "shetty@gmail.com");
//		map.put("password", "Iamking@000");
//		map.put("productname", "ADIDAS ORIGINAL");
		List<HashMap<String,String>> data =getJsonfiledata(System.getProperty("user.dir"+"\\src\\test\\java\\shaifaliwalia\\data\\purchaseorder.json"));
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
//		return new Object[][] {{"walia.shaifali8@gmail.com", "Shaifali@12","ZARA COAT 3"},{"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
	}
	
}
