package application;

import java.time.LocalDate;
/**
 * The purpose of this singleton class is to create an instance for passing information from one scene to another
 * The user will set the room type and check-in check-out dates in the search page
 * The reservation page will get the room type and check-in check-out dates and initialize based on this information
 */
public class availabilitySingleton {

	private static final availabilitySingleton instance = new availabilitySingleton();

	private String availabilityString;
	private LocalDate searchCheckIn;
	private LocalDate searchCheckOut;

	private availabilitySingleton() {
	}

	/**
	 * This method returns this instance
	 */
	public static availabilitySingleton getInstance() {
		return instance;
	}

	/**
	 * This method returns the room type for this instance.
	 */
	public String getAvailabilityString() {
		return availabilityString;
	}

	/**
	 * This method returns a check-in date for this instance.
	 */
	public LocalDate getSearchCheckIn() {
		return searchCheckIn;
	}

	/**
	 * This method returns a check-out date for this instance.
	 */
	public LocalDate getSearchCheckOut() {
		return searchCheckOut;
	}

	/**
	 * This method sets the room type for this instance.
	 */
	public void setAvailabilityString(String availabilityString) {
		this.availabilityString = availabilityString;
	}

	/**
	 * This method sets a check-in date for this instance.
	 */
	public void setSearchCheckIn(LocalDate searchCheckIn) {
		this.searchCheckIn = searchCheckIn;
	}

	/**
	 * This method sets a check-out date for this instance.
	 */
	public void setSearchCheckOut(LocalDate searchCheckOut) {
		this.searchCheckOut = searchCheckOut;
	}

}