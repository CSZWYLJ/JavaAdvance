package com.cs;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class RabbitmqApplicationTests {

    @Test
    void contextLoads() throws ParseException {
        System.out.println(5/2);
        System.out.println(new Date());
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println("--->"+format);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-01-07 09:34:11"));
    }

}
