package application;

import java.util.HashMap;

public class IDandPasswords {

	 HashMap<String, String> loginInfo = new HashMap<String, String>();
	IDandPasswords(){
		loginInfo.put("redrubio", "Asylum1337");
	}
	
	protected  HashMap getLoginInfo(){
		return loginInfo;
	}
}
