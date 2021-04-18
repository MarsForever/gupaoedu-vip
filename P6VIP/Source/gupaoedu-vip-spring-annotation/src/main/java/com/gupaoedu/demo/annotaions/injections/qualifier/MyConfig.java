package com.gupaoedu.demo.annotaions.injections.qualifier;

import com.gupaoedu.project.dao.MyDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

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
        dao.setFlag("2");
        return dao;
    }
}
