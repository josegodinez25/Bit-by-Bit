package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ReservationPageControllerClass {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
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
	public void test(ActionEvent event) throws IOException {
	
	}
}
