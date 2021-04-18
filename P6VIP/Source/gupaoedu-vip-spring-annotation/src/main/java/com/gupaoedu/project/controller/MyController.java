package com.gupaoedu.project.controller;

import com.gupaoedu.project.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by Tom.
 */
@Controller
public class MyController {
    @Autowired private MyService service;
}
