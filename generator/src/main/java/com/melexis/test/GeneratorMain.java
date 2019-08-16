package com.melexis.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.jms.JMSException;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

public class GeneratorMain {

    private static final int ACTIVEMQ_SERVER_URL = 0;
    private static final int QUEUE_NAME = 1;
    private static final ObjectMapper objectMapper=new ObjectMapper();
    private static final Random random=new Random();
    public static final int AMOUNT_OF_EVENTS_TO_GENERATE = 2;

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    private static MachineEventVO createMachineEventVO(){
        MachineEventVO machineEventVO=new MachineEventVO();
        machineEventVO.setUuid(UUID.randomUUID());
        machineEventVO.setDateTime(LocalDateTime.now());
        machineEventVO.setErrorType(randomEnum(ErrorType.class));
        machineEventVO.setMachineType(randomEnum(MachineType.class));
        return machineEventVO;
    }

    public static void main(String[] args) throws JMSException, JsonProcessingException, InterruptedException {
        if (args.length < 3) {
            throw new IllegalArgumentException(
                    "arguments to give to program: <activemq server url> <name of endpoint> <amount of events to generate>");
        }
        int amountOfEvents=Integer.valueOf(args[AMOUNT_OF_EVENTS_TO_GENERATE]);

        MessageSenderActiveMQ messageSenderActiveMQ = new MessageSenderActiveMQ();
        messageSenderActiveMQ.initialize(args[ACTIVEMQ_SERVER_URL], args[QUEUE_NAME]);
        for (int i=0;i<amountOfEvents;i++){
            messageSenderActiveMQ.postMessage(objectMapper.writeValueAsString(createMachineEventVO()));
            //assume some machine generates a message every few seconds:
            Thread.sleep((random.nextInt(3)+1)*1000);
        }

        messageSenderActiveMQ.tearDown();
    }
}
