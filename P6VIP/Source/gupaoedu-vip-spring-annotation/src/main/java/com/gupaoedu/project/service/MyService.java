package com.gupaoedu.project.service;

import com.gupaoedu.project.dao.MyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Tom.
 */
@Service
public class MyService {
    @Qualifier("dao")
//    @Resource(name="dao")
    @Autowired
    private MyDao myDao;

    public void print(){
        System.out.println(myDao);
    }
}
