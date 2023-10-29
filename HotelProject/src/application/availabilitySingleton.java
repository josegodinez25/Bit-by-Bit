package application;

import java.time.LocalDate;

public class availabilitySingleton {

	private static final availabilitySingleton instance = new availabilitySingleton();

	private String availabilityString;
	private LocalDate searchCheckIn;
	private LocalDate searchCheckOut;

	private availabilitySingleton() {
	}

	public static availabilitySingleton getInstance() {
		return instance;
	}

	public String getAvailabilityString() {
		return availabilityString;
	}

	public LocalDate getSearchCheckIn() {
		return searchCheckIn;
	}

	public LocalDate getSearchCheckOut() {
		return searchCheckOut;
	}

	public void setAvailabilityString(String availabilityString) {
		this.availabilityString = availabilityString;
	}

	public void setSearchCheckIn(LocalDate searchCheckIn) {
		this.searchCheckIn = searchCheckIn;
	}

	public void setSearchCheckOut(LocalDate searchCheckOut) {
		this.searchCheckOut = searchCheckOut;
	}

}