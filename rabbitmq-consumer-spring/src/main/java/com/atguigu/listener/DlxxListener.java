package com.atguigu.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * @Author cyl
 * @create 2021/4/18 20:11
 */
@Component
public class DlxxListener implements ChannelAwareMessageListener {
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try{
            System.out.println(new String(message.getBody()));
            System.out.println("死信队列...");
            channel.basicAck(deliveryTag,true);
        }catch (Exception e) {
            e.printStackTrace();
            channel.basicNack(deliveryTag, true, true);
        }
    }
}
