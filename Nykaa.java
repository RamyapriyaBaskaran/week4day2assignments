package week4.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		Actions builder = new Actions(driver);
		WebElement findElement = driver.findElement(By.xpath("(//a[@id='category'])/following::a[1]"));
		builder.clickAndHold(findElement).perform();
		driver.findElement(By.id("brandSearchBox")).sendKeys("L'Oreal Paris");
		driver.findElement(By.linkText("L'Oreal Paris")).click();
		String title = driver.getTitle();
		if (title.contains("L'Oreal Paris"))
			System.out.println("Title of the page: "+title);
		else
			System.out.println("Title of the page is different");
		
		driver.findElement(By.className("sort-name")).click();
		driver.findElement(By.xpath("(//div[@class='control-value'])[4]//span")).click();
		driver.findElement(By.xpath("//span[text()='Category']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		driver.findElement(By.xpath("//span[text()='Shampoo']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		driver.findElement(By.xpath("//span[text()='Color Protection']")).click();
		boolean displayed = driver.findElement(By.xpath("//span[text()='Shampoo']")).isDisplayed();
		if (displayed == true)
			System.out.println("Shampoo filter is applied");
		else
			System.out.println("Shampoo filter is not applied... please check");
		
		driver.findElement(By.xpath("//div[contains(text(),'Paris Colour Protect Shampoo')][1]")).click();

		Set<String> windowHandles = driver.getWindowHandles();
		List<String> window = new ArrayList<String>(windowHandles);
		driver.switchTo().window(window.get(1));
		
		WebElement mlValueSelector = driver.findElement(By.xpath("//select[@title='SIZE']"));
		Select obj = new Select(mlValueSelector);
		obj.selectByIndex(0);
		
		String MRP = driver.findElement(By.xpath("//span[text()='MRP:']/following-sibling::span[1]")).getText();
		System.out.println("Price: "+MRP);
		
		driver.findElement(By.xpath("//span[text()='ADD TO BAG']")).click();
		driver.findElement(By.xpath("//button[@type='button']")).click();
		
		driver.switchTo().frame(0);
		String bagTotal = driver.findElement(By.xpath("//div[@class='value'][1]")).getText();
		System.out.println("Bag Total displayed in shopping cart: "+bagTotal);
		String grandTotal = driver.findElement(By.xpath("(//div[@class='value'])[4]")).getText();
		System.out.println("Grand Total (after TAX added): "+grandTotal);
		
		driver.findElement(By.xpath("//span[text()='PROCEED']")).click();
		driver.findElement(By.xpath("//button[@class='btn full big']")).click();
		String totalAfterCheckout = driver.findElement(By.xpath("(//div[@class='value'])[2]")).getText();
		System.out.println("Grand Total (after TAX added) after proceeded as Guest: "+totalAfterCheckout);
		if (grandTotal.equals(totalAfterCheckout))
			System.out.println("Total price displayed is same");
		else
			System.out.println("Total price is different");
		driver.quit();
	}
}
