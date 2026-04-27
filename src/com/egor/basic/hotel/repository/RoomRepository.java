package com.egor.basic.hotel.repository;

import java.util.*;

import com.egor.basic.hotel.annotations.Annotations.*;
import com.egor.basic.hotel.model.Room;
import java.io.*;

@Repo
public class RoomRepository implements Repository<Room> {

    private final String FILE = "rooms.dat";

    public RoomRepository() {
    }

    @Override
    public void save(Room room) {
        List<Room> rooms = findAll();
        rooms.add(room);
        saveAll(rooms);
    }

    public void saveAll(List<Room> rooms) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE))) {
            oos.writeObject(rooms);
        }catch(IOException e){
            throw new RuntimeException("Error saving rooms to file");
        }
    }

    @Override
    public List<Room> findAll() {

        File file = new File(FILE);

        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE))) {

            return (List<Room>)ois.readObject();
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

}
