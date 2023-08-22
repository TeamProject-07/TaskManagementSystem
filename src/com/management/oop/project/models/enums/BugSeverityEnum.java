package com.management.oop.project.models.enums;

public enum BugSeverityEnum {
    CRITICAL,
    MAJOR,
    MINOR;

    @Override
    public String toString() {
        switch (this) {
            case CRITICAL:
                return "Critical";
            case MAJOR:
                return "Major";
            case MINOR:
                return "Minor";
            default:
                return "";
        }
    }
}
