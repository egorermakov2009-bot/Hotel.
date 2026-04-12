package model;

public class Room {

    private int number;
    private boolean occupied;

    public Room(int number, boolean occupied) {
        this.number = number;
        this.occupied = occupied;
    }

    public int getNumber() {
        return number;
    }
    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    @Override
    public String toString() {
        return number + "," + occupied;
    }
}
