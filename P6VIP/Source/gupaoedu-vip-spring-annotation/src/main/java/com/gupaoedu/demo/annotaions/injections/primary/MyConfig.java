package com.gupaoedu.demo.annotaions.injections.primary;

import com.gupaoedu.project.dao.MyDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Created by Tom.
 */
@Configuration
@ComponentScan({
        "com.gupaoedu.project.controller",
        "com.gupaoedu.project.service",
        "com.gupaoedu.project.dao"
            })
public class MyConfig {


    @Primary
    @Bean("myDao")
    public MyDao dao(){
        MyDao dao = new MyDao();
        dao.setFlag("9");
        return dao;
    }


    @Bean("myDao")
    public MyDao myDao(){
        MyDao dao = new MyDao();
        dao.setFlag("3");
        return dao;
    }
}
