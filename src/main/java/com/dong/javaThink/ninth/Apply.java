package com.dong.javaThink.ninth;

import java.util.Arrays;

/**
 * 处理器
 */
class Processor {
    public String name() {
        return getClass().getSimpleName();
    }

    /**
     * 程序
     *
     * @param input
     * @return
     */
    Object process(Object input) {
        return input;
    }
}

/**
 * 大写
 */
class Upcase extends Processor {
    /**
     * 程序
     *
     * @param input
     * @return
     */
    Object process(Object input) {
        return ((String) input).toUpperCase();
    }
}

/**
 * 小写
 */
class Downcase extends Processor {
    /**
     * 程序
     *
     * @param input
     * @return
     */
    Object process(Object input) {
        return ((String) input).toLowerCase();
    }
}

/**
 * 分割器
 */
class Splitter extends Processor {
    /**
     * 程序
     *
     * @param input
     * @return
     */
    Object process(Object input) {
        return Arrays.toString(((String) input).split(" "));
    }
}

/**
 * @author liudong 2022/8/23
 */
public class Apply {
    public static void process(Processor p, Object s) {
        System.out.println("Using Processor " + p.name());
        System.out.println(p.process(s));
    }

    public static String s = "Disagreement with beliefs is by definition incorrect";

    public static void main(String[] args) {
        process(new Upcase(), s);
        process(new Downcase(), s);
        process(new Splitter(), s);

    }
}
