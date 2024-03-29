package com.dong.javaThink.seventh;

/**
 * @author liudong 2022/8/18
 */
public class CADSystem extends Shape {

    private Circle c;
    private Triangle t;
    private Line[] lines = new Line[3];

    public CADSystem(int i) {
        super(i+1);
        for (int j = 0; j < lines.length; j++) {
            lines[j] = new Line(j, j * j);
        }
        c = new Circle(1);
        t = new Triangle(1);
        System.out.println("Combined constructor");
    }

    @Override
    void dispose() {
        System.out.println("CADSystem dispose()");
        t.dispose();
        c.dispose();
        for (Line line : lines) {
            line.dispose();
        }
        super.dispose();
    }

    public static void main(String[] args) {
        CADSystem x = new CADSystem(47);
        try {

        }finally {
            x.dispose();
        }
    }
}

class Shape {
    public Shape(int i) {
        System.out.println("Shape constructor");
    }

    void dispose() {
        System.out.println("Shape dispose");
    }
}

class Circle extends Shape {

    public Circle(int i) {
        super(i);
        System.out.println("Drawing Circle");
    }

    @Override
    void dispose() {
        System.out.println("Erasing Circle");
        super.dispose();
    }
}

class Triangle extends Shape {

    public Triangle(int i) {
        super(i);
        System.out.println("Drawing Triangle");
    }

    @Override
    void dispose() {
        System.out.println("Erasing Triangle");

        super.dispose();
    }
}


class Line extends Shape {

    private int start, end;

    public Line(int start, int end) {
        super(start);
        this.start = start;
        this.end = end;
        System.out.println("Drawing Line:" + start + ", " + end);
    }

    @Override
    void dispose() {
        System.out.println("Erasing Line:" + start + ", " + end);
        super.dispose();
        }
}


