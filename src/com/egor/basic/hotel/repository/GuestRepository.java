package com.egor.basic.hotel.repository;

import com.egor.basic.hotel.annotations.Annotations.*;
import com.egor.basic.hotel.model.Guest;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repo
public class GuestRepository implements Repository<Guest> {

    private final String FILE = "guests.dat";

    public GuestRepository() {
    }

    @Override
    public void save(Guest guest) {
        List<Guest> guests = findAll();
        guests.add(guest);
        saveAll(guests);
    }

    public void saveAll(List<Guest> guests) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE))) {
            oos.writeObject(guests);
        }catch (IOException e){
            throw new RuntimeException("Error saving guests to file");
        }
    }

    @Override
    public List<Guest> findAll() {

        File file = new File(FILE);

        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE))) {

            return (List<Guest>)ois.readObject();
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

}
