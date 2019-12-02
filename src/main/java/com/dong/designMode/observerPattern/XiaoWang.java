package com.dong.designMode.observerPattern;

public class XiaoWang implements Person {

    private String name = "小王";
    @Override
    public void getMessage(String msg) {
        System.out.println(name + "接到了小美打过来的电话，电话内容是：" + msg);
    }
}
