package controller;

import service.GuestService;
import annotations.Annotations.*;

@Controller
public class GuestController {

    private GuestService guestService = new GuestService();

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
