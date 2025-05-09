package Test;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Shopclues {
	WebDriver driver;
	@BeforeTest
	@Parameters("browser")
	public void browser(String browser) {
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		driver.get("https://www.shopclues.com/?mcid=ps&utm_source=google&utm_medium=cpc&gad_source=1&gclid=Cj0KCQjw782_BhDjARIsABTv_JDY6fq6au3baw0GNEoiUnhjmssKqJzaMqnktCnID4zWNNzg6CXAS0YaAh7zEALw_wcB");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
	}
		/*@BeforeMethod
		public void url() {
			
		}*/
		@Test(priority = 1)
		public void register() {
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement register=driver.findElement(By.xpath("//a[@id='reg_tab']"));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@id='reg_tab']")));
			register.click();
			driver.findElement(By.xpath("//input[@name='email']")).sendKeys("sufiyanku@gmail.com");
			driver.findElement(By.xpath("//input[@name='phone']")).sendKeys("456789912");
		    driver.findElement(By.xpath("//a[text()='Register']")).click();
		}
		@Test(priority = 0)
		public void search() {
			driver.findElement(By.xpath("//input[@id='autocomplete']")).sendKeys("Mobiles");
			driver.findElement(By.xpath("//a[text()='Search']")).click();
			Actions act=new Actions(driver);
			WebElement product=driver.findElement(By.xpath("//img[@width='200']"));
			act.moveToElement(product);
			product.click();
			act.build().perform();
			String parent=driver.getWindowHandle();
			Set<String> windowid=driver.getWindowHandles();
			for(String window:windowid) {
				if(!parent.equalsIgnoreCase(window)) {
					driver.switchTo().window(window);
					driver.findElement(By.xpath("//button[text()='Add To Cart']")).click();
					WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
					WebElement cart=driver.findElement(By.xpath("//a[@class='cart_ic']"));
					wait.until(ExpectedConditions.elementToBeClickable(cart));
				    cart.click();
				    driver.findElement(By.xpath("//div[text()='Place Order']")).click();
					
				}
			}
				
		}
		@AfterTest
		public void browserclose() {
			driver.quit();
		}
	

}
