package com.egor.basic.hotel.controller;

import com.egor.basic.hotel.annotations.Annotations.*;
import com.egor.basic.hotel.model.Role;
import com.egor.basic.hotel.service.EmployeeService;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    public EmployeeController() {
    }

    public void assignRole(int id, Role role) {
        employeeService.assignRole(id, role);
    }

}
