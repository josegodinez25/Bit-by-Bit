package application;

public class Room {
	String size;
	String price;
	String availability;
	String number;
	String ID;
	String checkIn;
	String checkOut;

	public Room(String size, String price, String availability, String number, String checkIn, String checkOut) {
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.size = size;
		this.price = price;
		this.availability = availability;
		this.number = number;
		ID = "";
	}

	public void updateRoom(Room room) {
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
		int rowCount = 12;
//	        while (obj.isRoomNull(rowCount, 0) == false) {
//	            rowCount++;
//	        }

		for (int i = 1; i <= rowCount; i++) {
			if (obj.ReadExcel("Availability", i, 0).equals(room.checkIn) == true) {
				row1 = i;
			}
		}

		obj.WriteExcel("Availability", row1, col, "booked");
		
		for (int i = 1; i <= rowCount; i++) {
			if (obj.ReadExcel("Availability", i, 0).equals(room.checkOut) == true) {
				row2 = i;
			}
		}
		
		System.out.println(row2);
		System.out.println(row1);
		while (col < end && obj.isRoomNull(row2, col) == false || obj.isRoomNull(row1, col) == false) {
			col++;
		}
		
		System.out.println(col);
		if(col == 8) {
			System.out.println("All rooms are booked");
		}else {
			obj.WriteExcel("Availability", row1, col, "booked");
			obj.WriteExcel("Availability", row2, col, "booked");
		}

	}

}
