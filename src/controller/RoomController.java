package controller;
import annotations.Annotations.*;
import service.RoomService;

@Controller
public class RoomController {

    private RoomService roomService = new RoomService();

    public void addRoom(int number) {
        roomService.addRoom(number);
    }

    public void showRooms() {
        roomService.getAllRooms().forEach(r -> { System.out.println("Room: " + r.getNumber()
                + " | " + (r.isOccupied() ? "Occupied" : "Free"));});

    }

}
