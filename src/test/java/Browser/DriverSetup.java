package Browser;

import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverSetup {

	public static WebDriver driver;
	
	public static WebDriver getWebDriver() {
		
		WebDriver driver = null;
		boolean flag = true;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Driver Name(chrome/edge) : ");
		String DriverName = sc.next();
		System.out.println();
		while(flag) {
			if(DriverName.equalsIgnoreCase("chrome")) {
				driver = new ChromeDriver();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				flag = false;
			}
			else if(DriverName.equalsIgnoreCase("edge")) {
				driver = new EdgeDriver();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				flag = false;
			}
		}
		sc.close();
		return driver;
		
	}
}
