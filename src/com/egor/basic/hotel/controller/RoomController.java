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

        System.out.println("\n================= ROOMS =================");
        System.out.println("+--------+--------------+");
        System.out.println("| Number | Status       |");
        System.out.println("+--------+--------------+");

        roomService.getAllRooms().forEach(r -> { System.out.printf("| %-6d | %-12s |%n",
                r.getNumber(),
                r.isOccupied() ? "OCCUPIED" : "FREE");});

        System.out.println("+--------+--------------+");
    }

}
