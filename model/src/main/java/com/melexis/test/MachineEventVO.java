package com.melexis.test;

import lombok.Data;

import java.time.LocalDateTime;

@Data
class MachineEventVO {
    private MachineType machineType;
    private String machineID;
    private LocalDateTime dateTime;
    private ErrorType errorType;

}
