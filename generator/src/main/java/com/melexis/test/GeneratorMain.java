package com.melexis.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.jms.JMSException;
import java.time.LocalDateTime;
import java.util.Random;

@SuppressWarnings("ALL")
class GeneratorMain {

    private static final int ACTIVEMQ_SERVER_URL = 0;
    private static final int QUEUE_NAME = 1;
    private static final int AMOUNT_OF_EVENTS_TO_GENERATE = 2;
    private static final int MACHINE_COUNT = 3;
    private static final String DEBUG_KEY = "debug";
    private static final int DEBUG_ARG_POS = 4;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Random random = new Random();
    private static boolean debug = false;

    private static <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    private static MachineEventVO createMachineEventVO(int machineCount) {
        MachineEventVO machineEventVO = new MachineEventVO();
        machineEventVO.setMachineID("MACHINE_" + random.nextInt(machineCount));
        machineEventVO.setDateTime(LocalDateTime.now());
        machineEventVO.setErrorType(randomEnum(ErrorType.class));
        machineEventVO.setMachineType(randomEnum(MachineType.class));
        return machineEventVO;
    }

    public static void main(String[] args) throws JMSException, JsonProcessingException, InterruptedException {
        if (args.length < 4) {
            throw new IllegalArgumentException(
                    "arguments to give to program: <activemq server url> <name of endpoint> <amount of events to generate> <amount of machines in parallel> [debug]");
        }

        if (args.length==5 && DEBUG_KEY.equals(args[DEBUG_ARG_POS])) {
            debug = true;
        }
        int amountOfEvents = Integer.valueOf(args[AMOUNT_OF_EVENTS_TO_GENERATE]);
        int machineCount = Integer.valueOf(args[MACHINE_COUNT]);
        //assuming each machine makes a message every 10mns:
        int meanTimeBetweenMachinesEvents = 10 * 60 / machineCount;
        MessageHandler messageHandler = new MessageHandler();
        messageHandler.initialize(args[ACTIVEMQ_SERVER_URL], args[QUEUE_NAME]);
        for (int i = 0; i < amountOfEvents; i++) {
            messageHandler.postMessage(objectMapper.writeValueAsString(createMachineEventVO(machineCount)));
            if (debug) {
                messageHandler.receiveMessage();
            }
            Thread.sleep((meanTimeBetweenMachinesEvents + random.nextInt(4) - 2) * 1000);
        }

        messageHandler.tearDown();
    }
}
