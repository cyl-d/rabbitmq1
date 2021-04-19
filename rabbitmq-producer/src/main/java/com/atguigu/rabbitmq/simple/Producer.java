package com.atguigu.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author cyl
 * @create 2021/4/17 9:24
 */
public class Producer {

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
        //创建连接
        Connection connection = connectionFactory.newConnection();
        //创建频道
        Channel channel = connection.createChannel();
        // 声明（创建）队列

        /**
         * queue      参数1：队列名称
         * durable    参数2：是否定义持久化队列,当mq重启之后,还在
         * exclusive  参数3：是否独占本次连接
         *            ① 是否独占,只能有一个消费者监听这个队列
         *            ② 当connection关闭时,是否删除队列
         * autoDelete 参数4：是否在不使用的时候自动删除队列,当没有consumer时,自动删除
         * arguments  参数5：队列其它参数
         */
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        // 要发送的信息
        String message = "hello rabbit";
        // props参数 配置消息
        channel.basicPublish("", QUEUE_NAME,null, message.getBytes());
        // 关闭资源
        channel.close();
        connection.close();

    }

}
