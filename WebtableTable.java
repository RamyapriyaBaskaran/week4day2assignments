package week4.assignments;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebtableTable {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/table.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		WebElement element = driver.findElement(By.id("table_id"));
		List<WebElement> rows = element.findElements(By.tagName("tr"));
		System.out.println("Number of Rows :" +rows.size()); 
		WebElement row = rows.get(1);
		List<WebElement> cols = row.findElements(By.tagName("td"));
		System.out.println("Number of Coulmns :" + cols.size());
	
		for (int i=1; i<rows.size(); i++) {
			WebElement eachRow = rows.get(i);
			WebElement tdColData = eachRow.findElements(By.tagName("td")).get(0);
			if(tdColData.getText().equals("Learn to interact with Elements")){
				String text = eachRow.findElements(By.tagName("td")).get(1).getText();
				System.out.println("Learn to interact with element: "+text);
			}	
	    }
		driver.findElement(By.xpath("//tr[@class='even'][2]//input")).click();
		System.out.println("Selected the checkbox");
	}
}
