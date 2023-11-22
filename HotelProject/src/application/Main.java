package application;/*Package declaration for organization and structure.*/

import java.io.IOException;/*Import IOException for handling input/output exceptions.*/
import javafx.application.Application;/*Import Application class from JavaFX for GUI.*/


public class Main {  /*Main class of your application.*/


	public static void main(String[] args) throws IOException { /* Main method - entry point of the application.*/
		Application.launch(MainPageControllerClass.class, args); /*Launches a JavaFX application. The MainPageControllerClass is the initial UI screen.*/
	}
}
