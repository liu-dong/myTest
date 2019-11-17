package com.dong.jmsSpring.consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

public class ConsumerMessageListener implements javax.jms.MessageListener {

    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println("接收消息："+textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
