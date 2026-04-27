package com.egor.basic.hotel.controller;
import com.egor.basic.hotel.annotations.Annotations.*;
import com.egor.basic.hotel.service.RoomService;

@Controller
public class RoomController {

    @Autowired
    private RoomService roomService;

    public void addRoom(int number) {
        roomService.addRoom(number);
    }

    public RoomController() {
    }

    public void showRooms() {
        roomService.getAllRooms().forEach(r -> { System.out.println("Room: " + r.getNumber()
                + " | " + (r.isOccupied() ? "Occupied" : "Free"));});

    }

}
