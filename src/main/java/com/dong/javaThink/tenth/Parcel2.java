package com.dong.javaThink.tenth;

/**
 * 邮包
 *
 * @author liudong 2022/8/23
 */
public class Parcel2 {
    class Contents {
        private int i = 11;

        public int value() {
            return i;
        }
    }

    /**
     * 目的地
     */
    class Destination {
        private String label;

        public Destination(String whereTo) {
            this.label = whereTo;
        }

        String readLabel() {
            return label;
        }
    }

    public Destination to(String s) {
        return new Destination(s);
    }

    public Contents contents() {
        return new Contents();
    }

    public void ship(String dest) {
        Parcel2 q = new Parcel2();
        Contents contents = q.contents();
        Destination borneo = q.to("Borneo");

        Contents c = contents();
        Destination d = to(dest);
        System.out.println(d.readLabel());
    }

    public static void main(String[] args) {
        Parcel2 p = new Parcel2();
        p.ship("Tasmania");
        Parcel2 q = new Parcel2();
        Contents contents = q.contents();
        Destination borneo = q.to("Borneo");
    }
}
