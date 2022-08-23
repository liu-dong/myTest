package com.dong.javaThink.ninth;

/**
 * @author liudong 2022/8/23
 */
public class Filter {

    public String name(){
        return getClass().getSimpleName();
    }

    public Waveform process(Waveform input){
        return input;
    }
}
