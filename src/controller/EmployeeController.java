package controller;

import annotations.Annotations.*;
import model.Role;
import service.EmployeeService;

@Controller
public class EmployeeController {

    private EmployeeService employeeService = new EmployeeService();

    public void assignRole(int id, Role role) {
        employeeService.assignRole(id, role);
    }

}
