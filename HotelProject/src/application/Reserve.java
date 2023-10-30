package application;

import java.util.Random;

public class Reserve {

	public boolean isBooked(Room room) {
		ReadWriteExcel obj = new ReadWriteExcel();
		int start = 0;
		int end = 0;
		int row = 1;
		int col = 1;

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
		int rowCount = 15;
		col = start;
		for (int i = 1; i <= rowCount; i++) {
			if (obj.ReadExcel("Availability", i, 0).equals(room.checkIn) == true) {
				row = i;
			}

		}
		int temp = col;
		int total = room.totalDates.size();
		int i = 0;
		outerLoop:
		for (int j = start; j <= end; j++) {
			for (i = row; i < row + room.totalDates.size(); i++) {
				if (obj.isRoomNull(i, j) == false) {
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

	private boolean IdNotSame(String generatedID) {
		return false;
	}
	
	public String randomGenerator() {
		String ID = null;
		String characterSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		int length = 5;

		Random random = new Random();
		StringBuilder randomString = new StringBuilder();
		
		
		boolean sameID = false;
		
		//makes sure ID is not the same and keeps generating until the id is unique
		while (!sameID) {
			//just generates a new id
			randomString.setLength(0);
		
			for (int i = 0; i < length; i++) {
				int randomIndex = random.nextInt(characterSet.length());
				char randomChar = characterSet.charAt(randomIndex);
				randomString.append(randomChar);
			}
			
			ID = randomString.toString();
			
			// checks if the id is not the same
			if (!IdNotSame(ID)) {
				sameID = true;		
			}
		}
		return ID;
	}	
	
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
		}else {
			System.out.println("All rooms are booked");
		}
	}
	
	public void cancel (String customerID) {
		//it will find the customer id
		Customer customer = findCustomerID(customerID);
		
		if (customer != null) {
			Room room = findCustomerRoom(customer);
			
			if (room != null) {
				//sets room to available
				room.availability = "available";
				clearCustomerInfo(customer);
				System.out.println("Reservation associated with customer id " +
				customerID + " has been cancelled");
			}
			else {
				System.out.println("Room with associated ID " + customerID + " does not exist");
			}
		}
		else {
			System.out.println("Customer with following ID " + customerID + " is not found");
		}	
	}

	private Customer findCustomerID(String customerID) {
		ReadWriteExcel obj = new ReadWriteExcel();
		int rowCount = 15;
			
		for (int i = 1; i<= rowCount; i++) {
			String storedID = obj.ReadExcel("Customer", i , 11);
			if (storedID.equals(customerID)) {
	            
				String firstName = obj.ReadExcel("Customers", i, 1);
	            String lastName = obj.ReadExcel("Customers", i, 2);
	            String email = obj.ReadExcel("Customers", i, 3);
	            String phoneNumber = obj.ReadExcel("Customers", i, 4);
	            String paymentFirstName = obj.ReadExcel("Customers", i, 5);
	            String paymentLastName = obj.ReadExcel("Customers", i, 6);
	            int cardNumber = Integer.parseInt(obj.ReadExcel("Customers", i, 7));
	            int expDate = Integer.parseInt(obj.ReadExcel("Customers", i, 8));
	            int zipCode = Integer.parseInt(obj.ReadExcel("Customers", i, 10));
	            String country = obj.ReadExcel("Customers", i, 9);

	            return new Customer(firstName, lastName, email, phoneNumber, paymentFirstName, paymentLastName,
	                    cardNumber, expDate, zipCode, country);
			}
		}
		return null;
	}
	private void clearCustomerInfo(Customer customer) {
		// TODO Auto-generated method stub
		
	}
	private Room findCustomerRoom(Customer customer) {
		// TODO Auto-generated method stub
		return null;
		}
}
