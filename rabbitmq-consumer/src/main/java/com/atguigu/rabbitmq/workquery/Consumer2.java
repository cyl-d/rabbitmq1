package com.atguigu.rabbitmq.workquery;

import com.atguigu.util.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @Author cyl
 * @create 2021/4/17 13:02
 */
public class Consumer2 {
    public static final String QUEUE_NAME = "workquery";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
//        添加设置，每次处理1个
        channel.basicQos(1);
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("consumer2 -- " + new String(body, StandardCharsets.UTF_8));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
//                    设置手动确认
                    channel.basicAck(envelope.getDeliveryTag(), true);
                }
            }
        };
//        关闭自动提交
        channel.basicConsume(QUEUE_NAME, false, defaultConsumer);
//        channel.basicConsume(QUEUE_NAME, true, defaultConsumer);
    }

}