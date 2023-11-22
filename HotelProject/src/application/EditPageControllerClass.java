package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
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
/**
 * This class controls the functionality of the edit page in the application.
 * It handles user interactions to modify existing reservations, displaying and allowing alterations
 * to reservation details such as guest information, room type, dates, and payment details.
 * The class initializes the edit page, retrieves existing reservation data, allows modifications, 
 * and confirms changes upon user interaction.
 * 
 * The controller interacts with FXML elements to display and update reservation information,
 * providing methods to set and confirm changes made by the user.
 * 
 * This class integrates with the Reserve, Customer, Room, and ReadWriteExcel classes 
 * to manage reservation data and perform necessary operations for editing reservations.
 * 
 * The class uses JavaFX elements for the user interface and employs various listeners 
 * to capture user inputs and update the displayed information accordingly.
 * 
 * @author Nathan
 * @author Eric
 * @author Arash
 * @author Jose
 * @version 1.0
 * @since 1.0
 */
public class EditPageControllerClass implements Initializable {
	reviewSingleton review = reviewSingleton.getInstance();
	private Stage stage;
	private Scene scene;
	private Parent root;
	private LocalDate dateIn, dateOut;
	List<LocalDate> totalDates = new ArrayList<>();
	@FXML
	private TextField editFirstName, editLastName, editEmail, editPhone, editCCfirstName, editCClastName, editCCnumber,
			editCCexperationMonth, editCCexperationYear, editCCcountry, editCCzip, editCCsecurity;
	@FXML
	private Label editReservationNumber;
	@FXML
	private DatePicker editPageCheckIn, editPageCheckOut;
	@FXML
	private RadioButton editPageSingle, editPageDouble, editPageKing, editPageSuite;
	@FXML
	private Label emailError, phoneError, cardError, dateError, zipError, securityError;
	@FXML
	private Button exitButton;

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
	String reservationCardSecurity;
	String reservationRoomType;
	String checkOut;
	String checkIn;
	String expCombined;
	private String ID;
	String editRoomType;
	
	
	/**
     * Initializes the controller class, retrieves existing reservation data, and populates the edit page.
     * If a reservation ID is available, the method fetches and displays the corresponding reservation details 
     * for modification. If not, it fetches data from an external source (Excel file) to populate the fields 
     * for editing.
     * 
     * @param url the location used to resolve relative paths for the root object, or null if the location is not known
     * @param resourceBundle the resources used to localize the root object, or null if the root object was not localized
     */
	@Override
	public void initialize(URL url, ResourceBundle resourcebundle) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Reserve res = new Reserve();

