package com.EmployeeManagement;


import com.EmployeeManagement.EmployeeManagementEntity;
import com.EmployeeManagement.EmployeeManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/employees")
public class EmployeeManagementController {

    @Autowired
    private EmployeeManagementService service;

    @PostMapping
    public EmployeeManagementEntity addEmployee(@RequestBody EmployeeManagementEntity employee) {
        return service.addEmployee(employee);
    }
    @GetMapping
    public List<EmployeeManagementEntity> getAllEmployees() {
        return service.getAllEmployees();
    }
    @GetMapping("/{id}")
    public Optional<EmployeeManagementEntity> getEmployeeById(@PathVariable int id) {
        return service.getEmployeeById(id);
    }
    @PutMapping
    public EmployeeManagementEntity updateEmployee(@RequestBody EmployeeManagementEntity employee) {
        return service.updateEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable int id) {
        service.deleteEmployee(id);
        return "Employee deleted successfully.";
    }
}