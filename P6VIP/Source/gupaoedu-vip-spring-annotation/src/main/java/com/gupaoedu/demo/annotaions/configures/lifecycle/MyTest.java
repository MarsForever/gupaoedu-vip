package com.gupaoedu.demo.annotaions.configures.lifecycle;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Tom.
 */
public class MyTest {
    @Test
    public void test(){
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MyConfig.class);
        System.out.println("IoC容器创建完成");
//        System.out.println(app.getBean("car"));
        System.out.println(app.getBean("train"));

        System.out.println(app.getBean("airPlane"));
        app.close();
    }
}
