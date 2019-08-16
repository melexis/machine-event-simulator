package com.melexis.test;

public enum ErrorType {
    PART_STUCK("part is stuck in the machine"),
    MACHINE_EMPTY("no part in machine loader"),
    SINGULATOR_ERROR("cannot move the part in the machine"),
    TEMPERATURE_ERROR("machine cannot go at expected temperature"),
    SORTER_ERROR("part cannot be sorted");

    private final String errorDescription;

    ErrorType(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getErrorDescription() {
        return errorDescription;
    }
}
