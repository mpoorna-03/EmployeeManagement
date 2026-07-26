package com.EmployeeManagement.Repository;

import com.EmployeeManagement.Entity.EmployeeManagementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeManagementRepository
        extends JpaRepository<EmployeeManagementEntity, Long> {

    List<EmployeeManagementEntity> findByNameContainingIgnoreCase(String name);

    List<EmployeeManagementEntity> findByDepartmentIgnoreCase(String department);

    boolean existsByEmail(String email);
}