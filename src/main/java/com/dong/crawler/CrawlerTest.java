package com.dong.crawler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author 3hld
 * @Date 2020/2/12 16:40
 * @Version 1.0
 */
public class CrawlerTest {

    public static void main(String[] args) {
        httpGet("http://bj.lianjia.com/");
    }


    public static String httpGet(String url)
    {
        String result = "";

        BufferedReader in = null;

        try {
            URL realUrl = new URL(url);
            System.out.println(realUrl + "\n" + url);
            URLConnection urlConnection = realUrl.openConnection();
            urlConnection.connect();
            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            System.out.println(urlConnection.getHeaderFields());

            String line;
            while((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e){
            System.out.println("发送get请求异常！" + e);
            e.printStackTrace();
        } finally {
            try{
                if(in != null){
                    in.close();
                }

            }catch (Exception e2){

                e2.printStackTrace();
            }
        }
        return result;
    }
}
