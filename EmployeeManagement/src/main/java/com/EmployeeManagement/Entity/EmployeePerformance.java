package com.EmployeeManagement.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_performance")
public class EmployeePerformance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;

    private double attendance;

    private int tasksAssigned;

    private int tasksCompleted;

    private double qualityScore;

    private double projectParticipation;

    private double performanceScore;

    private String performanceCategory;

    // Default constructor
    public EmployeePerformance() {
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public double getAttendance() {
        return attendance;
    }

    public void setAttendance(double attendance) {
        this.attendance = attendance;
    }

    public int getTasksAssigned() {
        return tasksAssigned;
    }

    public void setTasksAssigned(int tasksAssigned) {
        this.tasksAssigned = tasksAssigned;
    }

    public int getTasksCompleted() {
        return tasksCompleted;
    }

    public void setTasksCompleted(int tasksCompleted) {
        this.tasksCompleted = tasksCompleted;
    }

    public double getQualityScore() {
        return qualityScore;
    }

    public void setQualityScore(double qualityScore) {
        this.qualityScore = qualityScore;
    }

    public double getProjectParticipation() {
        return projectParticipation;
    }

    public void setProjectParticipation(double projectParticipation) {
        this.projectParticipation = projectParticipation;
    }

    public double getPerformanceScore() {
        return performanceScore;
    }

    public void setPerformanceScore(double performanceScore) {
        this.performanceScore = performanceScore;
    }

    public String getPerformanceCategory() {
        return performanceCategory;
    }

    public void setPerformanceCategory(String performanceCategory) {
        this.performanceCategory = performanceCategory;
    }
}