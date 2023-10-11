package application;

public class Customer {

	public void inputUserDetail(String FirstName, String LastName, String Email, String PhoneNumber) {

		ReadWriteExcel obj = new ReadWriteExcel();
		int CustomerCount = 1;

		while (obj.isNull(CustomerCount, 1) == false) {
			CustomerCount++;
		}

		obj.WriteExcel("Customers", CustomerCount, 1, FirstName);
		obj.WriteExcel("Customers", CustomerCount, 2, LastName);
		obj.WriteExcel("Customers", CustomerCount, 3, Email);
		obj.WriteExcel("Customers", CustomerCount, 4, PhoneNumber);

	}
}
