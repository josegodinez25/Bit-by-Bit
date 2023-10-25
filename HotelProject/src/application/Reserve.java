package application;

import java.util.Random;

public class Reserve {


	public boolean isBooked(Room room) {
		ReadWriteExcel obj = new ReadWriteExcel();

		int start = 0;
		int end = 0;
		int row1 = 1;
		int row2 = 1;

		if (room.size == "Single") {
			start = 1;
			end = 6;
		}

		int col = start;
		int rowCount = 15;


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
		if (col == 7) {
			return true;
		} else {
			return false;
		}

	}
	
	public String randomGenerator() {
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
}
