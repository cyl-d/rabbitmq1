package com.atguigu.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * @Author cyl
 * @create 2021/4/18 12:53
 */

@Component
public class AckListener implements ChannelAwareMessageListener {


    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try{
            System.out.println(new String(message.getBody()));
            System.out.println("处理业务逻辑");
//            int i = 1 / 0;
            channel.basicAck(deliveryTag, true);
        }catch (Exception e) {
            e.printStackTrace();
            channel.basicNack(deliveryTag, true, true);
        }
    }
}
