package application;

import java.io.IOException;
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
	private RadioButton reservationPageSingle, reservationPageDouble, reservationPageKing, reservationPageSuite;
	@FXML
	private DatePicker reservationPageCheckIn, reservationPageCheckOut;
	String reservationFirstName;
	String reservationLastName;
	String reservationEmail;
	String reservationPhoneNumber;
	String reservationCardFirstName;
	String reservationCardLastName;
	int reservationCardPaymentNumber;
	int reservationCardExpMonth;
	int reservationCardExpYear;
	String reservationCardCountry;
	int reservationCardZipcode;
	String reservationRoomType;
	String checkOut;
	String checkIn;
	String expCombined;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		if(search.getAvailabilityString()== "Single") {
			reservationPageSingle.setSelected(true);
		}
		else if(search.getAvailabilityString()== "Double") {
			reservationPageDouble.setSelected(true);
		}
		else if(search.getAvailabilityString()== "King") {
			reservationPageKing.setSelected(true);
}
		else if(search.getAvailabilityString()== "Suite") {
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

	@FXML
	public void setReservationCheckIn(ActionEvent event) {
		reservationCheckIn = reservationPageCheckIn.getValue();
		if (reservationCheckOut != null && reservationCheckIn != null) {
			reservationGetDatesBetween(reservationCheckIn, reservationCheckOut);
		}
		checkIn = reservationCheckIn.toString();
		// longReservationCheckIn = reservationCheckIn.toEpochDay();
		// test to check if this method works by printing to console
		// System.out.println(longReservationCheckIn);
	}

	@FXML
	public void setReservationCheckOut(ActionEvent event) {
		reservationCheckOut = reservationPageCheckOut.getValue();
		if (reservationCheckOut != null && reservationCheckIn != null) {
			reservationGetDatesBetween(reservationCheckIn, reservationCheckOut);
		}
		checkOut = reservationCheckOut.toString();
		// longReservationCheckOut = reservationCheckOut.toEpochDay();
		// test to check if this method works by printing to console
		// System.out.println(longReservationCheckOut);
	}

	// function receives information from the search page and sets the room type and
	// date to the radio buttons and date picker of the scene
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
	}
	
	public void checkForErrors() {
		try {
			reservationCardPaymentNumber = Integer.parseInt(paymentCardNumberTextField.getText());
		} catch (NumberFormatException e) {
			cardNumberError.setText("Please enter a valid card number");
			return;
		}
		try {
			reservationCardExpMonth = Integer.parseInt(expMonthCardTextField.getText());
		} catch (NumberFormatException e) {
			cardDateError.setText("Please enter a valid date");
			return;
		}
		try {
			reservationCardExpYear = Integer.parseInt(expYearCardTextField.getText());
		} catch (NumberFormatException e) {
			cardDateError.setText("Please enter a valid date");
			return;
		}
		reservationCardCountry = countryCardTextField.getText();
		try {
			reservationCardZipcode = Integer.parseInt(zipcodeCardTextField.getText());
		} catch (NumberFormatException e) {
			cardZipError.setText("Please enter a valid zipcode");
			return;
		}
		expCombined = Integer.toString(reservationCardExpMonth) + Integer.toString(reservationCardExpYear);
	}
	
	@FXML
	public void setCustomerReservationInformation(ActionEvent event) throws IOException {
		getCustomerInfo();
		checkForErrors();
		// there should be some sort of call here to a function in the customer class so
		// the information can be stored on the excel file
		Reserve res = new Reserve();
		Room rom = new Room(reservationRoomType, checkIn, checkOut,reservationTotalDates);
		Customer cus = new Customer(reservationFirstName, reservationLastName, reservationEmail, reservationPhoneNumber,
				reservationCardFirstName, reservationCardLastName, reservationCardPaymentNumber,
				reservationCardExpMonth, reservationCardZipcode, reservationCardCountry);

		res.reserveRoom(rom, cus);
		
		// these next two lines are temporary for testing they set the reservation name and last name for the review page
		review.setReviewFirstName(reservationFirstName);
		review.setReviewlastName(reservationLastName);
		
		root = FXMLLoader.load(getClass().getResource("reviewPage.FXML"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}