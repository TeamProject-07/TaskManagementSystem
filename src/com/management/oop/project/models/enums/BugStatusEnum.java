package com.management.oop.project.models.enums;

public enum BugStatusEnum {
    ACTIVE,
    FIXED;

    @Override
    public String toString() {
        switch (this) {
            case ACTIVE:
                return "Active";
            case FIXED:
                return "Fixed";
            default:
                return "";
        }
    }
}

