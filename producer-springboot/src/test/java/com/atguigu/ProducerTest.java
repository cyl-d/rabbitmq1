package com.atguigu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author cyl
 * @create 2021/4/18 20:30
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProducerTest {

    @Autowired
    RabbitTemplate rabbitTemplate;
    /**
     *
    */
    @Test
    public void testProducer() {
        rabbitTemplate.convertAndSend("boot_topic_exchange", "boot.hh", "spring boot test");
    }

}
