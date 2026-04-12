package service;
import annotations.Annotations.*;
import model.Room;
import repository.RoomRepository;

import java.util.List;

@Service
public class RoomService {

    private RoomRepository repository = new RoomRepository();

    public void addRoom(int number) {
        repository.save(new Room(number, false));
    }

    public List<Room> getAllRooms() {
        return repository.findAll();
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
