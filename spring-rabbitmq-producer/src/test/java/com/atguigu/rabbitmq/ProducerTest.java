package com.atguigu.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author cyl
 * @create 2021/4/18 11:02
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:spring-rabbitmq.xml")
public class ProducerTest {

    @Autowired
    RabbitTemplate rabbitTemplate;
    /**
     *
    */
    @Test
    public void testProducer() {
        rabbitTemplate.convertAndSend("spring_queue", "queueTest");
    }

    /**
     *
    */
    @Test
    public void testFanout() {
        rabbitTemplate.convertAndSend("spring_fanout_exchange", "", "通过广播发布");
    }

    /**
     *
    */
    @Test
    public void testTopic() {
        rabbitTemplate.convertAndSend("spring_topic_exchange", "ss.haohao", "匹配两个");
        rabbitTemplate.convertAndSend("spring_topic_exchange", "ss.haohao.ss", "匹配一个");
        rabbitTemplate.convertAndSend("spring_topic_exchange", "s.haohao", "匹配一个");
    }

}
