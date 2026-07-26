package com.EmployeeManagement.Service;

import com.EmployeeManagement.Entity.EmployeePerformance;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PerformanceService {

    private final ArrayList<EmployeePerformance> performanceList =
            new ArrayList<>();

    // Add multiple performance records
    public List<EmployeePerformance> addPerformanceList(
            List<EmployeePerformance> newPerformanceList) {

        for (EmployeePerformance performance : newPerformanceList) {

            // Calculate task completion percentage
            double taskCompletion = 0;

            if (performance.getTasksAssigned() > 0) {

                taskCompletion =
                        ((double) performance.getTasksCompleted()
                                / performance.getTasksAssigned()) * 100;
            }

            // Calculate performance score
            double score =
                    (performance.getAttendance() * 0.25)
                            + (taskCompletion * 0.30)
                            + (performance.getQualityScore() * 0.30)
                            + (performance.getProjectParticipation() * 0.15);

            // Round score
            score = Math.round(score * 100.0) / 100.0;

            performance.setPerformanceScore(score);

            // Performance category
            if (score >= 85) {

                performance.setPerformanceCategory("Excellent");

            } else if (score >= 70) {

                performance.setPerformanceCategory("Good");

            } else if (score >= 50) {

                performance.setPerformanceCategory("Average");

            } else {

                performance.setPerformanceCategory("Needs Improvement");
            }

            // Add to ArrayList
            performanceList.add(performance);
        }

        return newPerformanceList;
    }

    // Get all performance records
    public List<EmployeePerformance> getAllPerformance() {

        return performanceList;
    }

    // Get performance by employee ID
    public EmployeePerformance getByEmployeeId(Long employeeId) {

        for (EmployeePerformance performance : performanceList) {

            if (performance.getEmployeeId().equals(employeeId)) {

                return performance;
            }
        }

        return null;
    }
}