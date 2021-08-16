package com.codesoom.assignment.models;

public class Task {
    private Long id;
    private String title;

    public Task(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
