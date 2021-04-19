package com.atguigu.rabbitmq.routing;

import com.atguigu.util.ConnectionUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author cyl
 * @create 2021/4/17 13:40
 */
public class Producer {

    public static final String QUEUE1_NAME = "routing_1";
    public static final String QUEUE2_NAME = "routing_2";
    public static final String EXCHANGE_NAME = "test_routing";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT, true, false, false, null);
        channel.queueDeclare(QUEUE1_NAME, true, false, false, null);
        channel.queueDeclare(QUEUE2_NAME, true, false, false, null);
        String fanoutMessage = "日志信息：张三调用了findAll方法...日志级别：error...";
        channel.queueBind(QUEUE1_NAME, EXCHANGE_NAME, "info");
        channel.queueBind(QUEUE2_NAME, EXCHANGE_NAME, "info");
        channel.queueBind(QUEUE2_NAME, EXCHANGE_NAME, "error");
        channel.queueBind(QUEUE2_NAME, EXCHANGE_NAME, "warning");
        channel.basicPublish(EXCHANGE_NAME, "error", null, fanoutMessage.getBytes());
        channel.close();
        connection.close();
    }

}
