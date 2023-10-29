package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class MainPageControllerClass {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	public void switchToSearchScene(ActionEvent event) throws IOException {
			root = FXMLLoader.load(getClass().getResource("searchPage.FXML"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	}
	
	@FXML
	public void switchToReservationScene(ActionEvent event) throws IOException {
			root = FXMLLoader.load(getClass().getResource("reservationPage.FXML"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	}
	
	@FXML
	public void openReservationNumberPage(ActionEvent event)throws IOException {
		FXMLLoader FXMLLoader = new FXMLLoader(getClass().getResource("reservationNumberPage.FXML"));
		Parent root = (Parent) FXMLLoader.load();
		Stage stage = new Stage();
		stage.setTitle("Reservation Confirmation");
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	@FXML
	public void switchToReviewPage(ActionEvent event) throws IOException {
		FXMLLoader FXMLLoader = new FXMLLoader(getClass().getResource("reviewPage.FXML"));
		Parent root = (Parent) FXMLLoader.load();
		Stage stage = new Stage();
		stage.setTitle("Reservation Confirmation");
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	
}
