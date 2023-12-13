package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CancelPageControllerClass implements Initializable {
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	private Button exitButton;

	reviewSingleton review = reviewSingleton.getInstance();
	String ID;

	@FXML
	Label reservationID;

	@Override
	public void initialize(URL url, ResourceBundle resourcebundle) {
		ReadWriteExcel obj = new ReadWriteExcel();
		if (review.getID() == null) {
			int CustomerCount = 1;
			// checks if the cell is empty
			while (obj.isNull(CustomerCount, 1, 0) == false) {
				CustomerCount++;
			}
			CustomerCount--;
			ID = obj.ReadExcel("Customers", CustomerCount, 12);
		} else {
			ID = review.getID();
		}

		reservationID.setText(ID);
	}

	public void confirmCancel(ActionEvent event) throws IOException {
		Reserve res = new Reserve();
		res.clearCustomerInfo(ID);

		// returns back to main page
		Stage stage = (Stage) exitButton.getScene().getWindow();
		stage.close();
	}
	/**
	 * This method is an action that is taken when the user presses the main button in the cancel page.
	 * It will close the cancel page and open the main scene
	 * @throws IOException
	 */
	@FXML
	public void switchToMainScene(ActionEvent event) throws IOException {
		Stage stage = (Stage) exitButton.getScene().getWindow();
		stage.close();
	}

}
