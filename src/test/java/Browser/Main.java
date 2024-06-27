package Browser;

public class Main {

	public static void main(String[] args) throws Exception {
		
		HandleAlert.report();
		HandleAlert.createDriver();
		HandleAlert.navigateToUrl();
	    HandleAlert.hoverOnSwitchTo();
		HandleAlert.clickOnAlerts();
		HandleAlert.clickOnAlertWithOk();
		HandleAlert.clickOnAlertWithOkAndCancel();
		HandleAlert.clickOnAlertWithTextbox();
		HandleAlert.callFlush();
		HandleAlert.closeBrowser();
		
	}

}
