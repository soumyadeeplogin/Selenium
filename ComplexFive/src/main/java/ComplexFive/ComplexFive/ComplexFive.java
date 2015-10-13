package ComplexFive.ComplexFive;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ComplexFive {

	public static void main(String args[]) {

		WebDriver driver = new FirefoxDriver();
		driver.get("https://www.google.co.in/");
		WebDriverWait wait = new WebDriverWait(driver, 50);
		WebElement element;

		String search = "OpenText";
		element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("lst-ib")));
		element.sendKeys(search);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		List<WebElement> elements = new ArrayList<WebElement>();
		elements = driver.findElements(By.className("sbsb_c"));
		Actions mouseHover = new Actions(driver);
		mouseHover.moveToElement(elements.get(0).findElement(By.cssSelector("div[class='sbqs_c']"))).perform();

		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.findElement(By.xpath("//a[@href='#ifl']")).click();
		String url = driver.getCurrentUrl().toLowerCase();
		String pageName = driver.getTitle().toLowerCase();

		if (url.contains("opentext") && pageName.contains("opentext")) {
			Thread thread = new Thread() {
				public void run() {
					JOptionPane.showMessageDialog(null, "I am feeling lucky is confirmed");
				};
			};
			thread.start();
		} else {
			Thread thread = new Thread() {
				public void run() {
					JOptionPane.showMessageDialog(null, "I am feeling lucky confirmation failed");
				};
			};
			thread.start();
		}
		driver.quit();
	}
}
