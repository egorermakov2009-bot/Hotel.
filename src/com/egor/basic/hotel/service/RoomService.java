package com.egor.basic.hotel.service;
import com.egor.basic.hotel.annotations.Annotations.*;
import com.egor.basic.hotel.model.Room;
import com.egor.basic.hotel.repository.RoomRepository;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository repository;

    public void addRoom(int number) {
        repository.save(new Room(number, false));
    }

    public List<Room> getAllRooms() {
        return repository.findAll();
    }

    public RoomService() {
    }

    public Room findFreeRoom() {
        List<Room> rooms = repository.findAll();
        for(Room room : rooms) {
            if(!room.isOccupied()) {
                room.setOccupied(true);

                repository.saveAll(rooms);
                return room;
            }
        }
        throw new RuntimeException("No free rooms");
    }

    public void freeRoom(int number) {

        List<Room> rooms = repository.findAll();

        for(Room room : rooms) {
            if(room.getNumber() == number) {
                room.setOccupied(false);
            }
        }
        repository.saveAll(rooms);
    }

}
