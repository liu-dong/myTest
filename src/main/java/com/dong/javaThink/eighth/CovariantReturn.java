package com.dong.javaThink.eighth;

/**
 * 协变返回类型
 *
 * @author liudong 2022/8/22
 */
public class CovariantReturn {
    public static void main(String[] args) {
        Mill m = new Mill();
        Grain g = m.process();
        System.out.println(g);
        m = new WheatMill();
        g = m.process();
        System.out.println(g);

    }
}

/**
 * 谷物
 */
class Grain {
    public String toString() {
        return "Grain";
    }
}

/**
 * 小麦
 */
class Wheat extends Grain {
    @Override
    public String toString() {
        return "Wheat";
    }
}

/**
 * 磨粉场
 */
class Mill {
    Grain process() {
        return new Grain();
    }
}

class WheatMill extends Mill {
    Wheat process() {
        return new Wheat();
    }
}
