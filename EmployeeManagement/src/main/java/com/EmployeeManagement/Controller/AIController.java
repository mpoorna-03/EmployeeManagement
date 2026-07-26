package com.EmployeeManagement.Controller;

import com.EmployeeManagement.Entity.EmployeePerformance;
import com.EmployeeManagement.Service.AIService;
import com.EmployeeManagement.Service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class AIController {

    @Autowired
    private AIService aiService;

    @Autowired
    private PerformanceService performanceService;


    // Test Ollama
    @GetMapping("/ai/test")
    public String testAI() {

        return aiService.testAI();
    }


    // Analyze all employee performance using AI
    @PostMapping("/ai/analyze")
    public String analyzeEmployees() {

        List<EmployeePerformance> performanceList =
                performanceService.getAllPerformance();

        ArrayList<EmployeePerformance> employees =
                new ArrayList<>(performanceList);

        return aiService.analyzeEmployees(employees);
    }
}
