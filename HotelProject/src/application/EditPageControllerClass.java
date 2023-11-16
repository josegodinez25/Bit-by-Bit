package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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

public class EditPageControllerClass implements Initializable {
	reviewSingleton review = reviewSingleton.getInstance();
	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML
	private TextField editFirstName, editLastName, editEmail, editPhone, editCCfirstName, editCClastName, editCCnumber,
			editCCexperationMonth,editCCexperationYear, editCCcountry, editCCzip, editCCsecurity;
	@FXML
	private Label editReservationNumber;
	@FXML
	private DatePicker editPageCheckIn, editPageCheckOut;
	@FXML
	private RadioButton editPageSingle, editPageDouble, editPageKing, editPageSuite;
	@FXML
	private Label emailError, phoneError, cardError, dateError, zipError, securityError;

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
	private String ID;

	@Override
	public void initialize(URL url, ResourceBundle resourcebundle) {

		Reserve res = new Reserve();

		ID = review.getID();
		if (res.checkID(ID) == true) {
			editReservationNumber.setText(ID);
			editFirstName.setText(res.findCustomerID(ID).firstName);
			editLastName.setText(res.findCustomerID(ID).lastName);
			editEmail.setText(res.findCustomerID(ID).email);
			editPhone.setText(res.findCustomerID(ID).phoneNumber);

			// I need to edit this to fit a radio button
			// reviewRoomType.setText(res.findCustomerID(ID).roomType);
			
			editCCfirstName.setText(res.findCustomerID(ID).firstName);
			editCClastName.setText(res.findCustomerID(ID).lastName);
			// I need to edit this to fit a datpicker object
			// reviewCheckIn.setText(res.findCustomerID(ID).checkIn);
			// I need to edit this to fit a datpicker object
			// reviewCheckOut.setText(res.findCustomerID(ID).checkOut);
			editCCcountry.setText(res.findCustomerID(ID).country);

			editCCnumber.setText(res.findCustomerID(ID).cardNumber);
			editCCzip.setText(res.findCustomerID(ID).zipCode);
			//editCCexperationMonth.setText(res.findCustomerID(ID).expDate);
			//editCCexperationYear.setText(res.findCustomerID(ID).expDate);
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
			//figure out how to make the month and year separate
			// editCCexperation.setText(obj.ReadExcel("Customers", CustomerCount, 8));
			// editCCexperation.setText(obj.ReadExcel("Customers", CustomerCount, 8));
			editCCcountry.setText(obj.ReadExcel("Customers", CustomerCount, 9));
			editCCzip.setText(obj.ReadExcel("Customers", CustomerCount, 10));
			editReservationNumber.setText(obj.ReadExcel("Customers", CustomerCount, 11));
			// reviewCheckIn.setText(obj.ReadExcel("Customers", CustomerCount, 14));
			// reviewCheckOut.setText(obj.ReadExcel("Customers", CustomerCount, 15));
			// make a size in excel
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
	
	public void setNewChanges() {
		//roomType();
		
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
		
		//reservationRoomType = editRoomType.getText();
		//change to get a date value
		//checkOut = reservationPageCheckOut.getText();
		//checkIn = reservationPageCheckIn.getText();
		
		expCombined = reservationCardExpMonth + "/" + reservationCardExpYear;
	}

	@FXML
	public void confirmChanges(ActionEvent event) throws IOException {
		setNewChanges();
		Reserve reserve = new Reserve();
		Customer customer = new Customer(reservationFirstName, reservationLastName, reservationEmail, reservationPhoneNumber, reservationCardFirstName, 
				reservationCardLastName, reservationCardPaymentNumber,expCombined, reservationCardZipcode, reservationCardCountry);
		customer.ID = ID;
		reserve.changeReservation(ID, customer);
		// returns back to main page
		root = FXMLLoader.load(getClass().getResource("mainPage.FXML"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	public void switchToMainScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("mainPage.FXML"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}