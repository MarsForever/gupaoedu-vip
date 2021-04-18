package com.gupaoedu.demo.annotaions.configures.component;


import org.springframework.stereotype.Component;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(value = "com.gupaoedu.project",
//        includeFilters = {@Filter(type = FilterType.ANNOTATION,value = {Component.class})},
        includeFilters = {@Filter(type = FilterType.ANNOTATION,value = {Controller.class})},
        useDefaultFilters = false)
public class MyConfig {

}
