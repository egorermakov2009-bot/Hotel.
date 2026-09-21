package com.egor.basic.hotel.service;
import com.egor.basic.hotel.annotations.Annotations.*;
import com.egor.basic.hotel.model.Guest;
import com.egor.basic.hotel.model.Room;
import com.egor.basic.hotel.repository.GuestRepository;

import java.util.List;
import java.util.Random;

@Service
public class GuestService {

    @Autowired
    private GuestRepository guestRepository;
    @Autowired
    private RoomService roomService;

    public GuestService() {
    }

    public void checkIn(String name) {

        Room room = roomService.findFreeRoom();

        Guest guest = new Guest(new Random().nextInt(1000), name, room.getNumber());

        guestRepository.save(guest);
        System.out.println("check in: " + name + " in room: " + room.getNumber());
    }

    public void checkOut(int roomNumber) {

        List<Guest> guests = guestRepository.findAll();
        for (Guest guest : guests) {
            if(guest.getRoomNumber() == roomNumber) {
                guestRepository.delete(guest.getId());
                break;
            }
        }
        roomService.freeRoom(roomNumber);
    }

    public Iterable<Guest> getAll() {
        return guestRepository.findAll();
    }

}
