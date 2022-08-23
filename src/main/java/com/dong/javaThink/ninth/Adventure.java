package com.dong.javaThink.ninth;

/**
 * 冒险
 *
 * @author liudong 2022/8/23
 */
public class Adventure {
    public static void t(CanFight x){x.fight();}
    public static void u(CanSwim x){x.swim();}
    public static void v(CanFly x){x.fly();}
    public static void w(ActionCharacter x){x.fight();}
    public static void main(String[] args) {
        Hero hero = new Hero();
        t(hero);
        u(hero);
        v(hero);
        w(hero);
    }
}

interface CanFight {
    void fight();
}

interface CanSwim {
    void swim();
}

interface CanFly {
    void fly();
}

/**
 * 行动角色
 */
class ActionCharacter {
    public void fight() {
        System.out.println("ActionCharacter.fight");
    }
}

class Hero extends ActionCharacter implements CanFight, CanSwim, CanFly {

    public void swim() {

    }

    public void fly() {

    }

    /*public void fight() {
        System.out.println("Hero.fight");
    }*/
}
