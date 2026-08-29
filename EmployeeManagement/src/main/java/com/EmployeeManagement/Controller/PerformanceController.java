package com.EmployeeManagement.Controller;

import com.EmployeeManagement.Entity.EmployeePerformance;
import com.EmployeeManagement.Service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/performance")
public class PerformanceController {

    @Autowired
    private PerformanceService service;

    @PostMapping
    public List<EmployeePerformance> addPerformance(
            @RequestBody List<EmployeePerformance> performanceList) {

        return service.addPerformanceList(performanceList);
    }

    @GetMapping
    public List<EmployeePerformance> getAllPerformance() {

        return service.getAllPerformance();
    }

    @GetMapping("/{employeeId}")
    public EmployeePerformance getByEmployeeId(
            @PathVariable Long employeeId) {

        return service.getByEmployeeId(employeeId);
    }
}