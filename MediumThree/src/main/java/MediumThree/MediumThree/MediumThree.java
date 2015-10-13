package MediumThree.MediumThree;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MediumThree {

	static List<String> href;
	static List<String> pageTitle;
	static List<WebElement> pages;
	static WebDriver driver;
	static String prePage; 
	static WebElement element;
	static WebDriverWait wait;
	
	
	public static void checkLink(int n)
	{
		final String lastTitle = pageTitle.get(n);
		WebElement element = pages.get(n);
		element.click();
		System.out.println(lastTitle);
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("title")));
		System.out.println(driver.getTitle());
		if(lastTitle.equals(driver.getTitle()))
		{
			Thread cnf1 = new Thread(){
				public void run(){	

					JOptionPane.showMessageDialog(null, "Confirmed.\nPage Title :: " + driver.getTitle() + "\nExpected :: " + lastTitle );
				}
			};
			cnf1.start();
		}
		else
		{
			Thread fail1 = new Thread(){
				public void run(){
			JOptionPane.showMessageDialog(null, "Failed.\nPage Title :: " + driver.getTitle() + "\nExpected :: " + lastTitle );
				}
			};
			fail1.start();
		}
		
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void getElements()
	{
		
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		prePage = driver.getCurrentUrl();		
		pages = driver.findElements(By.cssSelector("h3[class='r'] a"));
		
		for(WebElement pg : pages)
		{
			pageTitle.add(pg.getText());
			href.add(pg.getAttribute("href"));
		}	
	}
	public static void main(String args[])
	{
		href = new ArrayList<String>();
		pageTitle = new ArrayList<String>();
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.google.co.in/");
			
		wait = new WebDriverWait(driver, 50);
		WebElement element;		

		element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("lst-ib")));
		element.sendKeys("wikipedia\n");		
		for(int i  = 1; i <=5 ;i++ )
		{
			getElements();
			checkLink(pages.size() - i);
			driver.get(prePage);
		}
		
		driver.quit();
	}

}
