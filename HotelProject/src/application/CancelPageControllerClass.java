package application; /* Package declaration for organizational purposes. */

/* Import statements for JavaFX and IO functionalities. */
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

/* 
   CancelPageControllerClass implementing Initializable for JavaFX application.
   This class is responsible for handling the cancellation process in a hotel reservation system.
*/
public class CancelPageControllerClass implements Initializable {
    /* Private fields for managing the JavaFX stage and scenes. */
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private Button exitButton; /* FXML annotated exit button for UI interaction. */

    /* Singleton instance for review management. */
    reviewSingleton review = reviewSingleton.getInstance();
    String ID; /* Variable to store the reservation ID. */

    @FXML
    Label reservationID; /* FXML annotated label to display the reservation ID in the UI. */

    /* 
       initialize method to set up initial state of the controller.
       This method is called after all @FXML annotated fields have been injected.
    */
    @Override
    public void initialize(URL url, ResourceBundle resourcebundle) {
        ID = review.getID(); /* Retrieving the reservation ID from the review singleton. */
        reservationID.setText(ID); /* Setting the reservation ID on the UI label. */
    }

    /* 
       confirmCancel method to handle the reservation cancellation logic.
       Invoked when the user confirms the cancellation of a reservation.
    */
    public void confirmCancel(ActionEvent event) throws IOException {
        Reserve res = new Reserve(); /* Creating an instance of the Reserve class. */
        res.clearCustomerInfo(ID); /* Calling method to clear customer information based on the ID. */

        /* Logic for switching back to the main page of the application. */
        root = FXMLLoader.load(getClass().getResource("mainPage.FXML"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    /* 
       switchToMainScene method for handling the scene switching.
       This method is called when the user clicks the exit button.
    */
    @FXML
    public void switchToMainScene(ActionEvent event) throws IOException {
        Stage stage = (Stage) exitButton.getScene().getWindow(); /* Getting the current stage from the exit button. */
        stage.close(); /* Closing the current stage, effectively exiting the current scene. */
    }
}
