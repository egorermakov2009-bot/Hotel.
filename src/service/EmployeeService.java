package service;
import annotations.Annotations.*;
import model.Employee;
import model.Role;
import repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository repository = new EmployeeRepository();

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
