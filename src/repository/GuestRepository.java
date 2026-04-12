package repository;

import model.Guest;
import model.Room;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class GuestRepository implements Repository<Guest> {

    private final String FILE = "guests.txt";

    public void save(Guest guest) {
        try{
            File file = new File(FILE);
            file.createNewFile();

            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

            writer.write(guest.toString());
            writer.newLine();
            writer.close();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public List<Guest> findAll() {

        File file = new File(FILE);
        if(!file.exists()) return new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.lines()
                    .filter(i -> !i.isBlank())
                    .map(i -> i.split(","))
                    .map(p -> new Guest(Integer.parseInt(p[0]), p[1], Integer.parseInt(p[2])))
                    .toList();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public void saveAll(List<Guest> guests) {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE));

            for(Guest guest : guests) {

                writer.write(guest.toString());
                writer.newLine();
            }
            writer.close();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

}
