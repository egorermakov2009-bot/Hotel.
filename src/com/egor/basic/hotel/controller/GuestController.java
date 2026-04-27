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

        guestService.getAll().forEach(System.out::println);
    }

}
