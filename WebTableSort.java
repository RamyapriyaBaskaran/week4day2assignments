package week4.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebTableSort {

	public static void main(String[] args) throws InterruptedException {
			WebDriverManager.chromedriver().setup();
			ChromeDriver driver = new ChromeDriver();
			driver.get("http://www.leafground.com/pages/sorttable.html");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			
			WebElement element = driver.findElement(By.id("table_id"));
			List<WebElement> rows = element.findElements(By.tagName("tr"));
			System.out.println("Number of Rows :" +rows.size()); 
			WebElement row = rows.get(1);
			List<WebElement> cols = row.findElements(By.tagName("td"));
			System.out.println("Number of Coulmns :" + cols.size());
		
			List<String> name = new ArrayList<String>();
			for (int i=1; i<rows.size(); i++) {
				WebElement eachRow = rows.get(i);
				WebElement tdColData = eachRow.findElements(By.tagName("td")).get(1);
				name.add(tdColData.getText());
			}
			
			Set<String> set = new TreeSet<String>(name); 
			System.out.println(set); 
			
			driver.findElement(By.xpath("//th[@class='sorting'][1]")).click();
			Thread.sleep(3000);
			
			List<String> name1 = new ArrayList<String>();
			for (int i=1; i<rows.size(); i++) {
				String text = driver.findElement(By.xpath("//table[@id='table_id']//tr["+i+"]//td[2]")).getText();
				name1.add(text);
			}	
			Set<String> set1 = new LinkedHashSet<String>(name1); 
			System.out.println("After sorted: "+set1);
			
			if(set.equals(set1))
				System.out.println("Names sorted");
			else
				System.out.println("Names not sorted");
	}
}
