package com.egor.basic.hotel.model;

import java.io.Serializable;

public class Guest implements Serializable {

    private int id;
    private String name;
    private int roomNumber;

    public Guest() {}

    public Guest(int id, String name, int roomNumber) {
        this.id = id;
        this.name = name;
        this.roomNumber = roomNumber;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getRoomNumber() {
        return roomNumber;
    }

    @Override
    public String toString() {
        return "Id: " + id + " | name: " + name + " | room: " + roomNumber;
    }
}
