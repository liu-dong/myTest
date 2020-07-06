package com.dong.designMode.observerPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者
 *
 * @author LD
 * @date 2020/7/6 9:45
 */
public abstract class Subject {

    private List<Observer> observerList = new ArrayList<>();

    /**
     * 添加观察者
     * @param observer
     */
    public void attachObserver(Observer observer){
        observerList.add(observer);
    }

    /**
     * 移除观察者
     * @param observer
     */
    public void detachObserver(Observer observer){
        observerList.remove(observer);
    }

    /**
     * 通知观察者
     * @param msg
     */
    public void notifyObservers(String msg){
        for (Observer observer: observerList){
            observer.getMessage(msg);
        }
    }
}
