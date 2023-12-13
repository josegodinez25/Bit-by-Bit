package application;

import java.util.HashMap;

/**
 * Class to manage user ID's and passwords
 */
public class IDandPasswords {
	
	// This hashmap manages login information
	 HashMap<String, String> loginInfo = new HashMap<String, String>();
	 /**
	 * Constructor to initialize default login credentials.
	  */
	IDandPasswords(){
		loginInfo.put("redrubio", "Asylum1337");
	}
	/**
     * Retrieves the login information HashMap.
     *
     * @return The HashMap containing login information.
     */
	protected  HashMap getLoginInfo(){
		return loginInfo;
	}
}
