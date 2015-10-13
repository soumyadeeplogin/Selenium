package MediumFive.MediumFive;

import java.util.List;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MediumFive {

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
		element = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("_e4b")));
		List<WebElement> pages = driver.findElements(By.className("_e4b"));

		element = pages.get(pages.size()-1);
		final String lastText = element.getText();
		element.click();

		element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("lst-ib")));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		final String newText = driver.findElement(By.id("lst-ib")).getAttribute("value");

		Thread thread = new Thread(){
			public void run()
			{
				if(lastText.equals(newText))
				{
					JOptionPane.showMessageDialog(null, "Success");
				} 
				else
				{
					JOptionPane.showMessageDialog(null, "Failed");
				}

			};
		};
		
		thread.start();
		driver.quit();
	}

}
