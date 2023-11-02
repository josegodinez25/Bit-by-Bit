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
	reviewCCnumber, reviewCCexperation, reviewCCcountry, reviewCCzip, reviewCCsecurity;

	//crate labels for each customers information
	
	private String ID;
	
	
	
	@Override
	public void initialize(URL url, ResourceBundle resourcebundle) {	
		Reserve res = new Reserve();
		ID = review.getID();
		
	//code will look like this but we need the ID the user types in saved into a string on this class	
		res.findCustomerID(ID);
		reviewFirstName.setText(res.findCustomerID(ID).firstName);
		reviewLastName.setText(res.findCustomerID(ID).lastName);
		
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