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
/**
 * This class is associated with the reviewPage.FXML.
 * @author Eric, Nathan
 * @version 11-21-23
 */
public class ReviewPageControllerClass implements Initializable {
	reviewSingleton review = reviewSingleton.getInstance();
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	private Button exitButton;
	@FXML
	private Label reviewFirstName, reviewLastName, reviewEmail, reviewPhone, reviewRoomType, reviewRoomNumber,
			reviewCheckIn, reviewCheckOut, reviewCCfirstName, reviewCClastName, reviewCCnumber, reviewCCexperation,
			reviewCCcountry, reviewCCzip, reviewCCsecurity, reviewReservationNumber;

	private String ID;

	
	/**
	 * If the user is trying to access this page from the reservation number page then this override method calls a method in the reviewSingleton to get a reservation id and generate a scene based on the reservation information for that instance.
	 * Else this page is being accessed from the reservation page and generates the scene based on the information from that reservation.
	 * @param url
	 * @param resourcebundle
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourcebundle) {
		Reserve res = new Reserve();
		ID = review.getID();

		if (res.checkID(ID) == true) {		
			reviewReservationNumber.setText(ID);
			reviewFirstName.setText(res.findCustomerID(ID).firstName);
			reviewLastName.setText(res.findCustomerID(ID).lastName);
			reviewEmail.setText(res.findCustomerID(ID).email);
			reviewPhone.setText(res.findCustomerID(ID).phoneNumber);
			
			reviewRoomType.setText(res.findCustomerID(ID).roomType);
			reviewRoomNumber.setText(res.findCustomerID(ID).roomNumber);
			reviewCCfirstName.setText(res.findCustomerID(ID).firstName);
			reviewCClastName.setText(res.findCustomerID(ID).lastName);
			reviewCheckIn.setText(res.findCustomerID(ID).checkIn);
			reviewCheckOut.setText(res.findCustomerID(ID).checkOut);
			reviewCCcountry.setText(res.findCustomerID(ID).country);
			reviewCCsecurity.setText(res.findCustomerID(ID).secCode);
			reviewCCnumber.setText(res.findCustomerID(ID).cardNumber);
			reviewCCzip.setText(res.findCustomerID(ID).zipCode);
			reviewCCexperation.setText(res.findCustomerID(ID).expDate);
		} else {
			ReadWriteExcel obj = new ReadWriteExcel();
	        int CustomerCount = 1;
	        while (obj.isNull(CustomerCount, 1,0) == false) {
	            CustomerCount++;
	        }
	        CustomerCount--;
	        
	        
			reviewFirstName.setText(obj.ReadExcel("Customers", CustomerCount, 1));
			reviewLastName.setText(obj.ReadExcel("Customers", CustomerCount, 2));
			reviewEmail.setText(obj.ReadExcel("Customers", CustomerCount, 3));
			reviewPhone.setText(obj.ReadExcel("Customers", CustomerCount, 4));
			reviewCCfirstName.setText(obj.ReadExcel("Customers", CustomerCount, 5));
			reviewCClastName.setText(obj.ReadExcel("Customers", CustomerCount, 6));
			reviewCCnumber.setText(obj.ReadExcel("Customers", CustomerCount, 7));
			reviewCCexperation.setText(obj.ReadExcel("Customers", CustomerCount, 8));
			reviewCCcountry.setText(obj.ReadExcel("Customers", CustomerCount, 9));
			reviewCCzip.setText(obj.ReadExcel("Customers", CustomerCount, 10));
			reviewCCsecurity.setText(obj.ReadExcel("Customers", CustomerCount, 11));
			reviewReservationNumber.setText(obj.ReadExcel("Customers", CustomerCount, 12));
			reviewRoomNumber.setText(obj.ReadExcel("Customers", CustomerCount, 13));
			reviewRoomType.setText(obj.ReadExcel("Customers", CustomerCount, 14));
			//reviewPrice.
			reviewCheckIn.setText(obj.ReadExcel("Customers", CustomerCount, 16));
			reviewCheckOut.setText(obj.ReadExcel("Customers", CustomerCount, 17));
		}
	}

	/**
	 * This method is an action that is taken when the user presses the edit reservation button and switches the scene to the edit page.
	 * @throws IOException
	 */
	@FXML
	public void switchToEditPage(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("editPage.FXML"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	/**
	 * This method is an action that is taken when the user presses the cancel reservation button and switches the scene to the cancel page.
	 * @throws IOException
	 */
	@FXML
	public void switchToCancelPage(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("cancelPage.FXML"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	/**
	 * This method is an action that is taken when the user presses the return to menu  button which closes the stage leaving the user back at the main page.
	 * @throws IOException
	 */
	@FXML
	public void switchToMainScene(ActionEvent event) throws IOException {
		Stage stage = (Stage) exitButton.getScene().getWindow();
		stage.close();
	}

}