package application;

import java.util.Random;

public class Reserve {

	// not finished but it "checks" if the room is open or closed
	public boolean isBooked(Room room) {
		ReadWriteExcel obj = new ReadWriteExcel();

		int start = 0;
		int end = 0;
		int row1 = 1;
		int row2 = 1;

		if (room.size == "Single") {
			start = 1;
			end = 7;
		}

		int col = start;
		int rowCount = 15;
//	        while (obj.isRoomNull(rowCount, 0) == false) {
//	            rowCount++;
//	        }

		for (int i = 1; i <= rowCount; i++) {
			if (obj.ReadExcel("Availability", i, 0).equals(room.checkIn) == true) {
				row1 = i;
			}
		}
		for (int i = 1; i <= rowCount; i++) {
			if (obj.ReadExcel("Availability", i, 0).equals(room.checkOut) == true) {
				row2 = i;
			}
		}

		while (col < end && obj.isRoomNull(row2, col) == false || obj.isRoomNull(row1, col) == false) {
			col++;
		}
		if (col == 8) {
			return true;
		} else {
			return false;
		}

	}

	public void reserveRoom(Room room, Customer customer) {
		// this makes a random 5 character String for the customers ID
		String ID;
		String characterSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		int length = 5;

		Random random = new Random();
		StringBuilder randomString = new StringBuilder();

		for (int i = 0; i < length; i++) {
			int randomIndex = random.nextInt(characterSet.length());
			char randomChar = characterSet.charAt(randomIndex);
			randomString.append(randomChar);
		}
		ID = randomString.toString();
		Reserve obj = new Reserve();

		// checks if the room they are looking for is booked
		if (obj.isBooked(room) == false) {
			// the customer and the room they book have the same ID (will need ID for
			// review,change,cancel)
			customer.ID = ID;
			room.ID = ID;
			// if its not booked then users detailed gets saved and they get an ID
			room.updateRoom(room);
			customer.roomNumber = room.number;
			customer.roomPrice = room.price;
			customer.inputUserDetail();
		}
	}
}
