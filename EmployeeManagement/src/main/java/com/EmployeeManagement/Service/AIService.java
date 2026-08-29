package com.EmployeeManagement.Service;

import com.EmployeeManagement.Entity.EmployeePerformance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class AIService {

    @Value("${ollama.url}")
    private String ollamaUrl;

    @Value("${ollama.model}")
    private String ollamaModel;

    public String testAI() {

        RestTemplate restTemplate = new RestTemplate();

        String prompt =
                "Say hello. You are an AI assistant for an Employee Management System.";

        Map<String, Object> request = new HashMap<>();

        request.put("model", ollamaModel);
        request.put("prompt", prompt);
        request.put("stream", false);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity =
                new HttpEntity<>(request, headers);

        ResponseEntity<Map> response =
                restTemplate.postForEntity(
                        ollamaUrl,
                        entity,
                        Map.class
                );

        if (response.getBody() != null &&
                response.getBody().get("response") != null) {

            return response.getBody()
                    .get("response")
                    .toString();
        }

        return "No response from Ollama";
    }

    public String analyzeEmployees(
            ArrayList<EmployeePerformance> employees) {

        RestTemplate restTemplate = new RestTemplate();

        StringBuilder prompt = new StringBuilder();

        prompt.append(
                "You are an AI assistant for an Employee Management System.\n\n"
        );

        prompt.append(
                "Analyze the following employee performance data.\n\n"
        );

        prompt.append(
                "For each employee:\n"
                        + "1. Identify whether the employee is performing well, average, or poorly.\n"
                        + "2. Explain the reason based on attendance, task completion, quality score and project participation.\n"
                        + "3. Give suggestions for improvement.\n"
                        + "4. Identify the best performing employee.\n"
                        + "5. Identify employees who need improvement.\n"
                        + "6. Give an overall summary.\n\n"
        );

        prompt.append("Employee Performance Data:\n\n");

        for (EmployeePerformance employee : employees) {

            prompt.append("Employee ID: ")
                    .append(employee.getEmployeeId())
                    .append("\n");

            prompt.append("Attendance: ")
                    .append(employee.getAttendance())
                    .append("\n");

            prompt.append("Tasks Assigned: ")
                    .append(employee.getTasksAssigned())
                    .append("\n");

            prompt.append("Tasks Completed: ")
                    .append(employee.getTasksCompleted())
                    .append("\n");

            prompt.append("Quality Score: ")
                    .append(employee.getQualityScore())
                    .append("\n");

            prompt.append("Project Participation: ")
                    .append(employee.getProjectParticipation())
                    .append("\n");

            prompt.append("Performance Score: ")
                    .append(employee.getPerformanceScore())
                    .append("\n");

            prompt.append("Performance Category: ")
                    .append(employee.getPerformanceCategory())
                    .append("\n");

            prompt.append("-------------------------\n");
        }

        Map<String, Object> request = new HashMap<>();

        request.put("model", ollamaModel);
        request.put("prompt", prompt.toString());
        request.put("stream", false);

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity =
                new HttpEntity<>(request, headers);

        ResponseEntity<Map> response =
                restTemplate.postForEntity(
                        ollamaUrl,
                        entity,
                        Map.class
                );

        if (response.getBody() != null &&
                response.getBody().get("response") != null) {

            return response.getBody()
                    .get("response")
                    .toString();
        }

        return "No response from Ollama";
    }
}