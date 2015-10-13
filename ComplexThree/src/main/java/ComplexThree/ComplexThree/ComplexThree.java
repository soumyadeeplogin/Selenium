package ComplexThree.ComplexThree;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ComplexThree {

	static WebDriver driver;
	static WebElement element;
	static WebDriverWait wait;


	public static void main(String args[])
	{
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://www.google.com/advanced_search");

		wait = new WebDriverWait(driver, 50);
		WebElement element;		
		element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("_dKg")));
		element.sendKeys("OpenText");	
		element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("_bKg")));
		element.sendKeys("enterprise\n");	
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<WebElement> tabs = new ArrayList<WebElement>();
		tabs = driver.findElements(By.cssSelector("h3[class='r']"));
		boolean flag = true;
		for(WebElement tab: tabs)
		{
//			System.out.println(tab.getText());
			if(tab.getText().toLowerCase().contains("enterprise") && !(tab.getText().toLowerCase().contains("opentext")))
			{
				Thread thread = new Thread(){
					public void run()
					{
						JOptionPane.showMessageDialog(null, "Failed");
					};
				};
				flag = false;
				thread.start();
				break;
			}
		}
		if(flag)
		{
			Thread threads = new Thread(){
				public void run()
				{
					JOptionPane.showMessageDialog(null, "Passed");
				};
			};
			flag = false;
			threads.start();
		}
		driver.quit();
	}

}
