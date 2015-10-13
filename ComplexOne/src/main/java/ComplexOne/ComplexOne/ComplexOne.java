package ComplexOne.ComplexOne;

import java.util.List;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ComplexOne {

	public static void main(String args[]) {

		WebDriver driver = new FirefoxDriver();
		driver.get("https://www.google.co.in/");

		WebDriverWait wait = new WebDriverWait(driver, 50);
		WebElement element;

		String search = "OpenText\n";
		String searchcpy = "OpenText".toLowerCase();
		element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("lst-ib")));
		element.sendKeys(search);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		element = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("rc")));
		List<WebElement> pages = driver.findElements(By.className("rc"));
		for (WebElement pg : pages) {
			String temp = pg.getText().substring(0, pg.getText().indexOf(' ')).toLowerCase();
			if (!temp.equals(searchcpy)) {
				element = driver.findElement(By.cssSelector("[class='r'] a "));
				final String href = element.getAttribute("href");
				WebDriver ndriver = new FirefoxDriver();
				ndriver.get(href);
				driver.quit();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				final String nhref = ndriver.getCurrentUrl();
				Thread thread = new Thread() {
					public void run() {
						if (href.equals(nhref)){
							JOptionPane.showMessageDialog(null,
									"Verification Completed.\nExpected :: " + href + "\nFound :: " + nhref);
						}
						else {
							JOptionPane.showMessageDialog(null,
									"Verification failed.\nExpected :: " + href + "\nFound :: " + nhref);
						}
					};
				};

				thread.start();
				ndriver.quit();
				break;
			}
		}
	}
}
