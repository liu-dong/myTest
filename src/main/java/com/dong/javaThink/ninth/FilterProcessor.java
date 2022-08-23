package com.dong.javaThink.ninth;

/**
 * @author liudong 2022/8/23
 */
public class FilterProcessor {

    public static void main(String[] args) {
        Waveform w = new Waveform();
        Apply1.process(new FilterAdapter(new LowPass(1.0)), w);
        Apply1.process(new FilterAdapter(new HighPass(2.0)), w);
        Apply1.process(new FilterAdapter(new BandPass(3.0,4.0)), w);
    }
}

class FilterAdapter implements Processor1 {
    Filter filter;

    public FilterAdapter(Filter filter) {
        this.filter = filter;
    }

    @Override
    public String name() {
        return filter.name();
    }

    @Override
    public Waveform process(Object input) {
        return filter.process((Waveform) input);
    }
}
