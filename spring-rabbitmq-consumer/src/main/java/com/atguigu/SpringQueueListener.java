package com.atguigu;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import java.nio.charset.StandardCharsets;

/**
 * @Author cyl
 * @create 2021/4/18 11:13
 */
public class SpringQueueListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            String msg = new String(message.getBody(), StandardCharsets.UTF_8);

            System.out.printf("接收路由名称为：%s,路由键为：%s,队列名为：%s的消息：%s \n",
                    message.getMessageProperties().getReceivedExchange(),
                    message.getMessageProperties().getReceivedRoutingKey(),
                    message.getMessageProperties().getConsumerQueue(),
                    msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
