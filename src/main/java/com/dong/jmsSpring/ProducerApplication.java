package com.dong.jmsSpring;

import com.dong.jmsSpring.producer.ProducerService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProducerApplication {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("jmsSpring/producer.xml");
        ProducerService producerService = applicationContext.getBean(ProducerService.class);
        for (int i = 1; i <= 20; i++) {
            producerService.sendMessage("我想你 " +i);
        }
        applicationContext.close();
    }
}
