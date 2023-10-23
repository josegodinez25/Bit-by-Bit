package application;

import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

public class SearchPageControllerClass {
	private Stage stage;
	private Scene scene;
	private Parent root;
	String searchPageRoomType;
	LocalDate searchCheckIn;
	LocalDate searchCheckOut;
	@FXML 
	private RadioButton searchPageSingle, searchPageDouble, searchPageKing, searchPageSuite;
	@FXML
	private DatePicker searchPageCheckIn, searchPageCheckOut;
	
	
	@FXML
	public void setSearchRoomType(ActionEvent event) {
		if(searchPageSingle.isSelected()) {
			searchPageRoomType = "Single";
		}
		else if(searchPageDouble.isSelected()) {
			searchPageRoomType = "Double";
		}
        else if(searchPageKing.isSelected()) {
        	searchPageRoomType = "King";
		}
        else if(searchPageSuite.isSelected()) {
        	searchPageRoomType = "Suite";
        }
		//test to check if this method works by printing to console
		//System.out.println(reservationRoomType);
	}
	@FXML
	public void setSearchCheckIn(ActionEvent event) {
		searchCheckIn = searchPageCheckIn.getValue();
		//test to check if this method works by printing to console
		//System.out.println(searchCheckIn.toString());
	}
	@FXML
	public void setSearchCheckOut(ActionEvent event) {
		searchCheckOut = searchPageCheckOut.getValue();
		//test to check if this method works by printing to console
		//System.out.println(searchCheckOut.toString());
	}
	
	@FXML
	public void searchAvailability(ActionEvent event) throws IOException {
		 
	}
	
	@FXML
	public void switchToMainScene(ActionEvent event) throws IOException {
		 root = FXMLLoader.load(getClass().getResource("mainPage.FXML"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
	}
	
	@FXML
	public void switchToReservationScene(ActionEvent event) throws IOException {
		if(searchPageSingle.isSelected()) {
		
		}
		else if(searchPageDouble.isSelected()) {
			
		}
        else if(searchPageKing.isSelected()) {
        	
		}
        else if(searchPageSuite.isSelected()) {
        	
        }
			root = FXMLLoader.load(getClass().getResource("reservationPage.FXML"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	}
	
}