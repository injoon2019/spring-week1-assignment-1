package com.codesoom.assignment.models;

public class TaskIdGenerator {
    private static Long sequence = 0L;

    public synchronized static Long generateSequence() {
        return sequence++;
    }

    public synchronized static Long getLastSequence() {
        return sequence - 1;
    }
}
