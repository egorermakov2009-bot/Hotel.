package repository;

import model.Employee;
import model.Role;
import model.Room;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements Repository<Employee> {

    private final String FILE = "employee.txt";

    public void save(Employee employee) {
        try{
            File file = new File(FILE);
            file.createNewFile();

            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

            writer.write(employee.toString());
            writer.newLine();
            writer.close();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public List<Employee> findAll() {

        File file = new File(FILE);
        if(!file.exists()) return new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.lines()
                    .filter(i -> !i.isBlank())
                    .map(i -> i.split(","))
                    .map(p -> new Employee(Integer.parseInt(p[0]), p[1], Role.valueOf(p[2])))
                    .toList();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public void saveAll(List<Employee> employees) {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE));

            for(Employee employee : employees) {

                writer.write(employee.toString());
                writer.newLine();
            }
            writer.close();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

}
