package com.EmployeeManagement.Controller;

import com.EmployeeManagement.Entity.EmployeeManagementEntity;
import com.EmployeeManagement.Service.EmployeeManagementService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "http://localhost:5173")
public class EmployeeManagementController {

    @Autowired
    private EmployeeManagementService service;

    // ADD EMPLOYEE
    @PostMapping
    public EmployeeManagementEntity addEmployee(
            @Valid @RequestBody EmployeeManagementEntity employee) {

        return service.addEmployee(employee);
    }

    // GET ALL EMPLOYEES
    @GetMapping
    public List<EmployeeManagementEntity> getAllEmployees() {

        return service.getAllEmployees();
    }

    // GET EMPLOYEE BY ID
    @GetMapping("/{id}")
    public EmployeeManagementEntity getEmployeeById(
            @PathVariable Long id) {

        return service.getEmployeeById(id);
    }

    // SEARCH BY NAME
    @GetMapping("/search")
    public List<EmployeeManagementEntity> searchByName(
            @RequestParam String name) {

        return service.searchByName(name);
    }

    // SEARCH BY DEPARTMENT
    @GetMapping("/department/{department}")
    public List<EmployeeManagementEntity> searchByDepartment(
            @PathVariable String department) {

        return service.searchByDepartment(department);
    }

    // UPDATE EMPLOYEE
    @PutMapping("/{id}")
    public EmployeeManagementEntity updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeManagementEntity employee) {

        return service.updateEmployee(id, employee);
    }

    // DELETE EMPLOYEE
    @DeleteMapping("/{id}")
    public String deleteEmployee(
            @PathVariable Long id) {

        service.deleteEmployee(id);

        return "Employee deleted successfully";
    }
}