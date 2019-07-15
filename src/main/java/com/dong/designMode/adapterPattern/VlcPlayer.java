package com.dong.designMode.adapterPattern;

/**
 * VLC播放器
 */
public class VlcPlayer implements AdvancedMediaPlayer {

    @Override
    public void playVlc(String fileName) {
        System.out.println("Play vlc file. 文件名称：" + fileName);
    }

    @Override
    public void playMp4(String fileName) {

    }
}
