package model;

public class Guest {

    private int id;
    private String name;
    private int roomNumber;

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
        return  id + "," + name + "," + roomNumber;
    }
}
