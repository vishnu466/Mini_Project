package Browser;

import com.aventstack.extentreports.ExtentReports; 
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport { 
	
	public static ExtentReports getReport() {
		
		// Creating an ExtentSparkReporter instance and specifying the path of the HTML report
		ExtentSparkReporter reporter = new ExtentSparkReporter("C:\\Users\\2318906\\eclipse-workspace\\HandsOnJava\\HandleAlertsAndPopups\\src\\ExtentReport\\Report.html");
		
		ExtentReports extent = new ExtentReports(); // Creating an ExtentReports instance
				
		extent.attachReporter(reporter); // Attaching the reporter to the ExtentReports instance
		
		return extent; // Returning the ExtentReports instance
	}
}
