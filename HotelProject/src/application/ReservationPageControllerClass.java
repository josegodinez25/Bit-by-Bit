package application;

import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ReservationPageControllerClass {
	private Stage stage;
	private Scene scene;
	private Parent root;
	LocalDate reservationCheckIn;
	LocalDate reservationCheckOut;
	
	
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
	
	
	//The user input from the reservation page will be saved to these variables
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
	
	
	@FXML
	public void switchToMainScene(ActionEvent event) throws IOException {
		 root = FXMLLoader.load(getClass().getResource("mainPage.FXML"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
	}
	@FXML
	public void switchToSearchScene(ActionEvent event) throws IOException {
			root = FXMLLoader.load(getClass().getResource("searchPage.FXML"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	}	
	
	@FXML
	public void setRoomType(ActionEvent event) {
		if(reservationPageSingle.isSelected()) {
			reservationRoomType = "Single";
		}
		else if(reservationPageDouble.isSelected()) {
			reservationRoomType = "Double";
		}
        else if(reservationPageKing.isSelected()) {
        	reservationRoomType = "King";
		}
        else if(reservationPageSuite.isSelected()) {
        	reservationRoomType = "Suite";
        }
		//test to check if this method works by printing to console
		//System.out.println(reservationRoomType);
	}
	
	@FXML
	public void setReservationCheckIn(ActionEvent event) {
		reservationCheckIn = reservationPageCheckIn.getValue();
		//test to check if this method works by printing to console
		System.out.println(reservationCheckIn.toString());
	}
	@FXML
	public void setReservationCheckOut(ActionEvent event) {
		reservationCheckOut = reservationPageCheckOut.getValue();
		//test to check if this method works by printing to console
		System.out.println(reservationCheckOut.toString());
	}
	
	@FXML
	public void setCustomerReservationInformation(ActionEvent event) throws IOException {
		reservationFirstName = firstNameTextField.getText();
		reservationLastName = lastNameTextField.getText();
		reservationEmail = emailTextField.getText();
		reservationPhoneNumber = phoneTextField.getText();
		reservationCardFirstName = firstNameCardTextField.getText();
		reservationCardLastName = lastNameCardTextField.getText();
		try {
		reservationCardPaymentNumber = Integer.parseInt(paymentCardNumberTextField.getText());
		}
		catch(NumberFormatException e) {
			cardNumberError.setText("Please enter a valid card number");
			return;
		}
		try {
		reservationCardExpMonth = Integer.parseInt(expMonthCardTextField.getText());
	}
	catch(NumberFormatException e) {
		cardDateError.setText("Please enter a valid date");
		return;
	}
		try {
		reservationCardExpYear = Integer.parseInt(expYearCardTextField.getText());
		}
		catch(NumberFormatException e) {
			cardDateError.setText("Please enter a valid date");
			return;
		}
		reservationCardCountry = countryCardTextField.getText();
		try {
			reservationCardZipcode = Integer.parseInt(zipcodeCardTextField.getText());
		}
		catch(NumberFormatException e) {
			cardZipError.setText("Please enter a valid zipcode");
			return;
		}
		//some test here to make sure this button works, just prints the user input into 
		//System.out.println(reservationFirstName);
		//System.out.println(reservationLastName);
		//System.out.println(reservationEmail);
		//System.out.println(reservationPhoneNumber);
		//System.out.println(reservationCardFirstName);
		//System.out.println(reservationCardLastName);
		//System.out.println(reservationCardPaymentNumber);
		//System.out.println(reservationCardExpMonth);
		//System.out.println(reservationCardExpYear);
		//System.out.println(reservationCardCountry);
		//System.out.println(reservationCardZipcode);
		
		//there should be some sort of call here to a function in the customer class so the information can be stored on the excel file
		//add reservationRoomType reservationCheckIn and reservationCheckOut
		Reserve res = new Reserve();
		Room rom  = new Room ("101","single","open","115");
		Customer cus = new Customer(reservationFirstName,reservationLastName,reservationEmail,reservationPhoneNumber,reservationCardFirstName,reservationCardLastName,
                reservationCardPaymentNumber,reservationCardExpMonth,reservationCardZipcode,reservationCardCountry);
		res.reserveRoom(rom, cus);
		//Temporarily returns the user to the main screen on success until we make a reservation review scene
		root = FXMLLoader.load(getClass().getResource("mainPage.FXML"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
	}
}
