package application; 
/* Package declaration for organizational purposes. */

/* Import statements for JavaFX, IO functionalities, and date manipulation. */
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

/* 
SearchPageControllerClass for handling room search functionalities in a hotel reservation system.
*/
public class SearchPageControllerClass {
	/* Singleton instance for availability management. */
	availabilitySingleton search = availabilitySingleton.getInstance();
	
	/* Private fields for managing the JavaFX stage and scenes. */
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	/* Fields to store room type and dates for search. */
	String searchPageRoomType;
	LocalDate searchCheckIn;
	LocalDate searchCheckOut;
	List<LocalDate> searchTotalDates = new ArrayList<>();
	
	/* FXML annotated fields for UI elements. */
	@FXML 
	private RadioButton searchPageSingle, searchPageDouble, searchPageKing, searchPageSuite;
	@FXML
	private DatePicker searchPageCheckIn, searchPageCheckOut;
	@FXML
	private Label roomAvailabilityMessage;
	
	/* Temporary fields to store date values as strings. */
	String checkIn;
	String checkOut;
	
	/* Method to generate a list of dates between two given dates. */
	public void searchGetDatesBetween(LocalDate startDate, LocalDate endDate) {
		searchTotalDates.clear();
		while (!startDate.isAfter(endDate)) {
			searchTotalDates.add(startDate);
			startDate = startDate.plusDays(1);
		}
	}
	/* Method to set the room type based on radio button selection. */
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
	 /* Methods to set check-in and check-out dates from date pickers. */
	@FXML
	public void setSearchCheckIn(ActionEvent event) {
		searchCheckIn = searchPageCheckIn.getValue();
		checkIn = searchCheckIn.toString();
		//test to check if this method works by printing to console
		//System.out.println(searchCheckIn.toString());
	}
	@FXML
	public void setSearchCheckOut(ActionEvent event) {
		searchCheckOut = searchPageCheckOut.getValue();
		checkOut = searchCheckOut.toString();
		//test to check if this method works by printing to console
		//System.out.println(searchCheckOut.toString());
	}
	/* Method to check room availability based on search criteria. */
	@FXML
	public void searchAvailability(ActionEvent event) throws IOException {
		searchGetDatesBetween(searchCheckIn, searchCheckOut);
		Reserve res = new Reserve();
		Room room = new Room(searchPageRoomType,checkIn,checkOut,searchTotalDates);
		
		if(res.isBooked(room) == true) {
			roomAvailabilityMessage.setText("I'm sorry we don't have any rooms of this type available for the chosen dates");
		}else {
			roomAvailabilityMessage.setText("This room is available click here to reserve this room!");
		}
	}
	/* Method to switch back to the main scene. */
	@FXML
	public void switchToMainScene(ActionEvent event) throws IOException {	
	
		 root = FXMLLoader.load(getClass().getResource("mainPage.FXML"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 stage.setScene(scene);
		 stage.show();
	}
	/* Method to switch to the reservation scene, passing along search criteria. */
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