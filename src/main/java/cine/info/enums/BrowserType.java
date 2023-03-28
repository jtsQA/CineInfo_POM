package cine.info.enums;

public enum BrowserType {
	CHROME("chrome"), FIREFOX("firefox"), EDGE("edge");

	private String browser;

	private BrowserType(final String browser) {
		this.browser = browser;
	}

	public String getBrowser() {
		return browser;
	}

}
