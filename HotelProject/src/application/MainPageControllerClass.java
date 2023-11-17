package application;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MainPageControllerClass  extends Application {
	reviewSingleton review = reviewSingleton.getInstance();
	private Stage stage;
	private Scene scene;
	private Parent root, root2;
	String reservationID;
	@FXML
	private TextField reservationIDtextField;
	@FXML
	private Label reservationIDerror;
	@FXML
	private Button closeButton, confirmButton, reviewButton;
	
	@Override
	public void start(Stage stage) throws Exception {
		// Group root = new Group();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("mainPage.FXML"));
			stage = new Stage();
			Image topIcon = new Image("topLeftLogo.png");
			Scene mainScreenScene = new Scene(root);
			stage.getIcons().add(topIcon);
			stage.setTitle("Asylum Hotel Reservation System");
			stage.setScene(mainScreenScene);
			stage.show();
			stage.setResizable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		root2 = (Parent) FXMLLoader.load();
		Stage stage2 = new Stage();
		stage2.setResizable(false);
		stage2.initOwner(stage);
		stage2.initModality(Modality.APPLICATION_MODAL);
		stage2.setScene(new Scene(root2));
		stage2.showAndWait();
	}
	
	@FXML
	public void closeSceneAction(ActionEvent event)throws IOException {
		Stage stage2 = (Stage) closeButton.getScene().getWindow();
		stage2.close();
	}
	
	
	
	@FXML
	public void switchToReviewPage(ActionEvent event) throws IOException {
		Reserve res = new Reserve();
		reservationID = reservationIDtextField.getText();
		
		// if the id the user puts in exists then switches to the review scene
		if(res.checkID(reservationID) == true) {
			Stage stage2 = (Stage) closeButton.getScene().getWindow();
			stage2.close();
			review.setID(reservationID);
			root = FXMLLoader.load(getClass().getResource("reviewPage.FXML"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}else {
			reservationIDerror.setText("Please enter a valid reservation number");
		}
		
	}

}
