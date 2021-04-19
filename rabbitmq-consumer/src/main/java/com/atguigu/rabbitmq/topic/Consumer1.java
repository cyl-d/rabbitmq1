package com.atguigu.rabbitmq.topic;

import com.atguigu.util.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @Author cyl
 * @create 2021/4/17 20:32
 */
public class Consumer1 {
    public static final String QUEUE1_NAME = "topic_1";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(QUEUE1_NAME + "  " + new String(body, StandardCharsets.UTF_8));
            }
        };
        channel.basicConsume(QUEUE1_NAME, true, defaultConsumer);
    }
}