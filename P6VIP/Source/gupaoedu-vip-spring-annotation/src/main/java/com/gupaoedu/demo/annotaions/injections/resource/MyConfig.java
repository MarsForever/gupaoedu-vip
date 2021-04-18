package com.gupaoedu.demo.annotaions.injections.resource;

import com.gupaoedu.project.dao.MyDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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

    @Bean("dao")
    public MyDao dao(){
        MyDao dao = new MyDao();
        dao.setFlag("4");
        return dao;
    }
}
