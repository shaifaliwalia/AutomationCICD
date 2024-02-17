package shaifaliwalia.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import shaifaliwalia.AbstractComponents.AbstractComponent;

public class ShoppingItemPage extends AbstractComponent {
		WebDriver driver;
		public ShoppingItemPage(WebDriver driver) {
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
		
		@FindBy(css=".mb-3")
		private List<WebElement> products;
		
		@FindBy(css="[routerlink*='cart']")
		private WebElement gotocart;
		
		@FindBy(className="ng-animating")
		private WebElement proccessing;
		
		@FindBy(css="[routerlink*='myorders']")
		private WebElement myorderbutton;
		
		
		By productsby = By.cssSelector(".mb-3");
		By addToCart = By.cssSelector(".card-body button:last-of-type");
		By addedtoast = By.id("toast-container");
		
		public List<WebElement> getproductlist() {
			WaitForElementToAppear(productsby);
			return products;
		}
		
		
		public WebElement getProductByName(String Productname) {
			WebElement productnames = getproductlist().stream().filter(product->product.findElement(By.tagName("b")).getText().
					equals(Productname)).findFirst().orElse(null);
			return productnames;
		}
		
		public CartPage AddItemToCart(String Productname) {
			
			WebElement productnames = getProductByName(Productname);
			productnames.findElement(addToCart).click();
			WaitForElementToAppear(addedtoast);
			WaitForElementToDisAppear(proccessing);
			gotocart.click();
			return new CartPage(driver);
		} 
		
		public OrdersPage gotomyorders() {
			myorderbutton.click();
			return new OrdersPage(driver);
		}
		
		
		
}
