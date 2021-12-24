package com.dong.javaBase;

import com.dong.javaBase.interfacePackage.impl.Person;

/**
 * 多继承测试
 *
 * @author LD 2021/12/24
 */
public class MultipleInheritanceTest {

    public static void main(String[] args) {
        Person person = new Person();
        person.drink();
        person.smoke();
        person.speak();
    }

}
