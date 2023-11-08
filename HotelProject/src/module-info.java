module HelloFX {
	requires javafx.controls;
	requires javafx.graphics;
	requires java.desktop;
	requires javafx.fxml;
	requires javafx.base;
	requires org.apache.poi.poi;
	requires java.mail;
	
	opens application to javafx.graphics, javafx.fxml;
}
