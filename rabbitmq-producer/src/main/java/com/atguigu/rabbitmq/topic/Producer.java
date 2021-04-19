package com.atguigu.rabbitmq.topic;

import com.atguigu.util.ConnectionUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author cyl
 * @create 2021/4/17 20:32
 */
public class Producer {

    public static final String QUEUE1_NAME = "topic_1";
    public static final String QUEUE2_NAME = "topic_2";
    public static final String EXCHANGE_NAME = "test_topic";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC, true, false, false,null);
        channel.queueDeclare(QUEUE1_NAME, true, false, false, null);
        channel.queueDeclare(QUEUE2_NAME, true, false, false, null);
        channel.queueBind(QUEUE1_NAME,EXCHANGE_NAME,"#.error");
        channel.queueBind(QUEUE1_NAME,EXCHANGE_NAME,"order.*");
        channel.queueBind(QUEUE2_NAME,EXCHANGE_NAME,"*.*");
        String body = "日志信息：张三调用了findAll方法...日志级别：info...";
        //发送消息goods.info,goods.error
        channel.basicPublish(EXCHANGE_NAME,"asdhasd.order.info.error",null,body.getBytes());
        channel.close();
        connection.close();

    }

}
