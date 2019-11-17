package com.dong.jms.topicsPattern;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 队列模式下的生产者
 *
 * @author LD
 */
public class ProducerTest {

    private static final String URL = "tcp://localhost:61616";
    private static final String TOPICS_NAME = "topics-test";

    public static void main(String[] args) throws JMSException {
        //1、创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);
        //2、连接工厂创建连接
        Connection connection = connectionFactory.createConnection();
        //3、启动连接
        connection.start();
        //4、连接创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5、会话创建目的地
        Destination destination = session.createTopic(TOPICS_NAME);
        //6、会话创建生产者
        MessageProducer producer = session.createProducer(destination);
        //7、向目的地发送消息
        for (int i = 0; i < 100; i++) {
            //8、创建消息
            TextMessage textMessage = session.createTextMessage("主题消息-" + i);
            //9、发布消息
            producer.send(textMessage);
            System.out.println("消息发送成功：" + textMessage.getText());
        }
        //10、关闭连接
        connection.close();
    }
}
