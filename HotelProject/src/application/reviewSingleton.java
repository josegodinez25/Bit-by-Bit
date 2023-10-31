package application;

import java.time.LocalDate;

public class reviewSingleton {

	private static final reviewSingleton instance = new reviewSingleton();

	private String reviewFirstName;
	private String reviewLastName;

	private reviewSingleton() {
	}

	public static reviewSingleton getInstance() {
		return instance;
	}

	public String getReviewFirstName() {
		return reviewFirstName;
	}

	public String getReviewLastName() {
		return reviewLastName;
	}

	public void setReviewFirstName(String reviewFirstName) {
		this.reviewFirstName = reviewFirstName;

	}

	public void setReviewlastName(String reviewLastName) {
		this.reviewLastName = reviewLastName;
	}

}