package application;

import java.util.Random;


public class Reserve {
		
	//not finished but it "checks" if the room is open or closed
	public boolean isBooked(Room room) {
		return false;
	}

	
	
	public void reserveRoom(Room room, Customer customer) {
		//this makes a random 5 character String for the customers ID
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
		
		
		//checks if the room they are looking for is booked
		if(obj.isBooked(room) == false) {
			//the customer and the room they book have the same ID (will need ID for review,change,cancel)
			customer.ID = ID;
			room.ID = ID;
			//if its not booked then users detailed gets saved and they get an ID
			customer.inputUserDetail();
		}
	}
}
