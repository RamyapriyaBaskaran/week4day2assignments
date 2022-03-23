package week4.assignments;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ExplicitWaitProgram {
	
	public void disappear() throws IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/disapper.html");
		driver.manage().window().maximize();
		WebElement disappearElement = driver.findElement(By.xpath("//button[@id='btn']//b"));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
		wait.until(ExpectedConditions.invisibilityOf(disappearElement));
		String str = driver.findElement(By.xpath("//div[@id='show']/p")).getText();
		File screenshotAs = driver.getScreenshotAs(OutputType.FILE);
		File image1 = new File("./snaps/img001.jpg");
		FileUtils.copyFile(screenshotAs, image1);
		System.out.println(str);
		driver.close();
	}
	
	public void appear() throws IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/appear.html");
		driver.manage().window().maximize();
		WebElement appearElement = driver.findElement(By.xpath("//button[@id='btn']//b"));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(appearElement));
		String str = appearElement.getText();
		File screenshotAs = driver.getScreenshotAs(OutputType.FILE);
		File image2 = new File("./snaps/img002.jpg");
		FileUtils.copyFile(screenshotAs, image2);
		System.out.println(str);	
		driver.close();
	}

	public void textChange() throws IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/TextChange.html");
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.textToBe((By.id("btn")), "Click ME!"));
		driver.findElement(By.id("btn")).click();
		Alert simple = driver.switchTo().alert();
		String text = simple.getText();
		System.out.println(text);
		simple.accept();
		File screenshotAs = driver.getScreenshotAs(OutputType.FILE);
		File image3 = new File("./snaps/img003.jpg");
		FileUtils.copyFile(screenshotAs, image3);
		driver.close();
	}
	
		
	public static void main(String[] args) throws IOException {
		ExplicitWaitProgram prgm = new ExplicitWaitProgram();
		prgm.disappear();
		prgm.appear();
		prgm.textChange();
	}

}
