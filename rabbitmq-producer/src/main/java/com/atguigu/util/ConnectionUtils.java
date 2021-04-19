package com.atguigu.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author cyl
 * @create 2021/4/17 11:28
 */
public class ConnectionUtils {

    public static Connection getConnection() throws IOException, TimeoutException {
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

        return connectionFactory.newConnection();
    }

}
