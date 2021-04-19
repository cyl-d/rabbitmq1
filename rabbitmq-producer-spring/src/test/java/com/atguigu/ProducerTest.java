package com.atguigu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author cyl
 * @create 2021/4/18 12:09
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:spring-rabbitmq-producer.xml")
public class ProducerTest {

    @Autowired
    RabbitTemplate rabbitTemplate;
    /**
     *
    */
    @Test
    public void testConfirm() {
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if(ack) {
                System.out.println("接受成功消息" + cause);
            } else {
                System.out.println("接受失败消息" + cause);
                //做一些处理,让消息再次发送。
            }
        });
        rabbitTemplate.convertAndSend("test_exchange_confirm", "confirm", "message confirm....");

    }

    /**
     *
    */
    @Test
    public void testReturn() {
        rabbitTemplate.setMandatory(true);

        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey)->{
            System.out.println("return 执行了....");
            System.out.println(message);

            System.out.println(replyCode);
            System.out.println(replyText);
            System.out.println(exchange);
            System.out.println(routingKey);

            //处理

        });

        rabbitTemplate.convertAndSend("test_exchange_confirm", "confirm", "message confirm....");

    }

    /**
     *
    */
    @Test
    public void testSend() {
        for (int i = 1; i < 11; i++) {
            rabbitTemplate.convertAndSend("test_exchange_confirm","confirm","message confirm");
        }
    }

    /**
     *
    */
    @Test
    public void testTTL() {
        for (int i = 1; i < 11; i++) {
            rabbitTemplate.convertAndSend("test_exchange_ttl","ttl.hehe","message ttl", (message) -> {
                message.getMessageProperties().setExpiration("5000");
                return message;
            });
        }
    }

    @Test
    public void testDlx(){
        //1. 测试过期时间，死信消息
        rabbitTemplate.convertAndSend("test_exchange_dlx","test.dlx.haha","我是一条消息,我会死吗？");
    }

}
