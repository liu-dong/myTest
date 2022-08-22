package com.dong.javaThink.eighth;

/**
 * 青蛙
 * @author liudong 2022/8/20
 */
public class Frog extends Amphibian {
    private Characteristic c = new Characteristic("Croaks");
    private Description d = new Description("Eat Bugs");

    public Frog() {
        System.out.println("Frog()");
    }

    @Override
    protected void dispose() {
        System.out.println("Frog dispose");
        d.dispose();
        c.dispose();
        super.dispose();
    }

    public static void main(String[] args) {
        Frog frog = new Frog();
        System.out.println("Bye!");
        frog.dispose();
    }
}

/**
 * 特征、特色
 */
class Characteristic{
    private String s;

    public Characteristic(String s) {
        this.s = s;
        System.out.println("Creating Characteristic " + s);
    }

    protected void dispose(){
        System.out.println("dispose Characteristic " + s);
    }
}

/**
 * 描述
 */
class Description{
    private String s;

    public Description(String s) {
        this.s = s;
        System.out.println("Creating Description " + s);
    }

    protected void dispose(){
        System.out.println("dispose Description " + s);
    }
}

/**
 * 生物
 */
class LivingCreature{
    private Characteristic c = new Characteristic("is alive");
    private Description d = new Description("Basic Living Creature");

    public LivingCreature() {
        System.out.println("LivingCreature()");
    }

    protected void dispose(){
        System.out.println("LivingCreature dispose");
        d.dispose();
        c.dispose();
    }
}

class Animal extends LivingCreature{
    private Characteristic c = new Characteristic("has heart");
    private Description d = new Description("Animal not Vegetable");

    public Animal() {
        System.out.println("Animal()");
    }

    protected void dispose(){
        System.out.println("Animal dispose");
        d.dispose();
        c.dispose();
        super.dispose();
    }
}

/**
 * 两栖动物
 */
class Amphibian extends Animal {
    private Characteristic c = new Characteristic("can live in water");
    private Description d = new Description("Both water and lang");

    public Amphibian() {
        System.out.println("Amphibian()");
    }

    protected void dispose(){
        System.out.println("Amphibian dispose");
        d.dispose();
        c.dispose();
        super.dispose();
    }
}

