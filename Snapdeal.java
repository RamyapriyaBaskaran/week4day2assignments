package week4.assignments;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Snapdeal {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		driver.findElement(By.xpath("//span[@class='catText']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[@class='linkTest'][1]")).click();
		
		String str = driver.findElement(By.xpath("//span[@class='category-name category-count']")).getText();
		System.out.println("Total number of items : " + str);
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		
		driver.findElement(By.xpath("//div[@class='sort-selected']")).click();
		driver.findElement(By.xpath("//ul[@class='sort-value']//li[2]")).click();
		
		Thread.sleep(3000);
		List<Integer> sortCheck = new ArrayList<Integer>();
		List<WebElement> priceAmount = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
		System.out.println("No. of Items: "+priceAmount.size());
		for(int i=0; i<priceAmount.size(); i++) {
		String priceWithRs = priceAmount.get(i).getText();
		String replaceAllRs = priceWithRs.replaceAll("[^0-9]", "");
		sortCheck.add(Integer.parseInt(replaceAllRs));
		}
		System.out.println(sortCheck);
		for (int j=0;j<sortCheck.size(); j++) {
			if(sortCheck.get(j)>(sortCheck.get(j+1)))  {
				System.out.println("Price Not sorted");
				break;
			}
		}
		
		WebElement minValue = driver.findElement(By.className("input-filter"));
		minValue.clear();
		minValue.sendKeys("500");
		
		WebElement maxValue = driver.findElement(By.xpath("(//input[@class='input-filter'])[2]"));
		maxValue.clear();
		maxValue.sendKeys("1200");
		
		driver.findElement(By.xpath("//div[@class='price-go-arrow btn btn-line btn-theme-secondary']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[@class='filter-color-tile Navy ']")).click();
		Thread.sleep(3000);

		String filterPrice = driver.findElement(By.xpath("//a[@class='clear-filter-pill'][1]")).getText();	
		if(filterPrice.equalsIgnoreCase("Rs. 500 - Rs. 1200"))
			System.out.println("Price filter applied is "+filterPrice);
		else
			System.out.println("Price filter is not applied correctly");
		
		String filtercol = driver.findElement(By.xpath("//a[@class='clear-filter-pill  '][1]")).getText();
		if(filtercol.equalsIgnoreCase("Navy"))
			System.out.println("Colour filter applied is "+filtercol);
		else
			System.out.println("Colour filter is not applied correctly");
		
		WebElement findElement = driver.findElement(By.xpath("//img[@class='product-image wooble']"));
		Actions builder = new Actions(driver);
		builder.moveToElement(findElement).perform();
		driver.findElement(By.xpath("//div[contains(text(),'Quick View')]")).click();
		String windowHandle = driver.getWindowHandle();
		driver.switchTo().window(windowHandle);
		
		String text = driver.findElement(By.xpath("//div[@class='product-price pdp-e-i-PAY-l clearfix']/span")).getText();
		System.out.println("Price displayed in Quick View :"+ text);
		String discountDisplayed = driver.findElement(By.xpath("//span[@class='percent-desc ']")).getText();
		System.out.println("Dicount displayed in Quick View :"+ discountDisplayed);	
		
		File screenshotAs = driver.getScreenshotAs(OutputType.FILE);
		File image = new File("./snaps/snapdeal.jpg");
		FileUtils.copyFile(screenshotAs, image);
		driver.close();
	}
}
