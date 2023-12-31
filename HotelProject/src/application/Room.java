package application;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Nathan
 * @version 11/20/23
 */
public class Room {
	String size;
	String price;
	String number;
	String checkIn;
	String checkOut;
	List<LocalDate> totalDates;

	int total;
	int row;
	int col;

	/**
	 * constructor for Room class
	 * @param size the room size
	 * @param checkIn their check in date
	 * @param checkOut their check out date
	 * @param totalDates an array of all the dates they are staying
	 */
	public Room(String size, String checkIn, String checkOut, List<LocalDate> totalDates) {
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.totalDates = totalDates;
		this.size = size;
	}

	/**
	 * writes booked on the excel sheet for the correct dates and size the customer picks
	 * @param room the room the customer is trying to book
	 */
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
