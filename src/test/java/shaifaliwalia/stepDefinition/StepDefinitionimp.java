package shaifaliwalia.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import shaifaliwalia.TestComponents.BaseTest;
import shaifaliwalia.pageobjects.CartPage;
import shaifaliwalia.pageobjects.ConfirmationPage;
import shaifaliwalia.pageobjects.LoginPage;
import shaifaliwalia.pageobjects.OrderPlacingPage;
import shaifaliwalia.pageobjects.ShoppingItemPage;

public class StepDefinitionimp extends BaseTest {
	public LoginPage loginpage;
	public ShoppingItemPage shoppingitempage;
	public CartPage cartpage;
	public ConfirmationPage confirmationpage;
	public OrderPlacingPage orderplacingpage;
	String text;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		loginpage=launchapplication();
	}
	
	@Given("^Logged in application with email (.+) and password (.+)$")
	public void logged_in_email_and_password(String email,String password)
	{
		shoppingitempage = loginpage.loginapplication(email,password);
	}
	
	@When("^Add item to cart with productname (.+)$")
	public void Addd_item_to_cart(String productname) {
		List<WebElement> products = shoppingitempage.getproductlist();
		shoppingitempage.getProductByName(productname);
		cartpage = shoppingitempage.AddItemToCart(productname);

	}
	@And("^checkout (.+) and submit the order$")
	public void check_submit_order(String productname) {
		Boolean match=cartpage.verifyproductdisplay(productname);
		Assert.assertTrue(match);
		orderplacingpage = cartpage.gotocheckoutpage();
		confirmationpage = orderplacingpage.selectcountry("India");
		
	}
	
	@Then("{string} verify the message on ConfirmatiomPage")
	public void Verify_displayed_message(String string) {
		text = confirmationpage.getconfirmationmessage();  
		Assert.assertEquals(string, text);
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void something_message_is_displayed(String string) {
		Assert.assertEquals(string,loginpage.geterrormsg());
		driver.close();
	} 
}