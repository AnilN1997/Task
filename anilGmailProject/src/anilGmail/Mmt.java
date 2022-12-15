package anilGmail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Mmt {
	public static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.makemytrip.com/flights/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement a = driver.findElement(By.xpath("//input[@id='fromCity']"));
		a.sendKeys("Delhi");
		driver.findElement(By.xpath("//input[@id='toCity']")).sendKeys("Mumbai");
		WebElement b = driver.findElement(By.xpath("//*[@class='calc60']/child::p[text()='Mumbai, India']"));
		driver.findElement(By.xpath("(//*[@data-cy='account'])[1]")).click();
		driver.findElement(By.xpath("(//*[@role=\"listbox\"])//div/div/following-sibling::ul/li")).click();
		driver.findElement(By.xpath("//a[@class='primaryBtn font24 latoBold widgetSearchBtn ']")).click();
		driver.findElement(By.xpath("//span[@class='bgProperties icon20 overlayCrossIcon']")).click();
		driver.findElement(By.xpath("(//span[@class='pointer']/child::span)[2]")).click();

		List<WebElement> c = driver.findElements(By.xpath("//p[@class='boldFont blackText airlineName']"));
		for (WebElement d : c) {
			System.out.println(d.getText());
		}

		List<String> pricevalue = new ArrayList<String>();
		List<WebElement> f = driver
				.findElements(By.xpath("//p[@class='blackText fontSize18 blackFont white-space-no-wrap']"));
		
		for (int i = 0; i < f.size(); i++) {
			pricevalue.add(f.get(i).getText());
		}

		int size1 = pricevalue.size();
		int[] arr = new int[size1];
		

		for (int i = 0; i < size1; i++) {
			String newStr = pricevalue.get(i).replace("₹ ", "").replace(",", "");
			arr[i] = Integer.parseInt(newStr);
		}

		int minValue = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] < minValue) {
				minValue = arr[i - 1];

			}

		}

		System.out.println("Second lowest price value is " + minValue);

	}
}
