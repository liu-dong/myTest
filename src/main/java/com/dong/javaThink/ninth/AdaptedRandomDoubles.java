package com.dong.javaThink.ninth;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Scanner;

/**
 * @author liudong 2022/8/23
 */
public class AdaptedRandomDoubles extends RandomDoubles implements Readable{

    private int count;

    public AdaptedRandomDoubles(int count) {
        this.count = count;
    }

    @Override
    public int read(CharBuffer cb) throws IOException {
        if (count-- ==0){
            return -1;
        }
        String result = Double.toString(next()) + "";
        cb.append(result);
        return result.length();
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(new AdaptedRandomDoubles(7));
        while (s.hasNext()){
            System.out.println(s.next());
        }
    }

}
