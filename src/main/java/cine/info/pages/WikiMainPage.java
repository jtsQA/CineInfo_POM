package cine.info.pages;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import cine.info.base.BasePage;
import cine.info.model.MovieDetails;

public class WikiMainPage extends BasePage {

	private static final String URL = "wikiurl";
	private static Map<String, String> movieInfo = new HashMap<String, String>();

	/* Initializing the Page Objects */
	public WikiMainPage() {
		PageFactory.initElements(driver, this);
		setDriver(driver);
	}

	private final By imgWikiLogo = By.className("mw-logo");
	private final By inputSearchBox = By.name("search");
	private final By drpdwnMovieClick = By.xpath("//span[@class='cdx-menu-item__text__description']/bdi[contains(text(),'film')]/parent::span/preceding-sibling::span/bdi/span");
	private final By linkReleaseDate = By.xpath("//div[contains(text(),'Release date')]/parent::th/following-sibling::td//li");
	private final By linkCountry = By.xpath("//th[text()='Country']/following-sibling::td");

	public void getToPage() {
		getToPage(URL);
	}

	public String getToPageInWindow() {
		return getToPageInWindow(URL);
	}

	public String getToPageInNewWindow(String window) {
		return getToPageInNewWindow(URL, window);
	}

	public String getPageTitle() {
		return getTitle();
	}

	public boolean verifyLogoisDisplayed() {
		return verifyDisplayed(imgWikiLogo);
	}

	public WikiMainPage sendData(String inputData) {
		sendInputData(inputSearchBox, inputData);
		return this;
	}

	public WikiMainPage navigateToCinema() {
		confirmClick(drpdwnMovieClick);
		return this;
	}

	public String getReleaseDate() {
		return retrieveData(linkReleaseDate).split("\\(")[0].trim();
	}

	public String getCountry() {
		return retrieveData(linkCountry);
	}

	public Map<String, String> searchForMovie(String inputData) {
		getToPage();
		if (verifyLogoisDisplayed()) {
			sendData(inputData);
			navigateToCinema();
			movieInfo.put("ReleaseDate", getReleaseDate());
			movieInfo.put("Country", getCountry());
			System.out.println("Wiki - Release Date: " + movieInfo.get("ReleaseDate") + " and Country: " + movieInfo.get("Country"));
			return movieInfo;
		}
		return null;
	}

	public Map<String, String> searchForMovies(String inputData) {
		if (verifyLogoisDisplayed()) {
			sendData(inputData);
			navigateToCinema();
			movieInfo.put("ReleaseDate", getReleaseDate());
			movieInfo.put("Country", getCountry());
			System.out.println("Wiki - Release Date: " + movieInfo.get("ReleaseDate") + " and Country: " + movieInfo.get("Country"));
			return movieInfo;
		}
		return null;
	}

	public Map<String, String> searchMovies() {
		movieInfo.put("ReleaseDate", getReleaseDate());
		movieInfo.put("Country", getCountry());
		System.out.println("Wiki - Release Date: " + movieInfo.get("ReleaseDate") + " and Country: " + movieInfo.get("Country"));
		return movieInfo;
	}

	public MovieDetails searchMoviesDetails(String inputData) {
		MovieDetails movieDetails = new MovieDetails();
		movieDetails.setMovie(inputData);
		movieDetails.setReleaseDate(getReleaseDate());
		movieDetails.setCountry(getCountry());
		System.out.println("Wiki - " + movieDetails.toString());
		return movieDetails;
	}

}
