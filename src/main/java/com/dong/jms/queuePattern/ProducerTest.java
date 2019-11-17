package com.dong.jms.queuePattern;

import com.dong.jms.Common;

import javax.jms.*;

/**
 * 队列模式下的生产者
 *
 * @author LD
 */
public class ProducerTest {

    private static final String URL = "tcp://localhost:61616";
    private static final String QUEUE_NAME = "queue-test";

    public static void main(String[] args) throws JMSException {
        Connection connection = Common.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5、会话创建目的地
        Destination destination = session.createQueue(QUEUE_NAME);
        //6、会话创建生产者
        MessageProducer producer = session.createProducer(destination);
        //7、向目的地发送消息
        for (int i = 0; i < 100; i++) {
            //8、创建消息
            TextMessage textMessage = session.createTextMessage("队列消息-" + i);
            //9、发布消息
            producer.send(textMessage);
            System.out.println("消息发送成功：" + textMessage.getText());
        }
        //10、关闭连接
        connection.close();
    }
}
