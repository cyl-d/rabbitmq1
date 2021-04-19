package com.atguigu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author cyl
 * @create 2021/4/18 13:02
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:spring-rabbitmq-consumer.xml")
public class AckTest {

    /**
     *
    */
    @Test
    public void testAck() {
        while (true){}
    }

}
