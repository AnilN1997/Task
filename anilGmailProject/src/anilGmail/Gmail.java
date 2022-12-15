package anilGmail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Gmail2 {
	public static void main(String[] args) throws InterruptedException, IOException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get(
				"https://accounts.google.com/v3/signin/identifier?dsh=S420415047%3A1670865390595434&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&rip=1&sacu=1&service=mail&flowName=GlifWebSignIn&flowEntry=ServiceLogin&ifkv=AeAAQh7ADlRIVXB85MUiAuot7mjfnr1rMvrYo7x2q9ymplPKecGaFOAfKRtt88f6T3k6nbj-gCBaMA");
		driver.manage().window().maximize();
		Thread.sleep(1000);
		
		// Username 
		WebElement userName = driver.findElement(By.xpath("//input[@type='email']"));
		userName.sendKeys("demotesting4878");
		WebElement userNext = driver.findElement(By.xpath("//span[text()='Next']"));
		userNext.click();
		Thread.sleep(6000);
		
		// Password
		try {
			WebElement password = new WebDriverWait(driver, 9)
					.until(ExpectedConditions.elementToBeClickable(By.name("Passwd")));
			password.sendKeys("Demo@1234");
		} catch (StaleElementReferenceException e) {
			WebElement password = new WebDriverWait(driver, 9)
					.until(ExpectedConditions.elementToBeClickable(By.name("Passwd")));
			password.sendKeys("Demo@1234");
		}

		WebElement passNext = driver.findElement(By.xpath("//span[text()='Next']"));
		passNext.click();
		
		// Compose Mail
		WebElement composeClick = driver.findElement(By.xpath("//div[@class='T-I T-I-KE L3']"));
		composeClick.click();
		Thread.sleep(2000);
		String parentWin = driver.getWindowHandle();
		
		// Recepient of mail
		driver.findElement(By.xpath("(//table[@class='GS']/tbody/tr/td)[2]/descendant::div[@class='aH9']/input"))
				.sendKeys("demotesting4878@gmail.com");
		
		//Subject of mail
		driver.findElement(By.xpath("(//table[@class='aoP aoC']/tbody/tr/td)[2]/form/div[3]/input")).sendKeys("Damco");
		Thread.sleep(2000);
		
		// Body of mail
		WebElement body = driver.findElement(By.xpath("(//table[@class='cf An']/tbody/tr/td)[2]/div[2]/child::div"));
		body.sendKeys("Line one \nLine two\nLine Three");

		Actions act = new Actions(driver);
		act.keyDown(Keys.CONTROL).sendKeys("a").build().perform();
		Thread.sleep(5000);
		
		// Formating 
		WebElement formattingO = driver.findElement(By.xpath("//div[@class=\"dv\"]"));
		formattingO.click();
		Thread.sleep(3000);
		
		// Bullet point of body
		WebElement bulletP = driver.findElement(By.xpath("//div[@class=\"eO  aaA aaB\"]"));
		bulletP.click();
		Thread.sleep(3000);
		formattingO.click();
		WebElement attachOption = driver.findElement(By.xpath("//input[@type='file']"));
		attachOption.sendKeys("C:\\Users\\vikid\\OneDrive\\Desktop\\code.png");
		Thread.sleep(3000);
		
		WebElement closingBody = driver.findElement(By.xpath("//img[@alt=\"Close\"]"));
		closingBody.click();
		Thread.sleep(3000);
		
		// Draft Mail Button
		WebElement draftBtn = driver.findElement(By.xpath("//a[text()='Drafts']"));
		draftBtn.click();
		Thread.sleep(3000);

		WebElement draftMail = driver.findElement(By.xpath("(//span[contains(normalize-space(),'Damco')] )[1]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", draftMail);

		Thread.sleep(3000);

		Set<String> allWinid = driver.getWindowHandles();
		for (String child : allWinid) {
			if (!(child.equals(parentWin))) {
				driver.switchTo().window(child);
			}
		}

		WebElement sendOption = driver.findElement(By.xpath("//div[@class='T-I J-J5-Ji aoO v7 T-I-atl L3']"));
		sendOption.click();

		// Inbox of mail
		WebElement inboxClick = driver.findElement(By.xpath("//a[text()='Inbox']"));
		inboxClick.click();
		WebElement subject = driver.findElement(By.xpath("//span[@class='bog']"));
		subject.click();
		String actualSubject = subject.getText();
		System.out.println(actualSubject);
		
		// Here subject got verified
		Assert.assertEquals(actualSubject, "Damco");
		Thread.sleep(3000);
		
		// Download File
		WebElement download = driver.findElement(By.xpath("//div[@class='aSK J-J5-Ji aYr']"));

		download.click();

	}
}