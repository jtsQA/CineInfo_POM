package cine.info.model;

public class MovieDetails {

	private String movie;
	private String releaseDate;
	private String country;

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getMovie() {
		return movie;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}

	@Override
	public String toString() {
		return String.format("MovieDetails [movie=%s, releaseDate=%s, country=%s]", movie, releaseDate, country);
	}

	// @Override
	// public String toString() {
	// return String.format("ReleaseDate: %s, and Country: %s", releaseDate, country);
	// }

	// @Override
	// public String toString() {
	// return String.format("{\n \"MovieDetails\": {\n \"releaseDate\": \"%s\",\n \"country\": \"%s\"\n }\n}", releaseDate, country);
	// }

	// @Override
	// public String toString() {
	// return String.format("{\n \"MovieDetails\": {\n \"releaseDate\": \"%s\",\n country\": \"%s\n }\n}", releaseDate, country);
	// }

	// @Override
	// public String toString() {
	// return String.format("\"{\n \\\"class_name\\\": \\\"MovieDetails\\\",\n \\\"members\\\": {\n \\\"releaseDate\\\": \\\"%s\\\",\n country\\\":
	// \\\"%s\n }\n}\"\n", releaseDate, country);
	// }

}
