package application;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

public class SearchPageControllerClass {
	availabilitySingleton search = availabilitySingleton.getInstance();
	private Stage stage;
	private Scene scene;
	private Parent root;
	String searchPageRoomType;
	LocalDate searchCheckIn;
	LocalDate searchCheckOut;
	List<LocalDate> searchTotalDates = new ArrayList<>();
	@FXML 
	private RadioButton searchPageSingle, searchPageDouble, searchPageKing, searchPageSuite;
	@FXML
	private DatePicker searchPageCheckIn, searchPageCheckOut;
	@FXML
	private Label roomAvailabilityMessage;
	
	public void searchGetDatesBetween(LocalDate startDate, LocalDate endDate) {
		searchTotalDates.clear();
		while (!startDate.isAfter(endDate)) {
			searchTotalDates.add(startDate);
			startDate = startDate.plusDays(1);
		}
	}
	
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
		searchGetDatesBetween(searchCheckIn, searchCheckOut);
		roomAvailabilityMessage.setText("I'm sorry we don't have any rooms of this type available for the chosen dates");	 
		roomAvailabilityMessage.setText("This room is available click here to reserve this room!");
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
		    search.setAvailabilityString(searchPageRoomType);
		    search.setSearchCheckIn(searchCheckIn);
		    search.setSearchCheckOut(searchCheckOut);
			root = FXMLLoader.load(getClass().getResource("reservationPage.FXML"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	}
	
}