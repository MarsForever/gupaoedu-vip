package com.gupaoedu.demo.annotaions.configures.configuration;

import com.gupaoedu.project.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Tom.
 */
@Configuration
public class MyConfig {

    @Bean
    public Person person1(){
        return new Person("Tom",18);
    }
}
