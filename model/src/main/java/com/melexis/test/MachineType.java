package com.melexis.test;

public enum MachineType {
    MACHINE_TYPE1("t1"),
    MACHINE_TYPE2("t2"),
    MACHINE_TYPE3("tnew"),
    OTHER_TYPE("o1"),
    AND_ANOTHER_ONE("another2");

    private final String id;

    MachineType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
