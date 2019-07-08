import core.Hotel;
import core.Manager;
import core.Room;

import java.util.ArrayList;

public class HotelServiceApplication {
	public static void main(String[] args) {
		Manager manager = new Manager("Manager");

		Room firstRoom = new Room(1);
		Room secondRoom = new Room(2);

		Hotel hotel = new Hotel("Hotel");
		hotel.getRooms().add(firstRoom);
		hotel.getRooms().add(secondRoom);
		manager.setHotel(hotel);
		ArrayList<Room> freeRooms = manager.getAvailableRooms();
		if (!freeRooms.isEmpty()) {
			freeRooms.get(0).bookTheRoom();
		}

		hotel.getRooms().stream().forEach(r -> r.clearRoomBooking());
	}
}
