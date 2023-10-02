package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class Main extends Application {

	
	@Override
	public void start(Stage stage) throws Exception {
		//Group root = new Group();
		try {
		Parent root = FXMLLoader.load(getClass().getResource("mainPage.FXML"));
		Image topIcon = new Image("Hotel_Logo.jpg");	
		Scene mainScreenScene = new Scene(root);	
		//Stage mainScreen = new Stage();
		
		stage.setWidth(1366);
		stage.setHeight(768);
		//resize true or false
		//stage.serResizable(false);
		stage.getIcons().add(topIcon);
		stage.setTitle("CHANGE TITLE");
		stage.setScene(mainScreenScene);
		stage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
}
	public static void main(String[] args) {
		Application.launch(args);
		
	}
}
