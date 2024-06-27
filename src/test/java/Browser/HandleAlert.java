package Browser;

import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class HandleAlert {
	
	public static WebDriver driver;// WebDriver instance
	public static String baseurl;// URL to navigate
	public static String filepath = System.getProperty("user.dir")+"\\src\\ExcelData\\Name.xlsx"; // File path for Excel data
	public static String openFileAt = System.getProperty("user.dir")+"\\src\\ExcelData\\OutputData.xlsx"; // File path for output data
	public static ExtentTest test; // Test instance for ExtentReports
	public static ExtentReports extent; // ExtentReports instance
	
	// Method to get the ExtentReports instance	
	public static void report() {
		extent = ExtentReport.getReport();
	}
	
	// Method to create the WebDriver
	public static WebDriver createDriver() {
		
		driver = DriverSetup.getWebDriver();
		return driver;
	}
	
	// Method to navigate to the URL
	public static void navigateToUrl() {
		baseurl = "http://demo.automationtesting.in/Alerts.html";
		
		test = extent.createTest("TC001- Opening Browser");
		test.log(Status.INFO, "Browser Opening");
		
		driver.navigate().to(baseurl);
		
		test.log(Status.PASS, "The browser has been opened successfully");
		driver.manage().window().maximize();
		
	}
	
	// Method to take screenshots
	public static void takeScreenShots(String imgName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File trg = new File((System.getProperty("user.dir"))+"\\src\\ScreenShots\\"+imgName);
		FileUtils.copyFile(src, trg);
	}
	
	// Method to hover on SwitchTo
	public static void hoverOnSwitchTo() throws InterruptedException, IOException {
		Actions action = new Actions(driver);
		Thread.sleep(3000);
		
		test = extent.createTest("TC002- Testing the Hover Functionality ");
		test.log(Status.INFO, "Hovering on SwithTo Tab");
		
		action.moveToElement(driver.findElement(By.linkText("SwitchTo"))).perform();
		
		test.log(Status.PASS, "Successfully Hovered on Switch To Tab");
		
		takeScreenShots("SwitchTo.png");
		Thread.sleep(2000);
	}
	
	// Method to click on Alerts
	public static void clickOnAlerts() throws InterruptedException, IOException {
		Actions action = new Actions(driver);
		
		test = extent.createTest("TC003- Clicking Alerts");
		
		test.log(Status.INFO, "Opening Alerts");
		
		action.moveToElement(driver.findElement(By.xpath("//a[normalize-space()='Alerts']"))).perform();
		
		test.log(Status.PASS, "Successfully Opened Alerts");
		
		takeScreenShots("Alert.png");
	}
	
	// Method to click on Alert with OK
	public static void clickOnAlertWithOk() throws Exception {
		
		test = extent.createTest("TC004-Alert With Ok Tab");

		driver.findElement(By.xpath("//ul[@class='nav nav-tabs nav-stacked']/child::li[1]")).click();
	
		test.log(Status.INFO, "Opening AlertWithOk");
		test.log(Status.PASS, "Successfully Opened Alert With Ok");
		
		test.log(Status.INFO, "Clicking the button inside Alert with Ok");
		
		driver.findElement(By.xpath("//div[@id='OKTab']/button")).click();
		
		test.log(Status.PASS,"Successfully Clicked on AlertWithOk button");
		
		try {
			Alert alert_window = driver.switchTo().alert();
			System.out.println("Alert Appears\n");
			Thread.sleep(3000);
			
			test = extent.createTest("TC005-Alert With Ok PopUp");
			test.log(Status.INFO,"Alert Pop up");
			
			ExcelUtility.setCellData(openFileAt, "OutputData","Poped-Up" , 1, 1);
		
			alert_window.accept();
			test.log(Status.PASS,"Alert Poped Up");
			Thread.sleep(2000);
			
			takeScreenShots("ClickOk.png");
			ExcelUtility.setCellData(openFileAt, "OutputData","No Text Verification" , 1, 2);
		}
		catch(NoAlertPresentException e) {
			
			System.out.println("Alert Does Not Appear\n");
			
			test.log(Status.FAIL, "Alert Does not Appear");
			
			ExcelUtility.setCellData(openFileAt, "OutputData","Not Poped-Up" , 1, 1);
		}
	}
	
	// Method to click on Alert with Ok and Cancel
	public static void clickOnAlertWithOkAndCancel() throws Exception {
		
		test = extent.createTest("TC006-Alert With Ok & Cancel Tab");
		test.log(Status.INFO,"Clicking Alert With Ok & Cancel Tab");
		
		driver.findElement(By.xpath("//a[normalize-space()='Alert with OK & Cancel']")).click();
		
		test.log(Status.PASS,"Successfully Clicked Alert with Ok & Cancel");
		
		test.log(Status.INFO, "Clicking the button inside Alert with Ok & Cancel");
		driver.findElement(By.xpath("//div[@id='CancelTab']/button")).click();
		test.pass("Successfully Clicked the button");
		
		try {
			Alert alert_window = driver.switchTo().alert();
			Thread.sleep(2000);
			
			test = extent.createTest("TC007-Alert With Ok & Cancel Pop Up");
			test.log(Status.INFO, "Alert With Ok & Cancel Appearance");
			
			System.out.println("Alert Appears with Confirm box");
			ExcelUtility.setCellData(openFileAt, "OutputData","Poped-Up" , 2, 1);
			alert_window.dismiss();	
			
			test.pass("Alert with Ok and Cancel Appeared");
			
			Thread.sleep(2000);
			takeScreenShots("ClickCancel.png");
			
			String actual_Text = driver.findElement(By.xpath("//p[@id='demo']")).getText();
			String expected_Text = ExcelUtility.getCellData(filepath,"Sheet1", 1, 1);
			
			if(expected_Text.equals(actual_Text)) {
				test.log(Status.INFO,"Text Verification");
				test.pass("Text Verified Successfully");
				
				System.out.println("Test Case Passed\n");
				ExcelUtility.setCellData(openFileAt, "OutputData","Passed" , 2, 2);
			}
			else {
				test.fail("Text Verification Failed");
				
				System.out.println("Test Case Not Passed\n");	
				
				ExcelUtility.setCellData(openFileAt, "OutputData","Not Passed" , 2, 2);
			}	
		}
		catch(NoAlertPresentException e) {
			test.fail("Alert with Ok & Cancel does not Appear");
			
			System.out.println("Alert Does Not Appear with Confirm box");
			ExcelUtility.setCellData(openFileAt, "OutputData","Not Poped-Up" , 2, 1);
		}	
	}
	
	// Method to click on Alert with Textbox
	public static void clickOnAlertWithTextbox() throws Exception {
		
		test = extent.createTest("TC008-Alert With Text Box Tab");
		test.log(Status.INFO,"Clicking Alert With Text Box Tab");
		
		driver.findElement(By.xpath("//a[normalize-space()='Alert with Textbox']")).click();
		
		test.pass("Successfully Tab Clicked");
		
		test.log(Status.INFO,"Clicking Button Inside Alert With Text Box");
		driver.findElement(By.xpath("//div[@id='Textbox']/button")).click();
		test.pass("Successfully Clicked the Button");
		
		try {
			Alert alert_window = driver.switchTo().alert();
			Thread.sleep(2000);
			
			test = extent.createTest("TC009-Alert With Text Box Pop Up");
			test.log(Status.INFO, "Alert With Text Box Appearance");
			test.pass("Alert With Text Box Appeared");
			
			System.out.println("Alert Appears with Textbox");
			ExcelUtility.setCellData(openFileAt, "OutputData","Poped-Up" , 3, 1);
			String name = ExcelUtility.getCellData(filepath,"Sheet1", 1, 0);
			
			alert_window.sendKeys(name);
			alert_window.accept();	
			
			Thread.sleep(2000);
			takeScreenShots("TextBox.png");
			
			String actual_Text = driver.findElement(By.xpath("//p[@id='demo1']")).getText();
			String expected_Text = "Hello "+name+" How are you today";
			
			if(expected_Text.equals(actual_Text)) {
				test.log(Status.INFO,"Text Verification");
				test.pass("Text Verified Successfully");
				
				System.out.println("Test Case Passed \n");
				ExcelUtility.setCellData(openFileAt, "OutputData","Passed" , 3, 2);
			}
			else {
				test.fail("Text Verification Failed");
				
				System.out.println("Test Case Not Passed\n");
				ExcelUtility.setCellData(openFileAt, "OutputData","Poped-Up" , 3, 2);
			}	
		}
		catch(NoAlertPresentException e) {
			test.fail("Alert with Text Box does not Appear");
			
			System.out.println("Alert Does Not Appear with Text box");
			ExcelUtility.setCellData(openFileAt, "OutputData","Not Poped-Up" , 3, 1);
		}	
	}
	
	public static void callFlush() {
		extent.flush();
	}
	public static void closeBrowser() {
		driver.quit();
	}

	
}
