package application;
/**
 * The purpose of this singleton class is to manage a reservation ID when a user navigates between scenes
 * The reservation ID is used when generating the review, edit, and cancel reservation scenes
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
	 */
	public static reviewSingleton getInstance() {
		return instance;
	}
	
	/**
	 * This method returns the customers first name for this instance.
	 */
	public String getReviewFirstName() {
		return reviewFirstName;
	}
	
	/**
	 * This method returns the customers last name for this instance.
	 */
	public String getReviewLastName() {
		return reviewLastName;
	}

	/**
	 * This method returns the reservation ID for this instance.
	 */
	public String getID() {
		return ID;
	}
	
	/**
	 * This method sets the customers first name for this instance.
	 */
	public void setReviewFirstName(String reviewFirstName) {
		this.reviewFirstName = reviewFirstName;

	}

	/**
	 * This method sets the customers last name for this instance.
	 */
	public void setReviewlastName(String reviewLastName) {
		this.reviewLastName = reviewLastName;
	}

	/**
	 * This method sets the reservation ID for this instance.
	 */
	public void setID(String ID) {
		this.ID = ID;
	}
	
	
}