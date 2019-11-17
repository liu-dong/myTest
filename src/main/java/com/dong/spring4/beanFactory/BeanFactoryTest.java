package com.dong.spring4.beanFactory;

import com.dong.spring4.reflect.Car;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.testng.annotations.Test;

public class BeanFactoryTest {

    @Test
    public void getBean() throws Throwable {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        Resource resource = resolver.getResource("classpath:com/dong/spring4/beanFactory/beans.xml");//不能加载非resource下的xml文件
        Resource resource = resolver.getResource("classpath:beans.xml");
        System.out.println(resource.getURL());
        System.out.println(resource.getURI());

        //BeanFactory beanFactory = new XmlBeanFactory(resource);
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(resource);

        System.out.println("init BeanFactory");

        Car car = factory.getBean("car",Car.class);
        System.out.println("car bean is ready for use");
        car.introduce();

    }
}
