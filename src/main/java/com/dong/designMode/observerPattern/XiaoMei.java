package com.dong.designMode.observerPattern;

import java.util.ArrayList;
import java.util.List;

public class XiaoMei {

    private List<Person> personList = new ArrayList<>();

    public void addPerson(Person person){
        personList.add(person);
    }

    public void notifyPerson(){
        for (Person person : personList) {
            person.getMessage("有人打游戏吗？");
        }
    }
}
