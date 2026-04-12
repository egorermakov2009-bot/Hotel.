package repository;
import java.util.*;
import model.Room;
import java.io.*;


public class RoomRepository implements Repository<Room>{

    private final String FILE = "rooms.txt";

    public void save(Room room) {
        try{
            File file = new File(FILE);
            file.createNewFile();

            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

            writer.write(room.toString());
            writer.newLine();
            writer.close();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public List<Room> findAll() {

        File file = new File(FILE);
        if(!file.exists()) return new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.lines()
                    .filter(i -> !i.isBlank())
                    .map(i -> i.split(","))
                    .map(p -> new Room(Integer.parseInt(p[0]), p[1].equals("true")))
                    .toList();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public void saveAll(List<Room> rooms) {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE));

            for(Room room : rooms) {
                writer.write(room.toString());
                writer.newLine();
            }

            writer.close();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

}
