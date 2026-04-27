package com.egor.basic.hotel.repository;

import com.egor.basic.hotel.annotations.Annotations.*;
import com.egor.basic.hotel.model.Employee;
import com.egor.basic.hotel.model.Role;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repo
public class EmployeeRepository implements Repository<Employee> {

    private final String FILE = "employee.dat";

    public EmployeeRepository() {
    }

    @Override
    public void save(Employee employee) {
        List<Employee> employees = findAll();
        employees.add(employee);
        saveAll(employees);
    }

    public void saveAll(List<Employee> employees) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE))) {
            oos.writeObject(employees);
        }catch (IOException e){
            throw new RuntimeException("Error saving employees to file");
        }
    }

    @Override
    public List<Employee> findAll() {

        File file = new File(FILE);

        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE))) {

            return (List<Employee>)ois.readObject();
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

}
