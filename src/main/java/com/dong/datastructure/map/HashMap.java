package com.dong.datastructure.map;

/**
 * @author LD
 */
public class HashMap {

    private final String[] array = new String[16];

    /**
     * 赋值
     * @param key
     * @param value
     */
    public void put(String key, String value) {
        int index = getHash(key);
        array[index] = value;
    }

    /**
     * 取值
     * @param key
     * @return
     */
    public String get(String key) {
        int index = getHash(key);
        return array[index];

    }

    /**
     * 计算hash值，获得数组下标
     * @param key
     * @return
     */
    private int getHash(String key){
        return 1;
    }
}
