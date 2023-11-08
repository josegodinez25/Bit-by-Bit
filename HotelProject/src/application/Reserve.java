package application;

import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reserve {

	
	//returns true or false if the room is booked or not
	public boolean isBooked(Room room) {
		ReadWriteExcel obj = new ReadWriteExcel();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		int start = 0;
		int end = 0;
		int row = 1;
		int col = 1;

		//sets the size to the correct size the customer picked
		if (room.size == "Single") {
			start = 1;
			end = 6;
		} else if (room.size == "Double") {
			start = 7;
			end = 12;

		} else if (room.size == "King") {
			start = 13;
			end = 18;

		} else if (room.size == "Suite") {
			start = 19;
			end = 19;
		}

		//how many dates we have
		int rowCount = 134;
		col = start;
		
		//finds the row where the customers check in date is
		for (int i = 1; i <= rowCount; i++) {
			if (df.format(obj.getCell("Availability", i, 0).getDateCellValue()).equals(room.checkIn) == true) {
				row = i;
			}
		}
		
		int temp = col;
		int total = room.totalDates.size();
		int i = 0;
		//uses nested loop to check if the dates the customer wants is booked
		outerLoop: for (int j = start; j <= end; j++) {
			for (i = row; i < row + room.totalDates.size(); i++) {
				if (obj.isNull(i, j, 1) == false) {
					col++;
					i = row + room.totalDates.size();
				} else if (col == temp) {
					if (i == row + total - 1) {
						break outerLoop;
					}
				}
			}
			temp++;
		}
		if (col > end) {
			return true;
		} else {
			room.row = row;
			room.col = col;
			room.total = room.totalDates.size();
			return false;
		}

	}

	//function that does not allow for duplicate IDs
	private boolean IdNotSame(String generatedID) {
		return false;
	}

	//returns a 5 digit random String
	//this is the customers ID
	public String randomGenerator() {
		String ID = null;
		String characterSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		int length = 5;

		Random random = new Random();
		StringBuilder randomString = new StringBuilder();

		boolean sameID = false;

		// makes sure ID is not the same and keeps generating until the id is unique
		while (!sameID) {
			// just generates a new id
			randomString.setLength(0);

			for (int i = 0; i < length; i++) {
				int randomIndex = random.nextInt(characterSet.length());
				char randomChar = characterSet.charAt(randomIndex);
				randomString.append(randomChar);
			}

			ID = randomString.toString();
			if (!IdNotSame(ID)) {
				sameID = true;
			}
		}
		return ID;
	}

	//if the room the customer selected is available then it saves all the data into an Excel Sheet
	public void reserveRoom(Room room, Customer customer) {
		if (isBooked(room) == false) {
			// the customer gets a unique id (will need ID for review,change,cancel)
			String ID = randomGenerator();
			customer.ID = ID;
			room.updateRoom(room);
			customer.roomNumber = room.number;
			customer.roomPrice = room.price;
			customer.checkIn = room.checkIn;
			customer.checkOut = room.checkOut;

			customer.inputUserDetail();
		} else {
			System.out.println("All rooms are booked");
		}
		// after sys print all room are booked
		// public void reserveRoom(Room room, Customer customer) {

		// confirmation email based on reservationPageControllerClass or SearchPageControllerClass
				// change the values here for searchPageController variables	
				// if any of these variables are null it will print this email
				if (room.number == null || room.checkIn == null || room.checkOut == null) {
					// change 
					String subject = "Reservation Confirmation";
					String messageText = "Dear " + customer.firstName + " " + customer.lastName + ",\n\n"
		                + "Your reservation has been confirmed. Here are the details:\n"
		                + "Room Number: " + room.number + "\n"
		                + "Check-In Date: " + room.checkIn + "\n"
		                + "Check-Out Date: " + room.checkOut + "\n"
		                + "Total Price: " + room.price + "\n"
		                + "Reservation ID: " + customer.ID + "\n\n"
		                + "Thank you for choosing Asylum Hotel for your stay!\n";

					EmailSend emailSender = new EmailSend();
					emailSender.sendEmailToCustomer(customer.email, subject, messageText);	
				} 
				// if the variables are not null then this message will send 
				else {
					String subject = "Reservation Confirmation";
			        String messageText = "Dear " + customer.firstName + " " + customer.lastName + ",\n\n"
			                + "Your reservation has been confirmed. Here are the details:\n"
			                + "Room Number: " + room.number + "\n"
			                + "Check-In Date: " + room.checkIn + "\n"
			                + "Check-Out Date: " + room.checkOut + "\n"
			                + "Total Price: " + room.price + "\n"
			                + "Reservation ID: " + customer.ID + "\n\n"
			                + "Thank you for choosing Asylum Hotel for your stay!\n";
			        EmailSend emailSender = new EmailSend();
					emailSender.sendEmailToCustomer(customer.email, subject, messageText);
				}
	}

	//scans the excel sheet for the ID that is inputed then returns that Customer
	public Customer findCustomerID(String customerID) {
		ReadWriteExcel obj = new ReadWriteExcel();
		int rowCount = 1;
		while (obj.isNull(rowCount, 1, 0) == false) {
			rowCount++;
		}

		for (int i = 1; i <= rowCount; i++) {
			String storedID = obj.ReadExcel("Customers", i, 11);
			if (storedID.equals(customerID)) {

				String firstName = obj.ReadExcel("Customers", i, 1);
				String lastName = obj.ReadExcel("Customers", i, 2);
				String email = obj.ReadExcel("Customers", i, 3);
				String phoneNumber = obj.ReadExcel("Customers", i, 4);
				String paymentFirstName = obj.ReadExcel("Customers", i, 5);
				String paymentLastName = obj.ReadExcel("Customers", i, 6);
				String cardNumber = obj.ReadExcel("Customers", i, 7);
				String expDate = obj.ReadExcel("Customers", i, 8);
				String zipCode = obj.ReadExcel("Customers", i, 10);
				String country = obj.ReadExcel("Customers", i, 9);
				String roomNumber = obj.ReadExcel("Customers", i, 12);
				String price = obj.ReadExcel("Customers", i, 13);
				String startDate = obj.ReadExcel("Customers", i, 14);
				String endDate = obj.ReadExcel("Customers", i, 15);

				Customer customer = new Customer(firstName, lastName, email, phoneNumber, paymentFirstName,
						paymentLastName, cardNumber, expDate, zipCode, country);
				customer.checkIn = startDate;
				customer.roomNumber = roomNumber;
				customer.roomPrice = price;
				customer.checkOut = endDate;

				return customer;
			}
		}
		return null;
	}

	//returns true or false if the ID that is inputed exists or not
	public boolean checkID(String ID) {
		ReadWriteExcel obj = new ReadWriteExcel();
		int rowCount = 1;
		while (obj.isNull(rowCount, 1, 0) == false) {
			rowCount++;
		}
		for (int i = 1; i < rowCount; i++) {
			if (obj.ReadExcel("Customers", i, 11).equals(ID) == true) {
				return true;
			}
		}
		return false;
	}

	//finds which customer is trying to edit info using the String ID
	//then updates their information with the new information
	public void changeReservation(String ID, Customer cus) {
		ReadWriteExcel obj = new ReadWriteExcel();
		int rowCount = 1;
		while (obj.isNull(rowCount, 1, 0) == false) {
			rowCount++;
		}

		for (int i = 1; i <= rowCount; i++) {
			if (obj.ReadExcel("Customers", i, 11).equals(ID)) {
				obj.WriteExcel("Customers", rowCount, 1, cus.firstName);
				obj.WriteExcel("Customers", rowCount, 2, cus.lastName);
				obj.WriteExcel("Customers", rowCount, 3, cus.email);
				obj.WriteExcel("Customers", rowCount, 4, cus.phoneNumber);
				obj.WriteExcel("Customers", rowCount, 5, cus.paymentFirstName);
				obj.WriteExcel("Customers", rowCount, 6, cus.paymentLastName);
				obj.WriteExcel("Customers", rowCount, 7, cus.cardNumber);
				obj.WriteExcel("Customers", rowCount, 8, cus.expDate);
				obj.WriteExcel("Customers", rowCount, 9, cus.country);
				obj.WriteExcel("Customers", rowCount, 10, cus.zipCode);
				obj.WriteExcel("Customers", rowCount, 11, cus.ID);
				obj.WriteExcel("Customers", rowCount, 12, cus.roomNumber);
				obj.WriteExcel("Customers", rowCount, 13, cus.roomPrice);
				obj.WriteExcel("Customers", rowCount, 14, cus.checkIn);
				obj.WriteExcel("Customers", rowCount, 15, cus.checkOut);
			}
		}
	}

	//finds which customer is trying to edit info using the String ID
	//then sets all their information back to null
	public void clearCustomerInfo(String ID, Customer cus) {
		ReadWriteExcel obj = new ReadWriteExcel();
		int rowCount = 1;
		while (obj.isNull(rowCount, 1, 0) == false) {
			rowCount++;
		}

		for (int i = 1; i <= rowCount; i++) {
			if (obj.ReadExcel("Customers", i, 11).equals(ID)) {
				obj.WriteExcel("Customers", rowCount, 1, null);
				obj.WriteExcel("Customers", rowCount, 2, null);
				obj.WriteExcel("Customers", rowCount, 3, null);
				obj.WriteExcel("Customers", rowCount, 4, null);
				obj.WriteExcel("Customers", rowCount, 5, null);
				obj.WriteExcel("Customers", rowCount, 6, null);
				obj.WriteExcel("Customers", rowCount, 7, null);
				obj.WriteExcel("Customers", rowCount, 8, null);
				obj.WriteExcel("Customers", rowCount, 9, null);
				obj.WriteExcel("Customers", rowCount, 10, null);
				obj.WriteExcel("Customers", rowCount, 11, null);
				obj.WriteExcel("Customers", rowCount, 12, null);
				obj.WriteExcel("Customers", rowCount, 13, null);
				obj.WriteExcel("Customers", rowCount, 14, null);
				obj.WriteExcel("Customers", rowCount, 15, null);
			}
		}

	}
}
