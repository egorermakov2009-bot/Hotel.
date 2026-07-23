package com.egor.basic.hotel.controller;

import com.egor.basic.hotel.service.GuestService;
import com.egor.basic.hotel.annotations.Annotations.*;

@Controller
public class GuestController {

    @Autowired
    private GuestService guestService;

    public GuestController() {
    }

    public void checkInGuest(String name) {
        guestService.checkIn(name);
    }

    public void checkOutGuest(int room) {
        guestService.checkOut(room);
    }

    public void showGuests() {

        System.out.println("\n================ GUESTS ================");
        System.out.println("+------+---------------+------+");
        System.out.println("| ID   | Name          | Room |");
        System.out.println("+------+---------------+------+");

        guestService.getAll().forEach(g -> { System.out.printf("| %-4d | %-13s | %-4d |%n",
                g.getId(),
                g.getName(),
                g.getRoomNumber());});

        System.out.println("+------+---------------+------+");
    }

}
