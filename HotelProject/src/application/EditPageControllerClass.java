package application;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EditPageControllerClass implements Initializable {
	reviewSingleton review = reviewSingleton.getInstance();
	private Stage stage;
	private Scene scene;
	private Parent root,root2;
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
			
			String[] split = res.findCustomerID(ID).expDate.split("/");
			editCCexperationMonth.setText(split[0]);
			editCCexperationYear.setText(split[1]);
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
			String[] split = obj.ReadExcel("Customers", CustomerCount, 8).split("/");
			editCCexperationMonth.setText(split[0]);
			editCCexperationYear.setText(split[1]);
			editCCcountry.setText(obj.ReadExcel("Customers", CustomerCount, 9));
			editCCzip.setText(obj.ReadExcel("Customers", CustomerCount, 10));
			editCCsecurity.setText(obj.ReadExcel("Customers", CustomerCount, 11));
			editReservationNumber.setText(obj.ReadExcel("Customers", CustomerCount, 12));
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
			dateIn = LocalDate.parse((obj.ReadExcel("Customers", CustomerCount, 16)), formatter);
			dateOut = LocalDate.parse((obj.ReadExcel("Customers", CustomerCount, 17)), formatter);
			editPageCheckIn.setValue(dateIn);
			editPageCheckOut.setValue(dateOut);
		}
	}

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

	public void getDatesBetween(LocalDate startDate, LocalDate endDate) {
		totalDates.clear();
		while (!startDate.isAfter(endDate)) {
			totalDates.add(startDate);
			startDate = startDate.plusDays(1);
		}
	}

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
		ID = editReservationNumber.getText();
		checkIn = dateIn.toString();
		checkOut = dateOut.toString(); 
		expCombined = reservationCardExpMonth + "/" + reservationCardExpYear;
	}

	@FXML
	public void confirmChanges(ActionEvent event) throws IOException {
		setNewChanges();
		Reserve reserve = new Reserve();
		Customer customer = new Customer(reservationFirstName, reservationLastName, reservationEmail,
				reservationPhoneNumber, reservationCardFirstName, reservationCardLastName, reservationCardPaymentNumber,
				expCombined, reservationCardZipcode, reservationCardCountry, reservationCardSecurity);
		Room room = new Room(reservationRoomType, checkIn, checkOut, totalDates);
		customer.ID = ID;
		customer.roomType = reservationRoomType;
		customer.checkIn = checkIn;
		customer.checkOut = checkOut;

		if (reserve.isBooked(room) == false) {
			room.updateRoom(room);
			customer.roomNumber = room.number;
			customer.roomPrice = room.price;
			reserve.changeReservation(ID, customer, room);
			
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
			
			
			//Stage stage = (Stage) exitButton.getScene().getWindow();
			//stage.close();
		} else {
			dateError.setText("Dates are not available");
		}

	}

	@FXML
	public void switchToMainScene(ActionEvent event) throws IOException {
		Stage stage = (Stage) exitButton.getScene().getWindow();
		stage.close();
	}

}