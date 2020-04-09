package com.dong.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
//@EnableScheduling
public class TimeTask {

    private static Logger logger = LoggerFactory.getLogger(TimeTask.class);

    @Scheduled(cron = "0/1 * * * * ?")
    public void test() {
        System.err.println("*********   定时任务执行   **************");
        CopyOnWriteArraySet<MyWebSocket> webSocketSet = MyWebSocket.getWebSocketSet();
        webSocketSet.forEach(webSocket -> {
            try {
                webSocket.sendMessage("  定时发送  " + new Date().toString());
                logger.info("  定时发送  " + new Date().toString());
            } catch (IOException e) {
                e.printStackTrace();

            }

        });
        System.err.println("/n 定时任务完成.......");
        logger.error("/n 定时任务完成.......");
    }
}
