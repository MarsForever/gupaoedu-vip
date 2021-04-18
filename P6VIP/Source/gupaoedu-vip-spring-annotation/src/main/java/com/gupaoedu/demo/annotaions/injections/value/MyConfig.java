package com.gupaoedu.demo.annotaions.injections.value;

import com.gupaoedu.project.entity.Bird;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Tom.
 */
@Configuration
public class MyConfig {

    @Bean
    public Bird bird(){
        return new Bird();
    }
}
