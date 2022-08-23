package com.dong.javaThink.eighth;

/**
 * @author liudong 2022/8/22
 */
public class PolyConstructors {
    public static void main(String[] args) {
        new RoundGlyph(5);
    }
}

/**
 * 图形
 */
class Glyph{
    void draw(){
        System.out.println("Glyph.draw()");
    }

    public Glyph() {
        System.out.println("Glyph() before draw()");
        draw();
        System.out.println("Glyph() after draw()");
    }
}

/**
 * 球形
 */
class RoundGlyph extends Glyph{
    private int radius = 1;

    public RoundGlyph(int r) {
        radius = r;
        System.out.println("RoundGlyph.RoundGlyph(). radius = " + radius);
    }
    void draw(){
        System.out.println("RoundGlyph.draw(). radius = " + radius);
    }
}
