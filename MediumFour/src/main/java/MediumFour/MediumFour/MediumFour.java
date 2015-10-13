package MediumFour.MediumFour;

import java.util.List;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MediumFour {

	public static void main(String args[])
	{

		WebDriver driver = new FirefoxDriver();
		driver.get("https://www.google.co.in/");
		
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		WebElement element;
		
		String search = "OpenText\n";
		element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("lst-ib")));
		element.sendKeys(search);
				
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		e.printStackTrace();
	}
		int yes = 0;
		int no = 0;
		element = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("_e4b")));
		List<WebElement> pages = driver.findElements(By.className("_e4b"));
		for( WebElement pg : pages)
		{
			System.out.println("String :: " + pg.getText());
			if(pg.getText().contains(search.substring(0,search.length()-1)) || pg.getText().contains(search.substring(0,search.length()-1).toLowerCase()))
			{
				yes++;
			}
			else
			{
				no++;
			}
		}
		
		final int y = yes;
		final int n = no;
		
		Thread thread = new Thread(){
			public void run()
			{
				JOptionPane.showMessageDialog(null, "Verification Completed.\nMatched :: " + y + "\nFailed :: " + n );
			};
		};
		
		thread.start();
		driver.quit();
	}

}
