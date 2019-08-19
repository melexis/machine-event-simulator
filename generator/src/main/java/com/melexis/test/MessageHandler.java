package com.melexis.test;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.logging.Logger;

class MessageHandler {
    private static Connection connection;
    private static Session session;
    private MessageProducer producer;
    private MessageConsumer consumer;

    private final Logger logger = Logger.getLogger(MessageHandler.class.getName());

    public void initialize(String url, String endpoint) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        connectionFactory.setTrustAllPackages(true);
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination queue = session.createQueue(endpoint);
        producer = session.createProducer(queue);
        consumer = session.createConsumer(queue);
    }


    public void tearDown() throws JMSException {
        if (producer != null) {
            producer.close();
            producer = null;
        }
        if (consumer != null) {
            consumer.close();
            consumer = null;
        }
        if (session != null) {
            session.close();
            session = null;
        }
        if (connection != null) {
            connection.close();
            connection = null;
        }

    }

    public void receiveMessage() throws JMSException {
        logger.info("received message:"+consumer.receive());
    }

    public void postMessage(String content) throws JMSException {

        TextMessage message = session.createTextMessage();
        message.setText(content);
        logger.info("send message:" + content);
        producer.send(message);

    }
}
