package com.gupaoedu.demo.annotaions.injections.autowired;

import com.gupaoedu.project.entity.Bird;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

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

}
