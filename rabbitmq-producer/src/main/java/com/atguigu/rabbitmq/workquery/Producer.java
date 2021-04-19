package com.atguigu.rabbitmq.workquery;

import com.atguigu.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author cyl
 * @create 2021/4/17 11:30
 */
public class Producer {
    public static final String QUEUE_NAME = "workquery";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        for (int i = 0; i < 10; i++) {
            String message = i + ": work query";
            // props 配置消息
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        }
        channel.close();
        connection.close();

    }

}
