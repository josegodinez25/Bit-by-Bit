package application;

import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Nathan
 * @version 11/20/23
 */
public class Reserve {
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * checks if the room the user is looking for is booked
	 * @param room gets the users room they are trying to book
	 * @return returns true or false whether or not the dates of the room they are trying to book is available
	 */
	public boolean isBooked(Room room) {
		ReadWriteExcel obj = new ReadWriteExcel();
		int start = 0;
		int end = 0;
		int row = 1;
		int col = 1;

		// sets the size to the correct size the customer picked
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

		// how many dates we have
		int rowCount = 134;
		col = start;

		// finds the row where the customers check in date is
		for (int i = 1; i <= rowCount; i++) {
			if (df.format(obj.getCell("Availability", i, 0).getDateCellValue()).equals(room.checkIn) == true) {
				row = i;
			}
		}

		int temp = col;
		int total = room.totalDates.size();
		int i = 0;
		// uses nested loop to check if the dates the customer wants is booked
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

	// function that does not allow for duplicate IDs
	private boolean IdNotSame(String generatedID) {
		return false;
	}

	//returns a 5 digit random String
		//this is the customers ID
		/**
		 * creates a unique ID for the user
		 * @return returns a random 5 character ID
		 */
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

	// if the room the customer selected is available then it saves all the data
	// into an Excel Sheet
	
	/**
	 * checks if the room the customer is booked and if its not then it saves the customers information into our database
	 * @param room Room information
	 * @param customer Customer information
	 */
	public void reserveRoom(Room room, Customer customer) {
		if (isBooked(room) == false) {
			// the customer gets a unique id (will need ID for review,change,cancel)
			String ID = randomGenerator();
			customer.ID = ID;
			room.updateRoom(room);

			customer.roomType = room.size;
			customer.roomNumber = room.number;
			customer.roomPrice = room.price;
			customer.checkIn = room.checkIn;
			customer.checkOut = room.checkOut;

			customer.inputUserDetail();
			// we can comment this line out when we want to test the program so we dont get
			// a bunch of emails
			// sendEmail(customer, room, "reserveRoom");
		} else {
			System.out.println("All rooms are booked");
		}
	}

	// have to adjust the price of the hotel that is sent in the email
	// only sends the price of the room type
	
	/**
	 * sends an email to a customer if they reserve, cancel , or change a reservation
	 * @param customer Customers information
	 * @param room Room information
	 * @param method type of action ex: cancel, change, or reserve
	 */
	public void sendEmail(Customer customer, Room room, String method) {
		String subject = "Reservation Confirmation";
		String messageText = "";

		if (method.equals("clearCustomerInfo")) {
			subject = "Customer Information Cleared";
			messageText = "Dear " + customer.firstName + " " + customer.lastName + ",\n\n"
					+ "Your customer information has been cleared from our records.\n\n"
					+ "If this was not intentional, please contact us for further assistance.\n\n" + "Thank you.\n";
		} else if (method.equals("changeReservation")) {
			subject = "Reservation Updated";
			messageText = "Dear " + customer.firstName + " " + customer.lastName + ",\n\n"
					+ "Your reservation details have been updated successfully:\n" + "Room Number: " + room.number
					+ "\n" + "Check-In Date: " + room.checkIn + "\n" + "Check-Out Date: " + room.checkOut + "\n"
					+ "Total Price: " + room.price + "\n" + "Reservation ID: " + customer.ID + "\n\n"
					+ "Thank you for choosing Asylum Hotel for your stay!\n";
		} else {
			messageText = "Dear " + customer.firstName + " " + customer.lastName + ",\n\n"
					+ "Your reservation has been confirmed. Here are the details:\n" + "Room Number: " + room.number
					+ "\n" + "Check-In Date: " + room.checkIn + "\n" + "Check-Out Date: " + room.checkOut + "\n"
					+ "Total Price: " + room.price + "\n" + "Reservation ID: " + customer.ID + "\n\n"
					+ "Thank you for choosing Asylum Hotel for your stay!\n";
		}

		EmailSend emailSender = new EmailSend();
		emailSender.sendEmailToCustomer(customer.email, subject, messageText);
	}

	// scans the excel sheet for the ID that is inputed then returns that Customer
	
	/**
	 * uses the ID to find all of the customers information
	 * @param customerID The reservation ID the customer inputs
	 * @return Returns all the customers information given their ID
	 */
	public Customer findCustomerID(String customerID) {
		ReadWriteExcel obj = new ReadWriteExcel();
		int rowCount = 1;
		while (obj.isNull(rowCount, 1, 0) == false) {
			rowCount++;
		}

		for (int i = 1; i <= rowCount; i++) {
			String storedID = obj.ReadExcel("Customers", i, 12);
			if (storedID.equals(customerID)) {

				String firstName = obj.ReadExcel("Customers", i, 1);
				String lastName = obj.ReadExcel("Customers", i, 2);
				String email = obj.ReadExcel("Customers", i, 3);
				String phoneNumber = obj.ReadExcel("Customers", i, 4);
				String paymentFirstName = obj.ReadExcel("Customers", i, 5);
				String paymentLastName = obj.ReadExcel("Customers", i, 6);
				String cardNumber = obj.ReadExcel("Customers", i, 7);
				String expDate = obj.ReadExcel("Customers", i, 8);
				String country = obj.ReadExcel("Customers", i, 9);
				String zipCode = obj.ReadExcel("Customers", i, 10);
				String secCode = obj.ReadExcel("Customers", i, 11);
				String roomNumber = obj.ReadExcel("Customers", i, 13);
				String roomType = obj.ReadExcel("Customers", i, 14);
				String price = obj.ReadExcel("Customers", i, 15);
				String startDate = obj.ReadExcel("Customers", i, 16);
				String endDate = obj.ReadExcel("Customers", i, 17);

				Customer customer = new Customer(firstName, lastName, email, phoneNumber, paymentFirstName,
						paymentLastName, cardNumber, expDate, zipCode, country, secCode);
				customer.ID = customerID;
				customer.checkIn = startDate;
				customer.roomNumber = roomNumber;
				customer.roomPrice = price;
				customer.checkOut = endDate;
				customer.roomType = roomType;
				return customer;
			}
		}
		return null;
	}

	// returns true or false if the ID that is inputed exists or not
	
	/**
	 * checks if the ID the user puts in exists or not
	 * @param ID The reservation ID the customer inputs
	 * @return returns true or false if the ID exists or not
	 */
	public boolean checkID(String ID) {
		ReadWriteExcel obj = new ReadWriteExcel();
		int rowCount = 1;
		while (obj.isNull(rowCount, 1, 0) == false) {
			rowCount++;
		}
		for (int i = 1; i < rowCount; i++) {
			if (obj.ReadExcel("Customers", i, 12).equals(ID) == true) {
				return true;
			}
		}
		return false;
	}

	// finds which customer is trying to edit info using the String ID
	// then updates their information with the new information
	
	/**
	 * updates the excel sheet with customers new information
	 * @param The reservation ID the customer inputs
	 * @param cus The customer that wants to change their reservation
	 * @param room The Room that wants to get changed
	 */
	public void changeReservation(String ID, Customer cus, Room room) {
		ReadWriteExcel obj = new ReadWriteExcel();

		int rowCount = 1;
		while (obj.isNull(rowCount, 1, 0) == false) {
			rowCount++;
		}

		for (int i = 1; i < rowCount; i++) {
			if (obj.ReadExcel("Customers", i, 12).equals(ID)) {
				obj.WriteExcel("Customers", i, 1, cus.firstName);
				obj.WriteExcel("Customers", i, 2, cus.lastName);
				obj.WriteExcel("Customers", i, 3, cus.email);
				obj.WriteExcel("Customers", i, 4, cus.phoneNumber);
				obj.WriteExcel("Customers", i, 5, cus.paymentFirstName);
				obj.WriteExcel("Customers", i, 6, cus.paymentLastName);
				obj.WriteExcel("Customers", i, 7, cus.cardNumber);
				obj.WriteExcel("Customers", i, 8, cus.expDate);
				obj.WriteExcel("Customers", i, 9, cus.country);
				obj.WriteExcel("Customers", i, 10, cus.zipCode);
				obj.WriteExcel("Customers", i, 11, cus.secCode);
				obj.WriteExcel("Customers", i, 12, cus.ID);
				obj.WriteExcel("Customers", i, 13, room.number);
				obj.WriteExcel("Customers", i, 14, cus.roomType);
				obj.WriteExcel("Customers", i, 15, room.price);
				obj.WriteExcel("Customers", i, 16, cus.checkIn);
				obj.WriteExcel("Customers", i, 17, cus.checkOut);
			}
		}
		// sendEmail(cus, room, "changeReservation");
	}

	// finds which customer is trying to edit info using the String ID
	// then sets all their information back to null
	/**
	 * clears the customers room information and makes ID not exist anymore
	 * @param ID The reservation ID the customer inputs
	 */
	public void clearCustomerInfo(String ID) {
		ReadWriteExcel obj = new ReadWriteExcel();
		String roomNumber = "";
		String checkIn = "";
		String checkOut = "";
		
		int rowCount = 1;
		while (obj.isNull(rowCount, 1, 0) == false) {
			rowCount++;
		}
		// sendEmail(findCustomerID(ID), null, "clearCustomerInfo");
		for (int i = 1; i < rowCount; i++) {
			if (obj.ReadExcel("Customers", i, 12).equals(ID)) {
				obj.WriteExcel("Customers", i, 12, "REMOVED");
				 roomNumber = obj.ReadExcel("Customers", i, 13);
				 checkIn = obj.ReadExcel("Customers", i, 16);
				 checkOut = obj.ReadExcel("Customers", i, 17);
			}
		}
	
		int colRoomNumber = 0;
		int rowCheckIn = 1;
		int rowCheckOut = 1;
		
		for(int i = 1; i <= 19; i++) {
			if(obj.ReadExcel("Availability", 0, i).equals(roomNumber) == true) {
				colRoomNumber = i;
			}
		}
		
		for(int i = 1; i <= 134; i++) {
			if(df.format(obj.getCell("Availability", i, 0).getDateCellValue()).equals(checkIn) == true) {
				rowCheckIn = i;
			}
		}
		for(int i = 1; i <= 134; i++) {
			if(df.format(obj.getCell("Availability", i, 0).getDateCellValue()).equals(checkOut) == true) {
				rowCheckOut = i;
			}
		}

		for(int i = rowCheckIn; i <= rowCheckOut;i++) {
			obj.WriteExcel("Availability", i, colRoomNumber, null);
		}		
	}
}
