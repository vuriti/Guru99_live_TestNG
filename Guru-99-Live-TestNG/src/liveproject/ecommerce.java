package liveproject;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ecommerce {

	public WebDriver driver;

	public void Invokbrowser(String browserType) {

		System.out.println(browserType + "Browser is opening to Run the Tests ");

		if (browserType.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver", "C:\\Selenium Drivers\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserType.equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.gecko.driver",
					"C:\\Selenium Drivers\\geckodriver-v0.21.0-win64\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browserType.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", "C:\\Selenium Drivers\\MicrosoftWebDriver.exe");
			driver = new EdgeDriver();
		} else {
			System.err.println("invalid browser option");
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		System.out.println("***** Befor Method is completed ******");

	}

	@BeforeTest

	public void openbrowser() {

		Invokbrowser("chrome");

	}

	@Test
	public void Test_001() {

		/*
		 * Test Case Description : 
		 * Step 1 : Go to Page :
		 * http://live.guru99.com/index.php/ 
		 * Step 2 : verify the title of page || Expected : "THIS IS DEMO SITE" shown in the home page 
		 * Step 3 : Click on mobile 
		 * Step 4 : Verify the title of the page || Expected : title "MOBILE"should be displayed 
		 * Step 5 : in the list of all mobile select Sort by and select Name in that
		 * Step 6 : Verify all products sorted by name || Expected : products should be sorted by name
		 */

		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

		// Step 1:
		driver.get("http://live.guru99.com/index.php/");

		// Step 2:
		String Titel1_actual = driver.getTitle();
		System.out.println("Page Titel is : " + Titel1_actual);
		String Titel1_Expcted = "Home page";
		Assert.assertEquals(Titel1_actual, Titel1_Expcted, " Step 2 failed");

		// Step 3
		
		WebElement mobile = driver.findElement(By.xpath("//a[contains(text(),'Mobile')]"));
		
		mobile.click();
		
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		
		//Step 4:
		
		String Titel2_actul = driver.getTitle();
		
		System.out.println("Current Page is : " + Titel2_actul);
		
		String Titel2_expcted ="Mobile";
				
		Assert.assertEquals(Titel2_actul, Titel2_expcted);
		
		//List all products on the page
		
		List<WebElement> Phone = driver.findElements(By.xpath("//ul[@class='products-grid products-grid--max-4-col first last odd']//li[@class='item last']/a"));
		
		System.out.println("Before Sort Phone list is as below :\n");
		for (WebElement list :Phone) {
				
			System.out.println(list.getAttribute("title"));
		}
		
		//Step 5:
		// handlining drop down
		
		WebElement Option = driver.findElement(By.xpath("//div[@class='toolbar-bottom']//select[@title ='Sort By']"));

		Select select = new Select(Option) ;
		
		select.selectByVisibleText("Name");
		

		List<WebElement> Phone1 = driver.findElements(By.xpath("//ul[@class='products-grid products-grid--max-4-col first last odd']//li[@class='item last']/a"));
		
		System.out.println("After Sort Phone list is as below :\n");
		
		for (WebElement list1 :Phone1) {
				
			System.out.println(list1.getAttribute("title"));
	}

	}
	
}
