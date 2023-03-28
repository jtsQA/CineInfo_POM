package cine.info.pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import cine.info.base.BasePage;
import cine.info.model.MovieDetails;

public class ImdbMainPage extends BasePage {

	private static final String URL = "imdburl";
	private static Map<String, String> movieInfo = new HashMap<String, String>();

	/* Initializing the Page Objects */
	public ImdbMainPage() {
		PageFactory.initElements(driver, this);
		setDriver(driver);
	}

	private final By imgIMDbLogo = By.id("home_img_holder");
	private final By inputSearchBox = By.id("suggestion-search");
	private final By drpdwnMovieClick = By.xpath("//div[contains(@class,'constTitle')]");
	private final By linkReleaseDate = By.xpath("//a[text()='Release date']/following-sibling::div//a");
	private final By linkCountry = By.xpath("//span[text()='Country of origin']/following-sibling::div//a");

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
		return verifyDisplayed(imgIMDbLogo);
	}

	public ImdbMainPage sendData(String inputData) {
		sendInputData(inputSearchBox, inputData);
		return this;
	}

	public ImdbMainPage navigateToCinema(String inputData) {
		confirmClickFromList(drpdwnMovieClick, inputData);
		return this;
	}

	public String getReleaseDate() {
		String date = retrieveData(linkReleaseDate).split("\\(")[0].trim();
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMMM d, yyyy");
		LocalDate localDate = LocalDate.parse(date, pattern);
		return localDate.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));
	}

	public String getCountry() {
		return retrieveData(linkCountry);
	}

	public Map<String, String> searchForMovie(String inputData) {
		getToPage();
		if (verifyLogoisDisplayed()) {
			sendData(inputData);
			navigateToCinema(inputData);
			movieInfo.put("ReleaseDate", getReleaseDate());
			movieInfo.put("Country", getCountry());
			System.out.println("Wiki - Release Date: " + movieInfo.get("ReleaseDate") + " and Country: " + movieInfo.get("Country"));
			return movieInfo;
		}
		return Collections.emptyMap();
	}

	public Map<String, String> searchForMovies(String inputData) {
		if (verifyLogoisDisplayed()) {
			sendData(inputData);
			navigateToCinema(inputData);
			movieInfo.put("ReleaseDate", getReleaseDate());
			movieInfo.put("Country", getCountry());
			System.out.println("Wiki - Release Date: " + movieInfo.get("ReleaseDate") + " and Country: " + movieInfo.get("Country"));
			return movieInfo;
		}
		return Collections.emptyMap();
	}

	public Map<String, String> searchMovies() {
		movieInfo.put("ReleaseDate", getReleaseDate());
		movieInfo.put("Country", getCountry());
		System.out.println("IMDb - Release Date: " + movieInfo.get("ReleaseDate") + " and Country: " + movieInfo.get("Country"));
		return movieInfo;
	}

	public MovieDetails searchMoviesDetails(String inputData) {
		MovieDetails movieDetails = new MovieDetails();
		movieDetails.setMovie(inputData);
		movieDetails.setReleaseDate(getReleaseDate());
		movieDetails.setCountry(getCountry());
		System.out.println("IMDb - " + movieDetails.toString());
		return movieDetails;
	}

}
