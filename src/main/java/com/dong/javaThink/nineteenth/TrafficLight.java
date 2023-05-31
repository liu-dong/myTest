package com.dong.javaThink.nineteenth;

//import static com.dong.javaThink.nineteenth.Signal.*;

import static com.dong.javaThink.nineteenth.Signal.*;

/**
 * @author liudong 2023/5/31
 */
public class TrafficLight {

    Signal color = RED;

    public void change() {
        switch (color) {
            case RED:
                color = GREEN;
                break;
            case GREEN:
                color = YELLOW;
                break;
            case YELLOW:
                color = RED;
                break;
        }
    }

    @Override
    public String toString() {
        return "The traffic light is " + color;
    }

    public static void main(String[] args) {
        TrafficLight t = new TrafficLight();
        for (int i = 0; i < 7; i++) {
            System.out.println(t);
            t.change();
        }
    }
}
