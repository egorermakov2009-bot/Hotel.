package com.egor.basic.hotel.service;
import com.egor.basic.hotel.annotations.Annotations.*;
import com.egor.basic.hotel.model.Guest;
import com.egor.basic.hotel.model.Room;
import com.egor.basic.hotel.repository.GuestRepository;

import java.util.Random;

@Service
public class GuestService {

    @Autowired
    private GuestRepository repository;
    @Autowired
    private RoomService roomService;

    public GuestService() {
    }

    public void checkIn(String name) {

        Room room = roomService.findFreeRoom();

        Guest guest = new Guest(new Random().nextInt(1000), name, room.getNumber());

        repository.save(guest);
        System.out.println("check in: " + name + " in room: " + room.getNumber());
    }

    public void checkOut(int roomNumber) {
        roomService.freeRoom(roomNumber);
    }

    public Iterable<Guest> getAll() {
        return repository.findAll();
    }

}
