package cine.info.tests;

import static org.testng.Assert.assertTrue;
import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import cine.info.base.BasePage;
import cine.info.base.BaseTest;
import cine.info.pages.ImdbMainPage;
import cine.info.pages.WikiMainPage;

public final class TC0002_CineInfoTest extends BaseTest {

	private String wikiWindow;
	private String imdbWindow;
	WikiMainPage wikiMainPage;
	ImdbMainPage imdbMainPage;

	@Override
	@BeforeClass
	@Parameters("browser")
	public void initialize(@Optional("edge") String browser) {
		BasePage.setup(browser);
		wikiMainPage = new WikiMainPage();
		wikiWindow = wikiMainPage.getToPageInWindow();
		imdbMainPage = new ImdbMainPage();
		imdbWindow = imdbMainPage.getToPageInNewWindow(wikiWindow);
		log.info("wikiWindow: " + wikiWindow + "; imdbWindow: " + imdbWindow);
	}

	@Override
	@Test(dataProvider = "getTestData")
	public void run(String movie) {
		log.info("\n########## Validating the details of movie (" + movie + ") ##########");
		Map<String, String> mapWiki = getMovieDetailsFromWiki(movie);
		Map<String, String> mapImdb = getMovieDetailsFromImdb(movie);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(mapImdb.get("ReleaseDate"), mapWiki.get("ReleaseDate"), "Release Date of " + movie + " is not matching in IMDB and Wiki pages.");
		softAssert.assertEquals(mapImdb.get("Country"), mapWiki.get("Country"), "Country of " + movie + " is not matching in IMDB and Wiki pages.");
		softAssert.assertAll();
	}

	private Map<String, String> getMovieDetailsFromWiki(String movie) {
		wikiMainPage.switchToWindow(wikiWindow);
		assertTrue(wikiMainPage.verifyLogoisDisplayed(), "Failed to load the required page!");
		wikiMainPage.sendData(movie);
		wikiMainPage.navigateToCinema();
		assertTrue(wikiMainPage.getPageTitle().contains(movie), "Failed to navigate to the required page!");
		return wikiMainPage.searchMovies();
	}

	private Map<String, String> getMovieDetailsFromImdb(String movie) {
		imdbMainPage.switchToWindow(imdbWindow);
		assertTrue(imdbMainPage.verifyLogoisDisplayed(), "Failed to load the required page!");
		imdbMainPage.sendData(movie);
		imdbMainPage.navigateToCinema(movie);
		assertTrue(imdbMainPage.getPageTitle().contains(movie), "Failed to navigate to the required page!");
		return imdbMainPage.searchMovies();
	}

}
