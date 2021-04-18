package com.gupaoedu.demo.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Tom.
 */
public class Test {

    public static void main(String[] args) {
        ApplicationContext app = new ClassPathXmlApplicationContext("application.xml");
        Object bean = app.getBean("person");
        System.out.println(bean);
    }

}
