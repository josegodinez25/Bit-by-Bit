package application;

import java.time.LocalDate;
import java.util.List;

public class Room {
	String size;
	String price;
	String availability;
	String number;
	String checkIn;
	String checkOut;
	List<LocalDate> totalDates;

	int total;
	int row;
	int col;

	public Room(String size, String checkIn, String checkOut, List<LocalDate> totalDates) {
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.totalDates = totalDates;
		this.size = size;
	}

	public void updateRoom(Room room) {
		ReadWriteExcel obj = new ReadWriteExcel();

		if (room.size == "Single") {
			room.price = "$115";
		} else if (room.size == "Double") {
			room.price = "$160";
		} else if (room.size == "King") {
			room.price = "224";
		} else if (room.size == "Suite") {
			room.price = "$1000";
		}

		for (int i = 0; i < total; i++) {
			obj.WriteExcel("Availability", row + i,col, "booked");
		}

		room.number = obj.ReadExcel("Availability", 0, col);
	}

}
