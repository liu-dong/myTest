package com.dong.spring4.applicationContext;

import com.dong.spring4.reflect.Car;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

@Configuration
public class AnnotationApplicationContextTest {

    @Test
   public void getBean(){
       ApplicationContext context = new AnnotationConfigApplicationContext(Beans.class);
       Car car = context.getBean("car",Car.class);
       assertNotNull(car);
   }
}
