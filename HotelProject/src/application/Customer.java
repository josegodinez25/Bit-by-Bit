package application;

public class Customer {
	String firstName;
	String lastName;
	String email;
	String phoneNumber;
	String ID;
	
	
	Customer(String firstName,String lastName, String email, String phoneNumber){
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		ID = "";
	}
	
	public void inputUserDetail() {

		ReadWriteExcel obj = new ReadWriteExcel();
		int CustomerCount = 1;
		//checks if the cell is empty
		while (obj.isNull(CustomerCount, 1) == false) {
			CustomerCount++;
		}
		//if the cell is empty then write the info in excel
		obj.WriteExcel("Customers", CustomerCount, 1, firstName);
		obj.WriteExcel("Customers", CustomerCount, 2, lastName);
		obj.WriteExcel("Customers", CustomerCount, 3, email);
		obj.WriteExcel("Customers", CustomerCount, 4, phoneNumber);
		obj.WriteExcel("Customers", CustomerCount, 5, ID);

	}
}
