package com.example.interactive_logistic_timeline;

public class TimelineModel {

    private String title;
    private String time;
    private String description;
    private Status status;
    private boolean isExpanded; // NEW: Tracks animation state

    public enum Status {
        COMPLETED, ACTIVE, PENDING
    }

    public TimelineModel(String title, String time, String description, Status status) {
        this.title = title;
        this.time = time;
        this.description = description;
        this.status = status;
        this.isExpanded = false; // Default is closed
    }

    public String getTitle() { return title; }
    public String getTime() { return time; }
    public String getDescription() { return description; }
    public Status getStatus() { return status; }

    // NEW Getters/Setters for expansion
    public boolean isExpanded() { return isExpanded; }
    public void setExpanded(boolean expanded) { isExpanded = expanded; }
}