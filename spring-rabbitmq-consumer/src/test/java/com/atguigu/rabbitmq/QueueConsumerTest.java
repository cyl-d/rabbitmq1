package com.atguigu.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author cyl
 * @create 2021/4/18 11:15
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:spring-rabbitmq.xml")
public class QueueConsumerTest {

    /**
     *
    */
    @Test
    public void testQueue() {
        while (true){}
    }

}
