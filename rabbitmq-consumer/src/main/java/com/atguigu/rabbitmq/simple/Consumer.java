package com.atguigu.rabbitmq.simple;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;

/**
 * @Author cyl
 * @create 2021/4/17 11:16
 */
public class Consumer {

    private static final String QUEUE_NAME = "simple_queue";
    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();

        // 主机地址
        connectionFactory.setHost("192.168.122.100");

        // 连接端口
        connectionFactory.setPort(5672);

        //虚拟主机名称;默认为 /
        connectionFactory.setVirtualHost("/");

        //连接用户名；默认为guest
        connectionFactory.setUsername("admin");
        //连接密码；默认为guest
        connectionFactory.setPassword("123456");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
             /*
               回调方法,当收到消息后,会自动执行该方法
               1. consumerTag：标识
               2. envelope：获取一些信息,交换机,路由key...
               3. properties：配置信息
               4. body：数据
            */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("consumerTag = " + consumerTag);
                System.out.println("Exchange = " + envelope.getExchange());
                System.out.println("getRoutingKey = " + envelope.getRoutingKey());
                System.out.println("properties = " + properties);
                System.out.println("body = " + new String(body));
            }
        };

        /*
        basicConsume(String queue, boolean autoAck, Consumer callback)
        参数：
            1. queue：队列名称
            2. autoAck：是否自动确认 ,类似咱们发短信,发送成功会收到一个确认消息
            3. callback：回调对象
         */
        channel.basicConsume(QUEUE_NAME, true, defaultConsumer);

    }

}
