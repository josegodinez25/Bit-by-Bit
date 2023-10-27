package application;

public class availabilitySingleton {
	
private static final availabilitySingleton instance = new availabilitySingleton();

private availabilitySingleton() {}

public static availabilitySingleton getInstance(){
	return instance;
}




}
