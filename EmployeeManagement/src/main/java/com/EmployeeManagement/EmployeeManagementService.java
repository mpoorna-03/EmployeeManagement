package com.EmployeeManagement;

import com.EmployeeManagement.EmployeeManagementEntity;
import com.EmployeeManagement.EmployeeManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeManagementService {

    @Autowired
    private EmployeeManagementRepository repository;

    public EmployeeManagementEntity addEmployee(EmployeeManagementEntity employee) {
        return repository.save(employee);
    }

    public List<EmployeeManagementEntity> getAllEmployees() {
        return repository.findAll();
    }

    public Optional<EmployeeManagementEntity> getEmployeeById(int id) {
        return repository.findById(id);
    }

    public EmployeeManagementEntity updateEmployee(EmployeeManagementEntity employee) {
        return repository.save(employee);
    }

    public void deleteEmployee(int id) {
        repository.deleteById(id);
    }
}