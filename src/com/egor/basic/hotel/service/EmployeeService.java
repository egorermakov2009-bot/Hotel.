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

    public void assignRole(int id, Role role) {

        List<Employee> list = repository.findAll();

        for(Employee employee : list) {
            if(employee.getId() == id) {
                employee.setRole(role);
            }
        }
        repository.saveAll(list);
    }

}
