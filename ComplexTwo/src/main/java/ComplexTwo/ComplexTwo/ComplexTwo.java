package ComplexTwo.ComplexTwo;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ComplexTwo {

	static WebDriver driver;
	static WebElement element;
	static WebDriverWait wait;


	public static void main(String args[])
	{
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.google.co.in/");

		wait = new WebDriverWait(driver, 50);
		WebElement element;		
		String search = "OpenText\n";
		String searchmat = search.substring(0,search.length()-1);
		element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("lst-ib")));
		element.sendKeys(search);	
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//		System.out.println("hdtb-tls");
		element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("hdtb-tls")));
		element.click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//		System.out.println("mn-dwn-arw");

		List<WebElement> tabs = new ArrayList<WebElement>();
		tabs = driver.findElements(By.cssSelector("div[class='mn-hd-txt']"));
		tabs.get(2).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("li_1"))).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tabs = new ArrayList<WebElement>();
		tabs = driver.findElements(By.cssSelector("[class='g'] [class='r']"));
		boolean flag = true;
		for(WebElement tab: tabs)
		{
			final String tabfin = tab.getText();
			System.out.println("tab.getText :: " + tab.getText().toLowerCase()  + "\nSearch String :: " + 
							searchmat + "\ncontains :: " + tab.getText().toLowerCase().contains(searchmat));
			if(!(tab.getText().toLowerCase().replaceAll("[^a-zA-Z ]", "").toLowerCase()).contains(searchmat))
			{
				Thread thread = new Thread(){
					public void run(){
						JOptionPane.showMessageDialog(null, "Failed. Found " + tabfin);
					};
				};
				thread.start();
				flag= false;
				driver.quit();
				break;
			}
			
		}
		if(flag)
		{
			Thread threads = new Thread(){
				public void run(){
					JOptionPane.showMessageDialog(null, "Passed");
				};
			};
			threads.start();
			driver.quit();
		}
		
		//		driver.quit();
	}

}
