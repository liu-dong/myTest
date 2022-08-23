package com.dong.javaThink.ninth;

import java.util.Arrays;

/**
 * @author liudong 2022/8/23
 */
public abstract class StringProcessor implements Processor1{

    @Override
    public String name() {
        return null;
    }

    public abstract String process(Object input);

    public static String s = "If she weighs the same as a duck. she's made of wood";

    public static void main(String[] args) {
        Apply1.process(new Upcase1(), s);
        Apply1.process(new Downcase1(), s);
        Apply1.process(new Splitter1(), s);

    }
}

/**
 * 大写
 */
class Upcase1 extends StringProcessor {
    /**
     * 程序
     *
     * @param input
     * @return
     */
    public String process(Object input) {
        return ((String) input).toUpperCase();
    }
}

/**
 * 小写
 */
class Downcase1 extends StringProcessor {
    /**
     * 程序
     *
     * @param input
     * @return
     */
    public String process(Object input) {
        return ((String) input).toLowerCase();
    }
}

/**
 * 分割器
 */
class Splitter1 extends StringProcessor {
    /**
     * 程序
     *
     * @param input
     * @return
     */
    public String process(Object input) {
        return Arrays.toString(((String) input).split(" "));
    }
}
