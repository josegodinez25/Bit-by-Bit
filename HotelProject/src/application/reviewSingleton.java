package application;

import java.time.LocalDate;

/**
 * The purpose of this singleton class is to manage a reservation ID when a user navigates between scenes.
 * The reservation ID is used when generating the review, edit, and cancel reservation scenes.
 * The reservation ID is set in the reservation number page or in the review page depending on how the user is accessing the review, edit, and cancel reservation pages.
 * @author Eric
 * @version 11-21-23
 */
public class reviewSingleton {

	private static final reviewSingleton instance = new reviewSingleton();

	private String reviewFirstName;
	private String reviewLastName;
	
	private String ID;
	
	private reviewSingleton() {
	}
	
	/**
	 * This method returns this instance
	 * @return
	 */
	public static reviewSingleton getInstance() {
		return instance;
	}

	
	//create all the getters and setters for each customers info
	
	/**
	 * This method returns the customers first name for this instance.
	 * @return
	 */
	public String getReviewFirstName() {
		return reviewFirstName;
	}
	/**
	 * This method returns the customers last name for this instance.
	 * @return
	 */
	public String getReviewLastName() {
		return reviewLastName;
	}
	/**
	 * This method returns the reservation ID for this instance.
	 * @return
	 */
	public String getID() {
		return ID;
	}
	/**
	 * This method sets the customers first name for this instance.
	 * @param reviewFirstName
	 */
	public void setReviewFirstName(String reviewFirstName) {
		this.reviewFirstName = reviewFirstName;

	}
	/**
	 * This method sets the customers last name for this instance.
	 * @param reviewLastName
	 */
	public void setReviewlastName(String reviewLastName) {
		this.reviewLastName = reviewLastName;
	}
	/**
	 * This method sets the reservation ID for this instance.
	 * @param ID used to set the reservation ID
	 */
	public void setID(String ID) {
		this.ID = ID;
	}
	
	
}