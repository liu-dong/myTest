package com.dong.javaThink.seventh;

/**
 * @author liudong 2022/8/17
 */
class Cleanser {

    private String s = "Cleanser";
    public void append(String a){s+=a;}
    public void dilute(){append(" dilute()");}
    public void apply(){append(" apply()");}
    public void scrub(){append(" scrub()");}

    public Cleanser() {
        System.out.println("Cleanser constructor");
    }

    public String toString() {
        return s;
    }

    public static void main(String[] args) {
        Cleanser cleanser = new Cleanser();
        cleanser.dilute();cleanser.apply();cleanser.scrub();
        System.out.println(cleanser);
    }
}

public class Detergent{
    private Cleanser cleanser = new Cleanser();


    public Detergent() {
        System.out.println("Detergent constructor");
    }
    /*public void scrub(){
        append(" Detergent.scrub() ");
        super.scrub();
    }
    public void foam(){
        append(" foam()");
    }*/
/*
    public static void main(String[] args) {
        Detergent detergent = new Detergent();
        detergent.dilute();detergent.apply();detergent.scrub();
        detergent.foam();
        System.out.println(detergent);
        Cleanser.main(args);
    }*/
}
