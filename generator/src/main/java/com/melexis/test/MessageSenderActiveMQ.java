package com.melexis.test;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.logging.Logger;

public class MessageSenderActiveMQ {
    private static Connection connection;
    private static Session session;
    private MessageProducer producer;
    private Logger logger=Logger.getLogger(MessageSenderActiveMQ.class.getName());

    public void initialize(String url, String endpoint) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        connectionFactory.setTrustAllPackages(true);
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination queue = session.createQueue(endpoint);
        producer = session.createProducer(queue);

    }


    public void tearDown() throws JMSException {
        if (producer != null) {
            producer.close();
            producer = null;
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


    public void postMessage(String content) throws JMSException {

        TextMessage message = session.createTextMessage();
        message.setText(content);
       logger.info("send message:" + content);
        producer.send(message);

    }
}