		ID = review.getID();
		if (res.checkID(ID) == true) {
			editReservationNumber.setText(ID);
			editFirstName.setText(res.findCustomerID(ID).firstName);
			editLastName.setText(res.findCustomerID(ID).lastName);
			editEmail.setText(res.findCustomerID(ID).email);
			editPhone.setText(res.findCustomerID(ID).phoneNumber);
			editRoomType = (res.findCustomerID(ID).roomType);

			if (editRoomType.equals("Single") == true) {
				editPageSingle.setSelected(true);
			} else if (editRoomType.equals("Double") == true) {
				editPageDouble.setSelected(true);
			} else if (editRoomType.equals("King") == true) {
				editPageKing.setSelected(true);
			} else if (editRoomType.equals("Suite") == true) {
				editPageSuite.setSelected(true);
			}

			editCCfirstName.setText(res.findCustomerID(ID).paymentFirstName);
			editCClastName.setText(res.findCustomerID(ID).paymentLastName);

			dateIn = LocalDate.parse((res.findCustomerID(ID).checkIn), formatter);
			dateOut = LocalDate.parse((res.findCustomerID(ID).checkOut), formatter);
			editPageCheckIn.setValue(dateIn);
			editPageCheckOut.setValue(dateOut);

			editCCcountry.setText(res.findCustomerID(ID).country);
			editCCnumber.setText(res.findCustomerID(ID).cardNumber);
			editCCzip.setText(res.findCustomerID(ID).zipCode);
			editCCsecurity.setText(res.findCustomerID(ID).secCode);
			editCCexperationMonth.setText(res.findCustomerID(ID).expDate);
			editCCexperationYear.setText(res.findCustomerID(ID).expDate);
		} else {
			ReadWriteExcel obj = new ReadWriteExcel();
			int CustomerCount = 1;
			while (obj.isNull(CustomerCount, 1, 0) == false) {
				CustomerCount++;
			}
			CustomerCount--;

			editFirstName.setText(obj.ReadExcel("Customers", CustomerCount, 1));
			editLastName.setText(obj.ReadExcel("Customers", CustomerCount, 2));
			editEmail.setText(obj.ReadExcel("Customers", CustomerCount, 3));
			editPhone.setText(obj.ReadExcel("Customers", CustomerCount, 4));
			editCCfirstName.setText(obj.ReadExcel("Customers", CustomerCount, 5));
			editCClastName.setText(obj.ReadExcel("Customers", CustomerCount, 6));
			editCCnumber.setText(obj.ReadExcel("Customers", CustomerCount, 7));
			// figure out how to make the month and year separate
			editCCexperationMonth.setText(obj.ReadExcel("Customers", CustomerCount, 8));
			editCCexperationYear.setText(obj.ReadExcel("Customers", CustomerCount, 8));
			editCCcountry.setText(obj.ReadExcel("Customers", CustomerCount, 9));
			editCCzip.setText(obj.ReadExcel("Customers", CustomerCount, 10));
			editCCsecurity.setText(obj.ReadExcel("Customers", CustomerCount, 11));
			editReservationNumber.setText(obj.ReadExcel("Customers", CustomerCount, 12));
			// skip room number
			editRoomType = (obj.ReadExcel("Customers", CustomerCount, 14));
			if (editRoomType.equals("Single") == true) {
				editPageSingle.setSelected(true);
			} else if (editRoomType.equals("Double") == true) {
				editPageDouble.setSelected(true);
			} else if (editRoomType.equals("King") == true) {
				editPageKing.setSelected(true);
			} else if (editRoomType.equals("Suite") == true) {
				editPageSuite.setSelected(true);
			}
			// skips price
			dateIn = LocalDate.parse((obj.ReadExcel("Customers", CustomerCount, 16)), formatter);
			dateOut = LocalDate.parse((obj.ReadExcel("Customers", CustomerCount, 17)), formatter);
			editPageCheckIn.setValue(dateIn);
			editPageCheckOut.setValue(dateOut);
		}
	}
	 /**
     * Determines the room type selected by the user and assigns it to the reservation.
     */
	public void roomType() {
		if (editPageSingle.isSelected()) {
			reservationRoomType = "Single";
		} else if (editPageDouble.isSelected()) {
			reservationRoomType = "Double";
		} else if (editPageKing.isSelected()) {
			reservationRoomType = "King";
		} else if (editPageSuite.isSelected()) {
			reservationRoomType = "Suite";
		}
	}
	
	/**
     * Sets the check-in date for the reservation based on the selected date by the user.
     * 
     * @param event the action event triggered by the user
     */
	@FXML
	public void setReservationCheckIn(ActionEvent event) {
		LocalDate today = LocalDate.now();
		LocalDate selectedDate = editPageCheckIn.getValue();

		if (selectedDate != null && !selectedDate.isBefore(today)) {
			dateIn = selectedDate;
			if (editPageCheckOut != null) {
				getDatesBetween(dateIn, dateOut);
			}
			checkIn = dateIn.toString();
		}
	}
	
	/**
     * Sets the check-out date for the reservation based on the selected date by the user.
     * 
     * @param event the action event triggered by the user
     */
	@FXML
	public void setReservationCheckOut(ActionEvent event) {
		LocalDate today = LocalDate.now();
		LocalDate selectedDate = editPageCheckOut.getValue();

		if (selectedDate != null && !selectedDate.isBefore(today)) {
			dateOut = selectedDate;
			if (editPageCheckIn != null) {
				getDatesBetween(dateIn, dateOut);
			}
			checkOut = dateOut.toString();
		}
	}
	
	/**
     * Generates a list of dates between the start and end date for the reservation.
     * 
     * @param startDate the start date of the reservation
     * @param endDate the end date of the reservation
     */
	public void getDatesBetween(LocalDate startDate, LocalDate endDate) {
		totalDates.clear();
		while (!startDate.isAfter(endDate)) {
			totalDates.add(startDate);
			startDate = startDate.plusDays(1);
		}
	}
	
	/**
     * Gathers and stores the updated reservation details entered by the user.
     */
	public void setNewChanges() {
		roomType();

		reservationFirstName = editFirstName.getText();
		reservationLastName = editLastName.getText();
		reservationEmail = editEmail.getText();
		reservationPhoneNumber = editPhone.getText();
		reservationCardFirstName = editCCfirstName.getText();
		reservationCardLastName = editCClastName.getText();
		reservationCardPaymentNumber = editCCnumber.getText();
		reservationCardExpMonth = editCCexperationMonth.getText();
		reservationCardExpYear = editCCexperationYear.getText();
		reservationCardCountry = editCCcountry.getText();
		reservationCardZipcode = editCCzip.getText();
		reservationCardSecurity = editCCsecurity.getText();
		expCombined = reservationCardExpMonth + "/" + reservationCardExpYear;
	}
	
	/**
     * Confirms the changes made by the user, updates the reservation, and closes the edit page.
     * 
     * @param event the action event triggered by the user
     * @throws IOException if an I/O exception occurs
     */
	@FXML
	public void confirmChanges(ActionEvent event) throws IOException {
		setNewChanges();
		Reserve reserve = new Reserve();
		Customer customer = new Customer(reservationFirstName, reservationLastName, reservationEmail,
				reservationPhoneNumber, reservationCardFirstName, reservationCardLastName, reservationCardPaymentNumber,
				expCombined, reservationCardZipcode, reservationCardCountry, reservationCardSecurity);
		customer.ID = ID;
		customer.checkIn = checkIn;
		customer.checkOut = checkOut;
		customer.roomType = reservationRoomType;
		// will change later
		customer.roomNumber = "1";
		customer.roomPrice = "1";

		Room room = new Room(reservationRoomType, checkIn, checkOut, totalDates);

		reserve.changeReservation(ID, customer, room);
		// returns back to main page
		Stage stage = (Stage) exitButton.getScene().getWindow();
		stage.close();
	}
	
	/**
     * Closes the edit page and switches back to the main scene.
     * 
     * @param event the action event triggered by the user
     * @throws IOException if an I/O exception occurs
     */
	@FXML
	public void switchToMainScene(ActionEvent event) throws IOException {
		Stage stage = (Stage) exitButton.getScene().getWindow();
		stage.close();
	}

}