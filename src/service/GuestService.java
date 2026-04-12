package service;
import annotations.Annotations.*;
import model.Guest;
import model.Room;
import repository.GuestRepository;

import java.util.Random;

@Service
public class GuestService {

    private GuestRepository repository = new GuestRepository();
    private RoomService roomService = new RoomService();

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
