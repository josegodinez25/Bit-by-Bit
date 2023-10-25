package application;

public class Room {
	String size;
	String price;
	String availability;
	String number;
	String checkIn;
	String checkOut;

	public Room(String size,String checkIn, String checkOut) {
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.size = size;
	}


	public void updateRoom(Room room) {
		ReadWriteExcel obj = new ReadWriteExcel();
		
		int start = 0;
		int end = 0;
		int row1 = 1;
		int row2 = 1;

		if (room.size == "Single") {
			start = 1;
			end = 6;
			room.price = "$115";
		}else if(room.size == "Double") {
			
			room.price = "$160";
		}else if(room.size == "King") {
			
			room.price = "224";
		}else if(room.size == "Penthouse") {
			
			room.price = "$1000";
		}

		int col = start;
		int rowCount = 15;

		//finds the row with the right dates
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

		//find the col with no occupied rooms
		while (col < end && obj.isRoomNull(row2, col) == false || obj.isRoomNull(row1, col) == false) {
			col++;
		}

		//if the column is pasted end (the last room) then there are no rooms available
		if (col > end) {
			
		} else {
			room.number = obj.ReadExcel("Availability", 0, col);
			obj.WriteExcel("Availability", row1, col, "booked");
			obj.WriteExcel("Availability", row2, col, "booked");
		}

	}

}
