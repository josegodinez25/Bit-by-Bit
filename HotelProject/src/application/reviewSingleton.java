package application;

import java.time.LocalDate;

public class reviewSingleton {

	private static final reviewSingleton instance = new reviewSingleton();

	private String reviewFirstName;
	private String reviewLastName;
	private String ID;
	
	private reviewSingleton() {
	}

	public static reviewSingleton getInstance() {
		return instance;
	}

	
	//create all the getters and setters for each customers info
	
	public String getReviewFirstName() {
		return reviewFirstName;
	}

	public String getReviewLastName() {
		return reviewLastName;
	}

	public String getID() {
		return ID;
	}
	
	public void setReviewFirstName(String reviewFirstName) {
		this.reviewFirstName = reviewFirstName;

	}

	public void setReviewlastName(String reviewLastName) {
		this.reviewLastName = reviewLastName;
	}

	public void setID(String ID) {
		this.ID = ID;
	}
	
	
}