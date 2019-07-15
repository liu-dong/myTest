package com.dong.designMode.adapterPattern;

/**
 * MP4播放器
 */
public class Mp4Player implements  AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {

    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Play mp4 file. 文件名称：" + fileName);
    }
}
