package com.melexis.test;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class MachineEventVO {
    private MachineType machineType;
    private UUID uuid;
    private LocalDateTime dateTime;
    private ErrorType errorType;

}
