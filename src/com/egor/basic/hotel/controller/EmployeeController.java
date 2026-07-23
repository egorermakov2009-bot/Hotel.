package com.egor.basic.hotel.controller;

import com.egor.basic.hotel.annotations.Annotations.*;
import com.egor.basic.hotel.model.Employee;
import com.egor.basic.hotel.model.Role;
import com.egor.basic.hotel.service.EmployeeService;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    public EmployeeController() {
    }

    public void assignRole(int id, Role role, String name) {
        employeeService.assignRole(id, role, name);
    }

    public void showEmployee() {

        System.out.println("\n================ EMPLOYEES ================");
        System.out.println("+------+---------------+-----------+");
        System.out.println("| ID   | Name          | Role      |");
        System.out.println("+------+---------------+-----------+");

        for(Employee employee : employeeService.getAll()) {
            System.out.printf("| %-4d | %-13s | %-9s |%n",
                    employee.getId(),
                    employee.getName(),
                    employee.getRole());
        }
        System.out.println("+------+---------------+-----------+");
    }

}
