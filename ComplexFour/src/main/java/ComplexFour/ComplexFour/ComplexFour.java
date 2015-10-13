package ComplexFour.ComplexFour;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ComplexFour {

	static WebDriver driver;
	static WebElement element;
	static WebDriverWait wait;

	public static void main(String args[]) {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.co.in/");

		wait = new WebDriverWait(driver, 50);
		WebElement element;
		String search = "OpenText\n";
		element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("lst-ib")));
		element.sendKeys(search);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<WebElement> heads = new ArrayList<WebElement>();
		heads = driver.findElements(By.className("r"));
		for (WebElement head : heads) {
			if (head.getText().contains("Wikipedia")) {
				head.click();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (driver.getTitle().contains("Wikipedia")) {
					Thread thread = new Thread() {
						public void run() {
							JOptionPane.showMessageDialog(null, "Success");
						};
					};
					thread.start();
					driver.close();
				}
				break;
			}
		}
	}
}
