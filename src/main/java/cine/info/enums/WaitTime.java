package cine.info.enums;

public enum WaitTime {
	PAGE_LOAD_TIME(60000), EXPLICIT_WAIT(30000);

	private int time;

	private WaitTime(int time) {
		this.time = time;
	}

	public int getTime() {
		return time;
	}
}
