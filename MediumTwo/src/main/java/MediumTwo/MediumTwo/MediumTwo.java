package MediumTwo.MediumTwo;

import java.util.List;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MediumTwo {

	public static void main(String args[])
	{

		WebDriver driver = new FirefoxDriver();
		driver.get("https://www.google.co.in/");
		
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		WebElement element;
		

		element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("lst-ib")));
		element.sendKeys("Selenium\n");
		
		element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("pnnext")));
		element.click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		e.printStackTrace();
	}
		
		int page =  Integer.parseInt((driver.findElements(By.cssSelector("[class='cur']"))).get(0).getText());
		
		if(page==2)
		{
			
			JOptionPane.showMessageDialog(null, "Page 2 confirmed.");
		}
		else
		{
			
			JOptionPane.showMessageDialog(null, "Page 2 confirmation failed.");
		}
		
		
		element = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("td a[class='fl']")));
		List<WebElement> pages = driver.findElements(By.cssSelector("td a[class='fl']"));
		for( WebElement pg : pages)
		{
			System.out.println("Pages :: " + pg.getText());
			if(pg.getText().equals("5"))
			{
				pg.click();
				break;
			}
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		page =  Integer.parseInt((driver.findElements(By.cssSelector("[class='cur']"))).get(0).getText());
		if(page==5)
		{
			
			JOptionPane.showMessageDialog(null, "Page 5 confirmed.");
		}
		else
		{
			
			JOptionPane.showMessageDialog(null, "Page 5 confirmation failed.");
		}
		
		element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("pnprev")));
		element.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		page =  Integer.parseInt((driver.findElements(By.cssSelector("[class='cur']"))).get(0).getText());
		if(page==4)
		{
			
			JOptionPane.showMessageDialog(null, "Page 4 :: Previous page confirmed.");
		}
		else
		{
			
			JOptionPane.showMessageDialog(null, "Page " + page + " :: Not the previous page.");
		}
		
		
		driver.quit();
	}

}
