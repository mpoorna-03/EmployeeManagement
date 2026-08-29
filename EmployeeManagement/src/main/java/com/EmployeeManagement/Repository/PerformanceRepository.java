package com.EmployeeManagement.Repository;

import com.EmployeeManagement.Entity.EmployeePerformance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PerformanceRepository
        extends JpaRepository<EmployeePerformance, Long> {

    List<EmployeePerformance> findByEmployeeId(Long employeeId);
}