package application;

public class Customer {
	String firstName;
	String lastName;
	String email;
	String phoneNumber;

	String paymentFirstName;
	String paymentLastName;
	String cardNumber;
	String expDate;
	String zipCode;
	String secCode;
	String country;

	String ID;
	String roomType;
	String roomNumber;
	String roomPrice;
	String totalPrice;
	String checkIn;
	String checkOut;

	// constructor of Customer Class
	// contains all of their information (11 Strings)
	Customer(String firstName, String lastName, String email, String phoneNumber, String paymentFirstName,
			String paymentLastName, String cardNumber, String expDate, String zipCode, String country, String secCode) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.paymentFirstName = paymentFirstName;
		this.paymentLastName = paymentLastName;
		this.cardNumber = cardNumber;
		this.expDate = expDate;
		this.zipCode = zipCode;
		this.country = country;
		this.secCode = secCode;

		ID = "";
	}

	public String getEmail() {
		return email;
	}

	// writes all the customers information into an Excel file
	public void inputUserDetail() {
		ReadWriteExcel obj = new ReadWriteExcel();

		int CustomerCount = 1;
		// checks if the cell is empty
		while (obj.isNull(CustomerCount, 1, 0) == false) {
			CustomerCount++;
		}

		// if the cell is empty then write the info in excel
		obj.WriteExcel("Customers", CustomerCount, 1, firstName);
		obj.WriteExcel("Customers", CustomerCount, 2, lastName);
		obj.WriteExcel("Customers", CustomerCount, 3, email);
		obj.WriteExcel("Customers", CustomerCount, 4, phoneNumber);
		obj.WriteExcel("Customers", CustomerCount, 5, paymentFirstName);
		obj.WriteExcel("Customers", CustomerCount, 6, paymentLastName);
		obj.WriteExcel("Customers", CustomerCount, 7, cardNumber);
		obj.WriteExcel("Customers", CustomerCount, 8, expDate);
		obj.WriteExcel("Customers", CustomerCount, 9, country);
		obj.WriteExcel("Customers", CustomerCount, 10, zipCode);
		obj.WriteExcel("Customers", CustomerCount, 11, secCode);
		obj.WriteExcel("Customers", CustomerCount, 12, ID);
		obj.WriteExcel("Customers", CustomerCount, 13, roomNumber);
		obj.WriteExcel("Customers", CustomerCount, 14, roomType);
		obj.WriteExcel("Customers", CustomerCount, 15, roomPrice);
		obj.WriteExcel("Customers", CustomerCount, 16, checkIn);
		obj.WriteExcel("Customers", CustomerCount, 17, checkOut);

	}
}