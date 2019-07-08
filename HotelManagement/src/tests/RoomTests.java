package tests;

import core.Room;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoomTests {

	@Test
	public void setNumberShouldThrowExceptionWhenSettingNegativeRoomNumber() {
		assertThrows(IllegalArgumentException.class, () -> {
			Room room = new Room(-1);
		});
	}
}
