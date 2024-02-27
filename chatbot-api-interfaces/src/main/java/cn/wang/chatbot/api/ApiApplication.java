package cn.wang.chatbot.api;

import cn.wang.chatbot.api.application.job.ChatBotSchedule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.swing.*;

/**
 * 入口
 */

@SpringBootApplication
public class ApiApplication {
    public static void main(String[] args) {

        SpringApplication.run(ApiApplication.class,args);

    }
}
