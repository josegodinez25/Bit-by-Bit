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
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * The ReservationPageControllerClass manages the reservation page UI functionality.
 * @author Eric
 * @author Nathan
 * @author Arash
 * @author Jose
 * @version 1.0
 * @since 1.0
 */
public class ReservationPageControllerClass implements Initializable {
	
	// Stage and Scene variables
	private Stage stage;
	private Scene scene;
	private Parent root, root2;
	
	// Reservation related variables
	LocalDate reservationCheckIn;
	LocalDate reservationCheckOut;
	private long longReservationCheckIn;
	private long longReservationCheckOut;
	List<LocalDate> reservationTotalDates = new ArrayList<>();
	availabilitySingleton search = availabilitySingleton.getInstance();
	reviewSingleton review = reviewSingleton.getInstance();
	
	// FXML elements
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
	private TextField securityCode;
	@FXML
	private Button confirmTransactionButton;
	@FXML
	private Label cardNumberError, cardDateError, cardZipError, emailError, phoneError, securityError, countryError,
			dateError, lastNameError, firstNameError, CCfirstNameError, CClastNameError, roomTypeError, roomDateError;
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
	int price;
	String stringPrice;
	String reservationSecurityCode;

	 /**
     * Initializes the controller after its root element has been completely processed.
     *
     * @param url            The location used to resolve relative paths for the root object.
     * @param resourceBundle The resources used to localize the root object.
     */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		// Use .equals() for string comparison
		String availabilityString = search.getAvailabilityString();
		if ("Single".equals(availabilityString)) {
			reservationPageSingle.setSelected(true);
			reservationRoomType = "Single";
			price = 115;
		} else if ("Double".equals(availabilityString)) {
			reservationPageDouble.setSelected(true);
			reservationRoomType = "Double";
			price = 160;
		} else if ("King".equals(availabilityString)) {
			reservationPageKing.setSelected(true);
			reservationRoomType = "King";
			price = 224;
		} else if ("Suite".equals(availabilityString)) {
			reservationPageSuite.setSelected(true);
			reservationRoomType = "Suite";
			price = 1000;
		}

