package com.egor.basic.hotel.service;
import com.egor.basic.hotel.annotations.Annotations.*;
import com.egor.basic.hotel.model.Employee;
import com.egor.basic.hotel.model.Role;
import com.egor.basic.hotel.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public EmployeeService() {
    }

    public void assignRole(int id, Role role, String name) {
        Employee employee = new Employee(id, name, role);
        repository.save(employee);

        System.out.println("\n=========================================");
        System.out.println("      EMPLOYEE HIRED SUCCESSFULLY");
        System.out.println("=========================================");
        System.out.println("Name : " + name);
        System.out.println("ID   : " + id);
        System.out.println("Role : " + role);
        System.out.println("\nWelcome to the Hotel Team!");
        System.out.println("=========================================\n");
    }

    public Iterable<Employee> getAll() {

        System.out.println(repository.findAll().size());
        return repository.findAll();
    }

}
