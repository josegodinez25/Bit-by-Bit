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
import java.util.HashMap;

public class MainPageControllerClass  extends Application {
	reviewSingleton review = reviewSingleton.getInstance();
	availabilitySingleton search = availabilitySingleton.getInstance();
	private Stage stage;
	private Scene scene;
	private Parent root, root2, root3, root4;
	String loginUserString, loginPasswordString;
	String reservationID;
	@FXML
	private TextField reservationIDtextField, loginUser, loginPassword;
	@FXML
	private Label reservationIDerror, incorrectLoginLable;
	@FXML
	private Button closeButton, confirmButton, reviewButton, adminLoginButton, exitSupportButton;
	
	@Override
	public void start(Stage stage) throws Exception {
		// Group root = new Group();
		try {
			review.setID(null);
			
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
	public void openSupport(ActionEvent event)throws IOException {
		FXMLLoader FXMLLoader = new FXMLLoader(getClass().getResource("supportPage.FXML"));
		root4 = (Parent) FXMLLoader.load();
		Stage stage4 = new Stage();
		stage4.setResizable(false);
		stage4.initOwner(stage);
		stage4.initModality(Modality.APPLICATION_MODAL);
		stage4.setScene(new Scene(root4));
		stage4.showAndWait();
	}
	
	@FXML
	public void exitSupport(ActionEvent event)throws IOException {
		Stage stage4 = (Stage) exitSupportButton.getScene().getWindow();
		stage4.close();
	}
	
	@FXML
	public void adminLogin(ActionEvent event)throws IOException {
		FXMLLoader FXMLLoader = new FXMLLoader(getClass().getResource("adminLoginPage.FXML"));
		root3 = (Parent) FXMLLoader.load();
		Stage stage3 = new Stage();
		stage3.setResizable(false);
		stage3.initOwner(stage);
		stage3.initModality(Modality.APPLICATION_MODAL);
		stage3.setScene(new Scene(root3));
		stage3.showAndWait();
	}
	
	@FXML
	public void adminLoginButton(ActionEvent event)throws IOException {
		loginUserString = loginUser.getText();
		loginPasswordString= loginPassword.getText();
		IDandPasswords loginCheck = new IDandPasswords();
		if(loginCheck.getLoginInfo().containsKey(loginUserString)) {
		Stage stage3 = (Stage) adminLoginButton.getScene().getWindow();
		stage3.close();
		root = FXMLLoader.load(getClass().getResource("reportPage.FXML"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		}
		else {
		incorrectLoginLable.setText("Incorrect login information");
		}
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
