package com.dong.designMode.proxyPattern;

/**
 * 代理类 实现原本功能类，并添加新功能
 */
public class ProxyImage implements Image {
    private RealImage realImage;// = new RealImage("liu.jpg")
    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if(realImage == null){
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}
