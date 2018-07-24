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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ecommerce {

	public WebDriver driver;
	
	public int scc =0;
	
	public int pgld_time = 30;
	
	public int sleep_time = 5000;

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

		Invokbrowser("edge");

	}

	@Test
	public void Test_001() throws Throwable {

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

		driver.manage().timeouts().pageLoadTimeout(pgld_time, TimeUnit.SECONDS);

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
		
		driver.manage().timeouts().pageLoadTimeout(pgld_time, TimeUnit.SECONDS);
		
		Thread.sleep(sleep_time);
		
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
		
		//Taking Screenshot 
		
		/*scc=(scc+1);
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		String png = ("D:\\selinum training\\Guru99-Live"+ scc + ".png");
		
		FileUtils.copyFile(scrFile, new File(png));		
		*/

	}
	
	
	@Test
	
	public void Test_002() throws Throwable {
		/* Test Case Descripition :
		 * Step 1: Go to http://live.guru99.com/index.php/ 
		 * Step 2: click on Mobile
		 * Step 3: Get the cost of sony  mobile
		 * Step 4: Click on Sony mobile
		 * Step 5: read the sony mobile cost
		 * Step 6: Compare the value in Step 3 &5
		 * 
		 */
		
		//Step 1:
		
		driver.manage().timeouts().pageLoadTimeout(pgld_time, TimeUnit.SECONDS);

		driver.get("http://live.guru99.com/index.php/");

		// Step 2
		
		WebElement mobile = driver.findElement(By.xpath("//a[contains(text(),'Mobile')]"));
		
		mobile.click();
		
		driver.manage().timeouts().pageLoadTimeout(pgld_time, TimeUnit.SECONDS);
		Thread.sleep(sleep_time);
		//Step 3 :
		
		WebElement Sony1 =driver.findElement(By.xpath("//li[@class='item last']//div[@class='price-box']/Span[@id='product-price-1']/span"));
		
		String Price1 = Sony1.getText();
		
		System.out.println(Price1);
		
		//Step 4:
		
		driver.findElement(By.xpath("//li[@class='item last']//a[@title='Sony Xperia']")).click();
		driver.manage().timeouts().pageLoadTimeout(pgld_time, TimeUnit.SECONDS);
		Thread.sleep(sleep_time);
		//Step 5:
		
		WebElement Sony2 = driver.findElement(By.xpath("//span[@id ='product-price-1']/span"));
		
		String Price2 = Sony2.getText();
		
		System.out.println(Price2);
		//Step 6:
		Assert.assertEquals(Price1, Price2, "test case 2 filed");
		
	}

	@AfterTest
	
	public void Closebrowser() {
		
		driver.quit();
		
	}
	
}