		if (search.getSearchCheckIn() != null && search.getSearchCheckOut() != null) {
			reservationPageCheckIn.setValue(search.getSearchCheckIn());
			reservationPageCheckOut.setValue(search.getSearchCheckOut());
			reservationGetDatesBetween(search.getSearchCheckIn(),search.getSearchCheckOut());
			totalCost.setText("The total cost of your stay is $" + (reservationTotalDates.size() - 1) * price);
			
		}
	}

	// ... [Methods like switchToMainScene, switchToSearchScene remain unchanged]
	
		/**
	     * Switches to the main scene.
	     *
	     * @param event The ActionEvent triggering the switch.
	     * @throws IOException If an I/O error occurs.
	     */
	@FXML
	public void switchToMainScene(ActionEvent event) throws IOException {

		search.setSearchCheckIn(null);
		search.setSearchCheckOut(null);
		search.setAvailabilityString(null);

		root = FXMLLoader.load(getClass().getResource("mainPage.FXML"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	/**
     * Switches to the search scene.
     *
     * @param event The ActionEvent triggering the switch.
     * @throws IOException If an I/O error occurs.
     */
	@FXML
	public void switchToSearchScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("searchPage.FXML"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	/**
     * Sets the selected room type.
     *
     * @param event The ActionEvent triggering the room type selection.
     */
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
	
	/**
     * Sets the check-in date for the reservation.
     *
     * @param event The ActionEvent triggering the check-in date selection.
     */
	@FXML
	public void setReservationCheckIn(ActionEvent event) {
		LocalDate today = LocalDate.now();
		LocalDate selectedDate = reservationPageCheckIn.getValue();

		if (selectedDate != null && !selectedDate.isBefore(today)) {
			reservationCheckIn = selectedDate;
			if (reservationCheckOut != null) {
				reservationGetDatesBetween(reservationCheckIn, reservationCheckOut);
				if (reservationRoomType.equals("Single")) {
					totalCost.setText(
							"The total cost of your stay is " + (reservationTotalDates.size() - 1) * 115 + "$");
					price = (reservationTotalDates.size() - 1) * 115;
				} else if (reservationRoomType.equals("Double")) {
					totalCost.setText(
							"The total cost of your stay is " + (reservationTotalDates.size() - 1) * 160 + "$");
					price = (reservationTotalDates.size() - 1) * 160;
				} else if (reservationRoomType.equals("King")) {
					totalCost.setText(
							"The total cost of your stay is " + (reservationTotalDates.size() - 1) * 224 + "$");
					price = (reservationTotalDates.size() - 1) * 224;
				} else if (reservationRoomType.equals("Suite")) {
					totalCost.setText(
							"The total cost of your stay is " + (reservationTotalDates.size() - 1) * 1000 + "$");
					price = (reservationTotalDates.size() - 1) * 1000;
				}
			}
			checkIn = reservationCheckIn.toString();
		} else {
			// Display an error message for invalid dates
			// Example: setting a text to a label
			// You can customize this part as per your UI design
			totalCost.setText("Selected check-in date is not valid.");
		}
	}

	// same error for this part where it needs an error pop up on GUI program where
	// it does not allow to move to next scene
	
	/**
     * Sets the check-out date for the reservation.
     *
     * @param event The ActionEvent triggering the check-out date selection.
     */
	@FXML
	public void setReservationCheckOut(ActionEvent event) {
		LocalDate today = LocalDate.now();
		LocalDate selectedDate = reservationPageCheckOut.getValue();

		if (selectedDate != null && !selectedDate.isBefore(today)) {
			reservationCheckOut = selectedDate;
			if (reservationCheckIn != null) {
				reservationGetDatesBetween(reservationCheckIn, reservationCheckOut);
				if (reservationRoomType.equals("Single")) {
					totalCost.setText(
							"The total cost of your stay is " + (reservationTotalDates.size() - 1) * 115 + "$");
					price = (reservationTotalDates.size() - 1) * 115;
				} else if (reservationRoomType.equals("Double")) {
					totalCost.setText(
							"The total cost of your stay is " + (reservationTotalDates.size() - 1) * 160 + "$");
					price = (reservationTotalDates.size() - 1) * 160;
				} else if (reservationRoomType.equals("King")) {
					totalCost.setText(
							"The total cost of your stay is " + (reservationTotalDates.size() - 1) * 224 + "$");
					price = (reservationTotalDates.size() - 1) * 224;
				} else if (reservationRoomType.equals("Suite")) {
					totalCost.setText(
							"The total cost of your stay is " + (reservationTotalDates.size() - 1) * 1000 + "$");
					price = (reservationTotalDates.size() - 1) * 1000;
				}
			}
			checkOut = reservationCheckOut.toString();
		} else {
			totalCost.setText("Selected check-out date is not valid.");
		}
	}
	/**
	* Receives room information from the search.
	*/
	public void receiveRoomFromSearch() {

	}

	// function finds the dates in between check in and check out and saves them to
	// a
	
	/**
	 * Generates a list of dates between the start and end dates for reservation purposes.
	 *
	 * @param startDate The starting date of the reservation.
	 * @param endDate   The ending date of the reservation.
	 */
	public void reservationGetDatesBetween(LocalDate startDate, LocalDate endDate) {
		reservationTotalDates.clear();
		while (!startDate.isAfter(endDate)) {
			reservationTotalDates.add(startDate);
			startDate = startDate.plusDays(1);
		}
		// small test that print the elements of the list
		// System.out.println(Arrays.toString(reservationTotalDates.toArray()));
	}
	
	/**
	 * Retrieves customer information from the input fields in the UI.
	 * Information includes name, email, phone number, payment details, and address.
	 */
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
		reservationSecurityCode = securityCode.getText();
		checkIn = reservationPageCheckIn.getValue().toString();
		checkOut = reservationPageCheckOut.getValue().toString();
		expCombined = reservationCardExpMonth + "/" + reservationCardExpYear;
		stringPrice = Integer.toString(price);
	}
	/**
     * Validates customer information for errors.
     *
     * @return True if any errors were found, otherwise false.
     */
	public boolean checkForErrors() {
		boolean hasErrors = false;

		// Reset error messages
		cardNumberError.setText("");
		cardDateError.setText("");
		cardZipError.setText("");
		firstNameError.setText("");
		lastNameError.setText("");
		CCfirstNameError.setText("");
		CClastNameError.setText("");
		phoneError.setText("");
		securityError.setText("");
		countryError.setText("");
		roomDateError.setText("");
		emailError.setText("");

		// Validate Card Number
		if (!isValidCardNumber(paymentCardNumberTextField.getText())) {
			cardNumberError.setText("Invalid card number. Must be 16 digits.");
			hasErrors = true;
		}

		// Validate Expiration Date
		if (!isValidExpirationDate(expMonthCardTextField.getText(), expYearCardTextField.getText())) {
			cardDateError.setText("Invalid expiration date.");
			hasErrors = true;
		}

		// Validate Zip Code
		if (!isValidZipCode(zipcodeCardTextField.getText())) {
			cardZipError.setText("Invalid ZIP code. Must be 5 digits.");
			hasErrors = true;
		}
		// Validate Zip Code
		if (!isFirstNameValid(firstNameTextField.getText())) {
			firstNameError.setText("Invalid First Name");
			hasErrors = true;
		}
		// Validate First Name
		if (!isLastNameValid(lastNameTextField.getText())) {
			lastNameError.setText("Invalid Last Name");
			hasErrors = true;
		}
		// Validate Customer Card First Name
		if (!isCCfirstNameValid(firstNameCardTextField.getText())) {
			CCfirstNameError.setText("Invalid Card First Name");
			hasErrors = true;
		}
		// Validate Customer Card Last Name
		if (!isCClastNameValid(lastNameCardTextField.getText())) {
			CClastNameError.setText("Invalid Card Last Name");
			hasErrors = true;
		}
		// Validate Phone Number
		if (!isPhoneValid(phoneTextField.getText())) {
			phoneError.setText("Invalid Phone Number");
			hasErrors = true;
		}
		// Validate Credit Card Security Code
		if (!isSecurityValid(securityCode.getText())) {
			securityError.setText("Invalid Security Code");
			hasErrors = true;
		}
		// Validate Country
		if (!isCountryValid(countryCardTextField.getText())) {
			countryError.setText("Invalid Country");
			hasErrors = true;
		}
		// Validate Reservation Page Check In
		if (reservationPageCheckIn.getValue() == null || reservationPageCheckIn.getValue() == null) {
			roomDateError.setText("Invalid Dates");
			hasErrors = true;
		}
		// Validate Room Size
		if (!reservationPageSingle.isSelected() && !reservationPageDouble.isSelected()
				&& !reservationPageKing.isSelected() && !reservationPageSuite.isSelected()) {
			roomTypeError.setText("Invalid Room Type");
			hasErrors = true;
		}
		// Validate Email
		if (!isEmailValid(emailTextField.getText())) {
			emailError.setText("Invalid Email");
			hasErrors = true;
		}

		// Additional Validations (e.g., for names, phone numbers) can be added here

		// Return true if any errors were found
		return hasErrors;
	}

	private boolean isFirstNameValid(String firstName) {
		// Makes it so only accepts letters
		if (firstName == "" || !firstName.matches("[a-zA-Z]+")) {
			return false;
		}
		return true;
	}

	private boolean isLastNameValid(String lastName) {
		// Makes it so only accepts letters
		if (lastName == "" || !lastName.matches("[a-zA-Z]+")) {
			return false;
		}
		return true;
	}

	private boolean isCCfirstNameValid(String CCfirstName) {
		// Makes it so only accepts letters
		if (CCfirstName == "" || !CCfirstName.matches("[a-zA-Z]+")) {
			return false;
		}
		return true;
	}

	private boolean isCClastNameValid(String CClastName) {
		// Makes it so only accepts letters
		if (CClastName == "" || !CClastName.matches("[a-zA-Z]+")) {
			return false;
		}
		return true;
	}

	private boolean isPhoneValid(String phone) {
		// Makes it phone has to numbers only and the length is either 15, 7, or 10
		// Can add different length to be accepted
		if (phone == "" || !phone.matches("[0-9]+")
				|| !((phone.length() == 15) || (phone.length() == 7) || (phone.length() == 10))) {
			return false;
		}
		return true;
	}

	private boolean isCountryValid(String country) {
		// Makes it so it only accepts letters
		if (country == "" || !country.matches("[a-zA-Z]+")) {
			return false;
		}
		return true;
	}

	private boolean isSecurityValid(String sec) {
		// Makes it so it only accepts numbers & the length of the security code is
		// either 3 or 4
		if (sec == "" || !sec.matches("[0-9]+") || !((sec.length() == 3) || (sec.length() == 4))) {
			return false;
		}
		return true;
	}

	private boolean isEmailValid(String email) {
		if (email == null || email.isEmpty() || email == "") {
			return false;
		}
		// Valid Email Domains
		String[] validDomains = { "@yahoo.com", "@gmail.com", "@my.csun.edu", "@outlook.com", "@hotmail.com" };

		for (String domain : validDomains) {
			if (email.endsWith(domain)) {
				return true; // Email ends with a valid domain
			}
		}

		return false; // Email does not match any valid domain
	}
	/**
     * Validates the card number format.
     *
     * @param cardNumber The card number to be validated.
     * @return True if the card number is valid, otherwise false.
     */
	private boolean isValidCardNumber(String cardNumber) {
		// Regular expression to check for 15 or 16 digit numbers
		String regex = "^[0-9]{15,16}$";
		return cardNumber.matches(regex);
	}
	/**
     * Validates the expiration date of the card.
     *
     * @param month The month of the expiration date.
     * @param year  The year of the expiration date.
     * @return True if the expiration date is valid, otherwise false.
     */
	private boolean isValidExpirationDate(String month, String year) {
		// Check if month and year are in the correct format
		String monthRegex = "^(0[1-9]|1[0-2])$"; // Month should be 01-12
		String yearRegex = "^[0-9]{2}$"; // Year should be two digits

		if (!month.matches(monthRegex) || !year.matches(yearRegex)) {
			return false;
		}

		try {
			// Convert to full year (assuming 2000s)
			int fullYear = 2000 + Integer.parseInt(year);
			int expMonth = Integer.parseInt(month);

			// Check if the date is in the past
			LocalDate currentDate = LocalDate.now();
			LocalDate expDate = LocalDate.of(fullYear, expMonth, 1);

			return !expDate.isBefore(currentDate.withDayOfMonth(1));
		} catch (NumberFormatException e) {
			return false;
		}
	}
	/**
     * Validates the ZIP code format.
     *
     * @param zipCode The ZIP code to be validated.
     * @return True if the ZIP code is valid, otherwise false.
     */
	private boolean isValidZipCode(String zipCode) {
		String regex = "^[0-9]{5}$"; // Example regex for a 5-digit US zipcode
		return zipCode.matches(regex);
	}
	
	/**
     * Sets the customer reservation information and proceeds with the reservation process.
     *
     * @param event The ActionEvent triggering the process.
     * @throws IOException If an I/O error occurs.
     */
	@FXML
	public void setCustomerReservationInformation(ActionEvent event) throws IOException {
		// getCustomerInfo();

		if (checkForErrors()) {
			// If errors are found, stop processing and show error messages
			return;
		}
		getCustomerInfo();
		// Proceed with the reservation process if no errors are found
		Reserve res = new Reserve();
		Room rom = new Room(reservationRoomType, checkIn, checkOut, reservationTotalDates);
		rom.price = stringPrice;
		Customer cus = new Customer(reservationFirstName, reservationLastName, reservationEmail, reservationPhoneNumber,
				reservationCardFirstName, reservationCardLastName, reservationCardPaymentNumber, expCombined,
				reservationCardZipcode, reservationCardCountry, reservationSecurityCode);

		if (res.isBooked(rom) == false) {
			res.reserveRoom(rom, cus);
			// Switch to the review page
			root = FXMLLoader.load(getClass().getResource("mainPage.FXML"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			FXMLLoader FXMLLoader = new FXMLLoader(getClass().getResource("reviewPage.FXML"));
			root2 = (Parent) FXMLLoader.load();
			Stage stage2 = new Stage();
			stage2.setResizable(false);
			stage2.initOwner(stage);
			stage2.initModality(Modality.APPLICATION_MODAL);
			stage2.setScene(new Scene(root2));
			stage2.showAndWait();
		} else {
			dateError.setText("Dates are not available");
		}
	}
}