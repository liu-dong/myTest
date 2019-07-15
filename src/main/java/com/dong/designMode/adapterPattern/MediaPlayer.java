package com.dong.designMode.adapterPattern;

/**
 * 媒体播放器接口
 */
public interface MediaPlayer {

    /**
     * 播放方法
     * @param audioType
     * @param fileName
     */
    void play(String audioType, String fileName);
}
