package com.dong.designMode.adapterPattern;

/**
 * 更高级的媒体播放器接口
 */
public interface AdvancedMediaPlayer {

    //播放VLC格式
    void playVlc(String fileName);

    //播放MP4
    void playMp4(String fileName);
}
