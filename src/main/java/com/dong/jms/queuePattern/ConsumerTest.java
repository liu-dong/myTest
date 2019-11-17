package com.dong.jms.queuePattern;

import com.dong.jms.Common;
import org.springframework.jms.listener.adapter.AbstractAdaptableMessageListener;

import javax.jms.*;

/**
 * 队列模式下的消费者
 * @author LD
 */
public class ConsumerTest {

    private static final String URL = "tcp://localhost:61616";
    private static final String QUEUE_NAME = "queue-test";

    public static void main(String[] args) throws JMSException {
        Connection connection = Common.createConnection();
        //4、连接创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5、会话创建目的地
        Destination destination = session.createQueue(QUEUE_NAME);
        //6、创建一个消费者
        MessageConsumer consumer = session.createConsumer(destination);
        //7、创建一个监听器
        consumer.setMessageListener(new AbstractAdaptableMessageListener() {
            @Override
            public void onMessage(Message message, Session session) throws JMSException {
                //8、监听到消息并接收消息
                TextMessage textMessage = (TextMessage) message;
                System.out.println("接收消息："+textMessage.getText());
            }
        });

        //9、关闭连接
//        connection.close();
    }
}
