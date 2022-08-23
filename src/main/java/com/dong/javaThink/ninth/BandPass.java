package com.dong.javaThink.ninth;

/**
 * @author liudong 2022/8/23
 */
public class BandPass extends Filter {
    double lowCutoff,highCutoff;

    public BandPass(double lowCutoff, double highCutoff) {
        this.lowCutoff = lowCutoff;
        this.highCutoff = highCutoff;
    }


    @Override
    public Waveform process(Waveform input) {
        return input;
    }
}
