package cine.info.tests;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import cine.info.base.BasePage;
import cine.info.base.BaseTest;
import cine.info.model.MovieDetails;
import cine.info.pages.ImdbMainPage;
import cine.info.pages.WikiMainPage;

public final class TC0001_CineInfoTest extends BaseTest {

	private String wikiWindow;
	private String imdbWindow;
	WikiMainPage wikiMainPage;
	ImdbMainPage imdbMainPage;
	Map<String, MovieDetails> mapWiki = new HashMap<>(), mapImdb = new HashMap<>();

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
	@SuppressWarnings("deprecation")
	@Test(dataProvider = "getTestData")
	public void run(String movie) {
		log.info("\n########## Validating the details of movie (" + movie + ") ##########");
		MovieDetails wikiMovieDetails = getMovieDetailsFromWiki(movie);
		MovieDetails imdbMovieDetails = getMovieDetailsFromImdb(movie);
		SoftAssertions softAssert = new SoftAssertions();
		softAssert.assertThat(wikiMovieDetails).isEqualToComparingFieldByField(imdbMovieDetails).isNotNull().isInstanceOf(MovieDetails.class);
		softAssert.assertAll();
	}

	private MovieDetails getMovieDetailsFromWiki(String movie) {
		wikiMainPage.switchToWindow(wikiWindow);
		assertThat(wikiMainPage.verifyLogoisDisplayed()).as("Failed to load the required page!").isTrue();
		wikiMainPage.sendData(movie).navigateToCinema();
		assertThat(wikiMainPage.getPageTitle()).isNotNull().as("Failed to get the title of the current page!").isNotEmpty().isNotBlank().as("Failed to navigate to the required page!").containsIgnoringCase(movie);
		return wikiMainPage.searchMoviesDetails(movie);
	}

	private MovieDetails getMovieDetailsFromImdb(String movie) {
		imdbMainPage.switchToWindow(imdbWindow);
		assertThat(imdbMainPage.verifyLogoisDisplayed()).as("Failed to load the required page!").isTrue();
		imdbMainPage.sendData(movie).navigateToCinema(movie);
		assertThat(imdbMainPage.getPageTitle()).isNotNull().as("Failed to get the title of the current page!").isNotEmpty().isNotBlank().as("Failed to navigate to the required page!").containsIgnoringCase(movie);
		return imdbMainPage.searchMoviesDetails(movie);
	}

}
