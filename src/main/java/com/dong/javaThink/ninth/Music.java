package com.dong.javaThink.ninth;

/**
 * @author liudong 2022/8/22
 */
public class Music {
    static void tune(Instrument i){
        i.play(Note.MIDDLE_C);
    }
    static void tuneAll(Instrument[] e){
        for (Instrument i : e) {
            tune(i);
        }
    }

    public static void main(String[] args) {
        Instrument[] orchestra = {
                new Wind(),
                new Percussion(),
                new Stringed(),
                new Brass(),
                new Woodwind()
        };
        tuneAll(orchestra);
    }
}

enum Note {
    MIDDLE_C, C_SHARP, B_FLAT;
}

abstract class Instrument {
    private int i;

    public abstract void play(Note n);

    public String what() {
        return "Instrument";
    }

    public abstract void adjust();
}

/**
 * 管乐
 */
class Wind extends Instrument {

    @Override
    public void play(Note n) {
        System.out.println("Wind.play() " + n);
    }

    @Override
    public void adjust() {

    }
}

/**
 * 打击乐
 */
class Percussion extends Instrument {

    @Override
    public void play(Note n) {
        System.out.println("Percussion.play() " + n);
    }


    public String what() {
        return "Percussion";
    }

    @Override
    public void adjust() {

    }
}

/**
 * 弦乐
 */
class Stringed extends Instrument {

    @Override
    public void play(Note n) {
        System.out.println("Stringed.play() " + n);
    }

    public String what() {
        return "Stringed";
    }

    @Override
    public void adjust() {

    }
}

/**
 * 铜管乐器
 */
class Brass extends Wind {
    @Override
    public void play(Note n) {
        System.out.println("Brass.play() " + n);
    }

    @Override
    public void adjust() {
        System.out.println("Brass.adjust() ");
    }
}

/**
 * 木管乐器
 */
class Woodwind extends Wind {
    @Override
    public void play(Note n) {
        System.out.println("Woodwind.play() " + n);
    }

    public String what() {
        return "Woodwind";
    }
}
