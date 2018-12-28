package com.dznfit.service;

import com.dznfit.controller.LoginController;
import javaConfiguration.RootConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class NewsServiceImplTest {
    @Autowired
    NewsServiceImpl newsService;


    @Test
    public void getNewsById() {
        newsService.getNewsById(2);
    }
}