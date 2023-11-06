package application;

import java.io.IOException;
import java.time.ZoneId;
import java.util.Date;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.lang.String;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ReservationPageControllerClass implements Initializable {
	private Stage stage;
	private Scene scene;
	private Parent root;
	LocalDate reservationCheckIn;
	LocalDate reservationCheckOut;
	private long longReservationCheckIn;
	private long longReservationCheckOut;
	List<LocalDate> reservationTotalDates = new ArrayList<>();
	availabilitySingleton search = availabilitySingleton.getInstance();
	reviewSingleton review = reviewSingleton.getInstance();
	@FXML
	private TextField firstNameTextField;
	@FXML
	private TextField lastNameTextField;
	@FXML
	private TextField emailTextField;
	@FXML
	private TextField phoneTextField;
	@FXML
	private TextField firstNameCardTextField;
	@FXML
	private TextField lastNameCardTextField;
	@FXML
	private TextField paymentCardNumberTextField;
	@FXML
	private TextField expMonthCardTextField;
	@FXML
	private TextField expYearCardTextField;
	@FXML
	private TextField countryCardTextField;
	@FXML
	private TextField zipcodeCardTextField;
	@FXML
	private Button confirmTransactionButton;
	@FXML
	private Label cardNumberError;
	@FXML
	private Label cardDateError;
	@FXML
	private Label cardZipError;
	@FXML
	private Label totalCost;
	@FXML
	private RadioButton reservationPageSingle, reservationPageDouble, reservationPageKing, reservationPageSuite;
	@FXML
	private DatePicker reservationPageCheckIn, reservationPageCheckOut;
	String reservationFirstName;
	String reservationLastName;
	String reservationEmail;
	String reservationPhoneNumber;
	String reservationCardFirstName;
	String reservationCardLastName;
	String reservationCardPaymentNumber;
	String reservationCardExpMonth;
	String reservationCardExpYear;
	String reservationCardCountry;
	String reservationCardZipcode;
	String reservationRoomType;
	String checkOut;
	String checkIn;
	String expCombined;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		if (search.getAvailabilityString() == "Single") {
			reservationPageSingle.setSelected(true);
		} else if (search.getAvailabilityString() == "Double") {
			reservationPageDouble.setSelected(true);
		} else if (search.getAvailabilityString() == "King") {
			reservationPageKing.setSelected(true);
		} else if (search.getAvailabilityString() == "Suite") {
			reservationPageSuite.setSelected(true);
		}
		reservationPageCheckIn.setValue(search.getSearchCheckIn());
		reservationPageCheckOut.setValue(search.getSearchCheckOut());
	}

	@FXML
	public void switchToMainScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("mainPage.FXML"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	public void switchToSearchScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("searchPage.FXML"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	public void setRoomType(ActionEvent event) {
		if (reservationPageSingle.isSelected()) {
			reservationRoomType = "Single";
		} else if (reservationPageDouble.isSelected()) {
			reservationRoomType = "Double";
		} else if (reservationPageKing.isSelected()) {
			reservationRoomType = "King";
		} else if (reservationPageSuite.isSelected()) {
			reservationRoomType = "Suite";
		}
		// test to check if this method works by printing to console
		// System.out.println(reservationRoomType);
	}

	// need to give error an confirming reservation when incorrect date is picked
	// so it does not move on to next scene
	@FXML
	public void setReservationCheckIn(ActionEvent event) {
		LocalDate today = LocalDate.now();
		LocalDate selectedDate = reservationPageCheckIn.getValue();

		if (selectedDate != null && !selectedDate.isBefore(today)) {
			reservationCheckIn = selectedDate;
			if (reservationCheckOut != null) {
				reservationGetDatesBetween(reservationCheckIn, reservationCheckOut);
				totalCost.setText("The total cost of your stay is " + reservationTotalDates.size()*200);
			}
			checkIn = reservationCheckIn.toString();
		} else {
			// Need error for when date is not available or valid

		}
	}

	// same error for this part where it needs an error pop up on GUI program where
	// it does not allow to move to next scene
	@FXML
	public void setReservationCheckOut(ActionEvent event) {
		LocalDate today = LocalDate.now();
		LocalDate selectedDate = reservationPageCheckOut.getValue();

		if (selectedDate != null && !selectedDate.isBefore(today)) {
			reservationCheckOut = selectedDate;
			if (reservationCheckIn != null) {
				reservationGetDatesBetween(reservationCheckIn, reservationCheckOut);
				totalCost.setText("The total cost of your stay is " + reservationTotalDates.size()*200);
			}
			checkOut = reservationCheckOut.toString();
		} else {
			// Need error for when date is not available or valid
		}
	}

	public void receiveRoomFromSearch() {

	}

	// function finds the dates in between check in and check out and saves them to
	// a
	public void reservationGetDatesBetween(LocalDate startDate, LocalDate endDate) {
		reservationTotalDates.clear();
		while (!startDate.isAfter(endDate)) {
			reservationTotalDates.add(startDate);
			startDate = startDate.plusDays(1);
		}
		// small test that print the elements of the list
		// System.out.println(Arrays.toString(reservationTotalDates.toArray()));
	}

	public void getCustomerInfo() {
		reservationFirstName = firstNameTextField.getText();
		reservationLastName = lastNameTextField.getText();
		reservationEmail = emailTextField.getText();
		reservationPhoneNumber = phoneTextField.getText();
		reservationCardFirstName = firstNameCardTextField.getText();
		reservationCardLastName = lastNameCardTextField.getText();
		reservationCardPaymentNumber = paymentCardNumberTextField.getText();
		reservationCardExpMonth = expMonthCardTextField.getText();
		reservationCardExpYear = expYearCardTextField.getText();
		reservationCardCountry = countryCardTextField.getText();
		reservationCardZipcode = zipcodeCardTextField.getText();
		expCombined = reservationCardExpMonth + "/" + reservationCardExpYear;
	}

//	public void checkForErrors() {
//		try {
//			reservationCardPaymentNumber = paymentCardNumberTextField.getText();
//		} catch (NumberFormatException e) {
//			cardNumberError.setText("Please enter a valid card number");
//
//		}
//		try {
//			reservationCardExpMonth = expMonthCardTextField.getText();
//		} catch (NumberFormatException e) {
//			cardDateError.setText("Please enter a valid date");
//
//		}
//
//		try {
//			reservationCardExpYear = expYearCardTextField.getText();
//			int currentYear = LocalDate.now().getYear();
//
//			if (reservationCardExpYear < currentYear) {
//				cardDateError.setText("Please enter a valid expiration year");
//			}
//		} catch (NumberFormatException e) {
//			cardDateError.setText("Please enter a valid year");
//		}
//		reservationCardCountry = countryCardTextField.getText();
//		try {
//			reservationCardZipcode = zipcodeCardTextField.getText();
//		} catch (NumberFormatException e) {
//			cardZipError.setText("Please enter a valid zipcode");
//		}
//		expCombined = reservationCardExpMonth + "/" + reservationCardExpYear;
//	}

	@FXML
	public void setCustomerReservationInformation(ActionEvent event) throws IOException {
		getCustomerInfo();
		//checkForErrors();
		// there should be some sort of call here to a function in the customer class so
		// the information can be stored on the excel file
	
		Reserve res = new Reserve();
		Room rom = new Room(reservationRoomType, checkIn, checkOut, reservationTotalDates);
		Customer cus = new Customer(reservationFirstName, reservationLastName, reservationEmail, reservationPhoneNumber,
				reservationCardFirstName, reservationCardLastName, reservationCardPaymentNumber,
				expCombined, reservationCardZipcode, reservationCardCountry);

		res.reserveRoom(rom, cus);

		// these next two lines are temporary for testing they set the reservation name
		// and last name for the review page
		review.setReviewFirstName(reservationFirstName);
		review.setReviewlastName(reservationLastName);

		root = FXMLLoader.load(getClass().getResource("reviewPage.FXML"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}