package com.EmployeeManagement.Service;

import com.EmployeeManagement.Entity.EmployeeManagementEntity;
import com.EmployeeManagement.Repository.EmployeeManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeManagementService {

    @Autowired
    private EmployeeManagementRepository repository;

    // ADD EMPLOYEE
    public EmployeeManagementEntity addEmployee(
            EmployeeManagementEntity employee) {

        if (repository.existsByEmail(employee.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        return repository.save(employee);
    }

    // GET ALL EMPLOYEES
    public List<EmployeeManagementEntity> getAllEmployees() {
        return repository.findAll();
    }

    // GET EMPLOYEE BY ID
    public EmployeeManagementEntity getEmployeeById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found with ID: " + id));
    }

    // SEARCH BY NAME
    public List<EmployeeManagementEntity> searchByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    // SEARCH BY DEPARTMENT
    public List<EmployeeManagementEntity> searchByDepartment(
            String department) {

        return repository.findByDepartmentIgnoreCase(department);
    }

    // UPDATE EMPLOYEE
    public EmployeeManagementEntity updateEmployee(
            Long id,
            EmployeeManagementEntity employee) {

        EmployeeManagementEntity existingEmployee =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Employee not found with ID: " + id));

        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setDepartment(employee.getDepartment());
        existingEmployee.setSalary(employee.getSalary());

        return repository.save(existingEmployee);
    }

    // DELETE EMPLOYEE
    public void deleteEmployee(Long id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException(
                    "Employee not found with ID: " + id);
        }

        repository.deleteById(id);
    }
}