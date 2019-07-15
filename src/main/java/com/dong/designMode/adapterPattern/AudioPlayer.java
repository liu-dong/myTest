package com.dong.designMode.adapterPattern;

public class AudioPlayer implements MediaPlayer {

    private MediaAdapter mediaAdapter;

    @Override
    public void play(String audioType, String fileName) {
        //播放 mp3 音乐文件的内置支持
        if(audioType.equalsIgnoreCase("mp3")){
            System.out.println("Play mp3 file. 文件名称: "+ fileName);
        }else if(audioType.equalsIgnoreCase("vlc") || audioType.equalsIgnoreCase("mp4")){
            mediaAdapter = new MediaAdapter(audioType);//mediaAdapter 提供了播放其他文件格式的支持
            mediaAdapter.play(audioType, fileName);
        }else{
            System.out.println("Invalid media. "+ audioType + " format not supported");
        }
    }
}
