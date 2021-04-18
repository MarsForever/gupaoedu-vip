package com.gupaoedu.demo.annotaions.injections.qualifier;

import com.gupaoedu.project.dao.MyDao;
import com.gupaoedu.project.service.MyService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Tom.
 */
public class MyTest {
    @Test
    public void test(){
        ApplicationContext app = new AnnotationConfigApplicationContext(MyConfig.class);


        MyService service = app.getBean(MyService.class);
        service.print();


    }
}
