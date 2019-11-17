package com.dong.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

public class Common {

    private static final String URL = "tcp://localhost:61616";

    /**
     * 创建activemq连接

     * @return
     * @throws JMSException
     */
    public static Connection createConnection() throws JMSException {//* @param patternName 模式名称：队列模式、主题模式
        //1、创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);
        //2、连接工厂创建连接
        Connection connection = connectionFactory.createConnection();
        //3、启动连接
        connection.start();
        //4、连接创建会话
//        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//        //5、会话创建目的地
//        Destination destination = session.createQueue(patternName);

        return connection;
    }
}
