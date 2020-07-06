package com.dong.designMode.observerPattern;

public class XiaoLi implements Observer {

    private String name = "小李";
    @Override
    public void getMessage(String msg) {
        System.out.println(name + "接到了小美打过来的电话，电话内容是：" + msg);
    }
}
