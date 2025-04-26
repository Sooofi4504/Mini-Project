package Test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test{
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
		driver.get("https://www.automationexercise.com/");
	}
	@BeforeMethod
	public void url() {
	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
	}
	@org.testng.annotations.Test(priority = 0)
	public void Signin() throws InterruptedException {
		 driver.findElement(By.xpath("//a[@href='/login']")).click();
		String exp="https://www.automationexercise.com/";
       boolean url= driver.getCurrentUrl().contains(exp);
       System.out.println("The home page is verified: "+url);
       
      
        boolean signupbutton= driver.findElement(By.xpath("//button[text()='Signup']")).isEnabled();
		System.out.println("Signup button is verified: " +signupbutton);
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Sufiyan");
		driver.findElement(By.xpath("//input[@name='name']//following::input[1]")).sendKeys("sufiyan@gmail.com");
		driver.findElement(By.xpath("//button[text()='Signup']")).click();
		boolean eai=driver.findElement(By.xpath("//h2[@class='title text-center']")).isDisplayed();
		System.out.println("The 'ENTER ACCOUNT INFORMATION' is visible" +eai);
		driver.findElement(By.xpath("//input[@id='id_gender1']")).click();
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("34567");
		WebElement day=driver.findElement(By.xpath("//select[@id='days']"));
		Select daydrop=new Select(day);
		daydrop.selectByIndex(12);
		WebElement month=driver.findElement(By.xpath("//select[@id='months']"));
	    Select monthdrop=new Select(month);
		monthdrop.selectByVisibleText("May");
		WebElement year=driver.findElement(By.xpath("//select[@id='years']"));
	    Select yeardrop=new Select(year);
	    yeardrop.selectByIndex(12);
	    /*WebDriverWait wt=new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement newsletter=driver.findElement(By.id("newsletter"));
		wt.until(ExpectedConditions.visibilityOf(newsletter));
		newsletter.click();
		driver.findElement(By.id("optin")).click();*/
		driver.findElement(By.id("first_name")).sendKeys("Sufiyan");
		driver.findElement(By.id("last_name")).sendKeys("K U");
		driver.findElement(By.id("company")).sendKeys("Techmindz");
		driver.findElement(By.id("address1")).sendKeys("Abc city");
		driver.findElement(By.id("address2")).sendKeys("cba city");
		driver.findElement(By.id("state")).sendKeys("Kerala");
		driver.findElement(By.id("city")).sendKeys("Ernakulam");
	    driver.findElement(By.id("zipcode")).sendKeys("256978");
	    driver.findElement(By.id("mobile_number")).sendKeys("12345678");
	    driver.findElement(By.xpath("//button[text()='Create Account']")).click();	
	    Thread.sleep(5000);
	}
	@org.testng.annotations.Test(priority = 1)
	public void test2() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.navigate().to("https://automationexercise.com/login");
		driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("sufiyan@gmail.com");
		driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys("34567");
		driver.findElement(By.xpath("//button[@data-qa='login-button']")).click();
		boolean loggedinasuser=driver.findElement(By.xpath("//i[@class='fa fa-user']")).isDisplayed();
		System.out.println("Verifies the logged in as username is present" +loggedinasuser);
		
		
		
	}
	@org.testng.annotations.Test(priority = 2)
	public void productbuying() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(By.xpath("//a[@href='/products']")).click();
		//WebDriverWait wt=new WebDriverWait(driver, Duration.ofSeconds(20));
		/*wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/product_details/2' and @style='color: brown;']")));
		WebElement addtocart1=driver.findElement(By.xpath("//a[@href='/product_details/2' and @style='color: brown;']"));
		addtocart1.click();*/
		Actions act=new Actions(driver);
		WebElement product1=driver.findElement(By.xpath("//img[@src='/get_product_picture/1']"));
		WebElement product2=driver.findElement(By.xpath("//img[@src='/get_product_picture/2']"));
		act.moveToElement(product1);
		driver.findElement(By.xpath("//a[@data-product-id='1']")).click();
		driver.findElement(By.xpath("//button[text()='Continue Shopping']"));
		act.moveToElement(product2);
		driver.findElement(By.xpath("//a[@data-product-id='2']")).click();
		/*wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/product_details/1' and @style='color: brown;']")));
		WebElement addtocart2=driver.findElement(By.xpath("//a[@href='/product_details/1' and @style='color: brown;']"));
		addtocart2.click();*/
		driver.findElement(By.xpath("//a[@href='/view_cart']")).click();
		Thread.sleep(5000);
		//driver.findElement(By.xpath("//button[@class='btn btn-default cart']")).click();
		

	}
	@org.testng.annotations.Test(priority = 3)
	public void cart() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Thread.sleep(5000);
		//driver.findElement(By.xpath("//a[@href='/view_cart']")).click();
		driver.findElement(By.xpath("//a[text()='Proceed To Checkout']")).click();
		driver.findElement(By.xpath("//a[text()='Place Order']")).click();
		
	}
}
