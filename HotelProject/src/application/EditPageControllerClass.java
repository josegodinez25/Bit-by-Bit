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
	private TextField editFirstName, editLastName, editEmail, editPhone, editCCfirstName, editCClastName, editCCnumber, editCCexperation,
			editCCcountry, editCCzip, editCCsecurity, editReservationNumber;
	@FXML
	private DatePicker reservationPageCheckIn, reservationPageCheckOut;
	@FXML
	private RadioButton reservationPageSingle, reservationPageDouble, reservationPageKing, reservationPageSuite;
	
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
		/**
		 * Reserve res = new Reserve();
		 
		ID = review.getID();

		if (res.checkID(ID) == true) {		
			editReservationNumber.setText(ID);
			editFirstName.setText(res.findCustomerID(ID).firstName);
			editLastName.setText(res.findCustomerID(ID).lastName);
			editEmail.setText(res.findCustomerID(ID).email);
			editPhone.setText(res.findCustomerID(ID).phoneNumber);
			
			// I need to edit this to fit a radio button reviewRoomType.setText(res.findCustomerID(ID).phoneNumber);
			// reviewRoomNumber.setText(res.findCustomerID(ID).roomNumber);
			editCCfirstName.setText(res.findCustomerID(ID).firstName);
			editCClastName.setText(res.findCustomerID(ID).lastName);
			// I need to edit this to fit a datpicker object reviewCheckIn.setText(res.findCustomerID(ID).checkIn);
			// I need to edit this to fit a datpicker objectreviewCheckOut.setText(res.findCustomerID(ID).checkOut);
			editCCcountry.setText(res.findCustomerID(ID).country);
		
				//these three need to be converted to Strings
		//	reviewCCnumber.setText(res.findCustomerID(ID).cardNumber);
		//	reviewCCzip.setText(res.findCustomerID(ID).zipCode);
		//	reviewCCexperation.setText(res.findCustomerID(ID).expDate);
		} else {
			ReadWriteExcel obj = new ReadWriteExcel();
	        int CustomerCount = 1;
	        while (obj.isCustomerNull(CustomerCount, 1) == false) {
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
			editCCexperation.setText(obj.ReadExcel("Customers", CustomerCount, 8));
			editCCcountry.setText(obj.ReadExcel("Customers", CustomerCount, 9));
			editCCzip.setText(obj.ReadExcel("Customers", CustomerCount, 10));
			editReservationNumber.setText(obj.ReadExcel("Customers", CustomerCount, 11));
			//reviewRoomNumber.setText(obj.ReadExcel("Customers", CustomerCount, 12));
			//reviewPrice.
			//reviewCheckIn.setText(obj.ReadExcel("Customers", CustomerCount, 14));
			//reviewCheckOut.setText(obj.ReadExcel("Customers", CustomerCount, 15));
		}**/
		
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