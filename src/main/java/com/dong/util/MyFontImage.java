package com.dong.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

/**
 * 
 * @author LD
 * @date 2020/6/4 9:27
 */
public class MyFontImage {
    public static void createFontImg(String path) {
        /*定义在图片中想展示的内容*/
        String base = "我爱你";
        try {
            /*读取想要转换的图片
            read() 方法回返回一个 BufferedImage 类型的图片缓存流。
            我们通过new File(path)，来创建一个文件流。*/
            BufferedImage image = ImageIO.read(new File(path));
            /*创建一个图片缓存流BufferedImage 用于放置输出的文字图片*/
            BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
            /*创建一个2D坐标转换及绘图相关的类 Graphics2D，用来设置每个像素点的颜色、字体大小和字体样式*/
            Graphics2D graphics2D = (Graphics2D) newImage.getGraphics();
            /*设置字体风格、样式和大小*/
            graphics2D.setFont(new Font("宋体", Font.BOLD, 12));
            int index = 0;
            /*循环遍历每一个像素点，将每隔12个像素点就替换为文字*/
            for (int y = 0; y < image.getHeight(); y += 12) {
                for (int x = 0; x < image.getWidth(); x += 12) {
                    /*获取图片当前位置像素的颜色*/
                    int pxColor = image.getRGB(x, y);
                    /*分离出rgb三种颜色，分别进行灰度和二值化处理*/
                    int r = (pxColor & 0xff0000) >> 16,
                            g = (pxColor & 0xff00) >> 8,
                            b = pxColor & 0xff;
                    /*通过graphics2d设置字体颜色*/
                    graphics2D.setColor(new Color(r, g, b));
                    /*在当前位置上绘制文字*/
                    graphics2D.drawString(String.valueOf(base.charAt(index % base.length())), x, y);
                    index++;
                }
            }
            /*图片重新绘制，并输出*/
            ImageIO.write(newImage, "JPG", new FileOutputStream("F:\\myLove.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MyFontImage.createFontImg("E:\\LD\\我的相册\\MyLove\\myLove.jpg");
        System.out.println("OK");
    }
}
