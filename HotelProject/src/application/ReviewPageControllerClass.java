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


public class ReviewPageControllerClass implements Initializable{
	reviewSingleton review = reviewSingleton.getInstance();
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	private Label reviewFirstName, reviewLastName, reviewEmail, reviewPhone, reviewRoomType, 
	reviewRoomNumber,reviewCheckIn, reviewCheckOut, reviewCCfirstName, reviewCClastName, 
	reviewCCnumber, reviewCCexperation, reviewCCcountry, reviewCCzip, reviewCCsecurity, reviewReservationNumber;

	
	private String ID;
	
	
	
	@Override
	public void initialize(URL url, ResourceBundle resourcebundle) {	
		Reserve res = new Reserve();
		ID = review.getID();
		
		res.findCustomerID(ID);
		reviewReservationNumber.setText(ID);
		reviewFirstName.setText(res.findCustomerID(ID).firstName);
		reviewLastName.setText(res.findCustomerID(ID).lastName);
		reviewEmail.setText(res.findCustomerID(ID).email);
		reviewPhone.setText(res.findCustomerID(ID).phoneNumber);		
		//we are going to have to change the excel sheet for room type since its not on there
		//reviewRoomType.setText(res.findCustomerID(ID).phoneNumber);
		reviewRoomNumber.setText(res.findCustomerID(ID).roomNumber);
		
		
		//when we create labels for these then remove comments
		
		//reviewCheckIn.setText(res.findCustomerID(ID).checkIn);
		//reviewCheckOut.setText(res.findCustomerID(ID).checkOut);
		//reviewCCcountry.setText(res.findCustomerID(ID).country);
		//reviewCCzip.setText(res.findCustomerID(ID).zipCode);
		//reviewCCsecurity.setText(res.findCustomerID(ID).expDate);
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