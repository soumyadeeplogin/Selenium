package MediumOne.MediumOne;

import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MediumOne {
	
	final static String match = "The email and password you entered don't match.";

	public static void main(String args[])
	{
		//Connecting to FireFox
		WebDriver driver = new FirefoxDriver();
		
		//Opeining www.google.com
		driver.get("http://www.google.com");
		
		//Waiting for the page to load
		WebDriverWait wait = new WebDriverWait(driver, 50);
		
		//Click on Sign in
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("gb_70")));
		//	     driver.findElement(By.id("gb_70")).click();
		element.click();
		
		element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("Email")));
		element.sendKeys("imagingqa2@gmail.com");
		element = wait.until(ExpectedConditions.elementToBeClickable(By.id("next")));
		element.click();
		
		element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("Passwd")));
		element.sendKeys("OpenText123$");
		element = wait.until(ExpectedConditions.elementToBeClickable(By.id("signIn")));
		element.click();
		
		try{
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			String error = driver.findElement(By.id("errormsg_0_Passwd")).getText();
			if(error.equals(match))
			{
				driver.quit();
				JOptionPane.showMessageDialog(null, "Error Message Found & Verified");
			}
		}
		catch(Exception e)
		{
			driver.quit();
			JOptionPane.showMessageDialog(null, "Error Message Not Found. Verification Failed.");
		}
		
	}

}
