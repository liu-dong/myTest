package com.dong.javaBase.map;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author liudong 2022/3/12
 */
public class MapFunction {

    Map<String, Function<Integer,String>> map = new HashMap<>();

    public MapFunction(){
        map.put("fun1",i -> fun1(i));
        map.put("fun2",i -> fun2(i));
        map.put("fun3",i -> fun3(i));
    }

    String fun1(Integer str){
        return null;
    }String fun2(Integer str){
        return null;
    }String fun3(Integer str){
        return null;
    }
}
