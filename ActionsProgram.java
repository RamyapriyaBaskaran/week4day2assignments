package week4.assignments;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ActionsProgram {
	public void resizable() {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://jqueryui.com/resizable");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		driver.switchTo().frame(0);
		WebElement mousePointer = driver.findElement(By.xpath("//div[@id='resizable']//div[3]"));
		Actions builder = new Actions(driver);
		builder.clickAndHold(mousePointer).moveByOffset(100,50).perform();
	}
	
	public void drag() {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/drag.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		WebElement elementDrag = driver.findElement(By.id("draggable"));
		Point location = elementDrag.getLocation();
		int x = location.getX();
		int y = location.getY();
		Actions builder = new Actions(driver);
		builder.dragAndDropBy(elementDrag, x+5, y+5).perform();
	}

	public void drop() {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/drop.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		WebElement dragElement = driver.findElement(By.id("draggable"));
		WebElement dropElement = driver.findElement(By.id("droppable"));
		Actions builder = new Actions(driver);
		builder.dragAndDrop(dragElement, dropElement).perform();
	}
	
	public void selectable() {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/selectable.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		WebElement item1 = driver.findElement(By.xpath("//ol[@id='selectable']/li[1]"));
		WebElement item3 = driver.findElement(By.xpath("//ol[@id='selectable']/li[3]"));
		WebElement item5 = driver.findElement(By.xpath("//ol[@id='selectable']/li[5]"));
		
		Actions builder = new Actions(driver);
		builder.keyDown(Keys.CONTROL).click(item1).click(item3).click(item5).keyUp(Keys.CONTROL).perform();
	}
	
	public void sortable() {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/sortable.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		WebElement itemSource= driver.findElement(By.xpath("(//ul[@id='sortable'])/li[3]"));
		WebElement itemDestination= driver.findElement(By.xpath("(//ul[@id='sortable'])/li[5]"));
		Actions builder = new Actions(driver);
		builder.clickAndHold(itemDestination).moveToElement(itemSource).release().perform();
	}
		
	public static void main(String[] args) {
		
		ActionsProgram obj = new ActionsProgram();
		obj.resizable();
		obj.drag();
		obj.drop();
		obj.selectable();
		obj.sortable();
	}

}
