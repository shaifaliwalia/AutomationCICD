package shaifaliwalia.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import shaifaliwalia.pageobjects.LoginPage;

public class BaseTest {
	public WebDriver driver;
	
	public WebDriver Invokebrowser() throws IOException {
		
		Properties prop =new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\shaifaliwalia\\Resources\\GlobalData.properties");
		prop.load(fis);
		String browsername =System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");

		
		if(browsername.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			if(browsername.contains("headless")) {
			options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900));
 		}
		
		else if(browsername.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		else if(browsername.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
			
		return driver;
	}
	public List<HashMap<String, String>> getJsonfiledata(String filepath) throws IOException {
		String jsoncontent = FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String,String>>>() {
		});
		
		return data;
	}
	
	public String getscreenshot(String testcasename,WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File sourfile=ts.getScreenshotAs(OutputType.FILE);
		File destfile = new File(System.getProperty("user.dir")+"//reports//"+testcasename+".png");
		FileUtils.copyFile(sourfile, destfile);
		return System.getProperty("user.dir")+"//reports//"+testcasename+".png";
		
	}
	
	@BeforeMethod(alwaysRun=true)
	public LoginPage launchapplication() throws IOException {
		driver = Invokebrowser();
		driver.get("https://rahulshettyacademy.com/client");
		LoginPage loginpage = new LoginPage(driver);
		return loginpage;
		
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}
}
