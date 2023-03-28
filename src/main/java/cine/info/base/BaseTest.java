package cine.info.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import cine.info.utils.DataProviderUtils;

public abstract class BaseTest extends BasePage {

	protected static final Logger log = LogManager.getLogger(BaseTest.class);
	// private static String excelSheetName = "Movies";

	protected BaseTest() {}

	// public static void setExcelSheetName(String excelSheetName) {
	// BaseTest.excelSheetName = excelSheetName;
	// }

	@BeforeClass
	@Parameters("browser")
	public void initialize(@Optional("edge") String browser) {
		setup(browser);
	}

	@DataProvider
	public String[] getTestData() {
		return DataProviderUtils.getExcelData(this.getClass().getSimpleName().split("_")[0]);
	}

	@Test(dataProvider = "getTestData")
	public void run(String columnName) {}

	@AfterClass
	public void finish() {
		tearDown();
	}

}
