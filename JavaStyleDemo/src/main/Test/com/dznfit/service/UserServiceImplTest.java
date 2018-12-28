package com.dznfit.service;

import com.dznfit.controller.LoginController;
import com.dznfit.entity.News;
import com.dznfit.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javaConfiguration.RootConfig;
import javaConfiguration.WebConfig;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import java.io.*;
import java.util.ArrayList;


public class UserServiceImplTest {
    private static Logger logger = Logger.getLogger(UserServiceImplTest.class);


    @Test
    public void login() throws IOException {
        Jedis jedis = new Jedis();
        User user = new User(1, "dz", "123", 1);
        //放入容器
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(RootConfig.class);
        //得到bean
        UserServiceImpl bean = context.getBean(UserServiceImpl.class);
        /**
         * ObjectMapper是JSON操作的核心，Jackson的所有JSON操作都是在ObjectMapper中实现。
         */
        ObjectMapper mapper = new ObjectMapper();

        System.out.println("类名" + bean.login((user)).getClass().getName());
        String s = mapper.writeValueAsString(bean.login(user));


        System.out.println("序列化：" + s);

        System.out.println("反序列化:" + mapper.readValue(s, User.class));
    }

    @Test
    public void Serialization() throws IOException, ClassNotFoundException {
        Jedis jedis = new Jedis();

        //java原生序列化
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(stream);
        oos.writeObject(new News(1, "sdj", "merry christmas"));
        oos.writeObject(new News(2, "zdm", "mashangyaoofangjiale"));

        jedis.set("news-01".getBytes(), stream.toByteArray());
        oos.close();
        System.out.println("---------");

        System.out.println(jedis.get("news-01").getBytes().length);
        System.out.println(jedis.get("news-01".getBytes()).length);
        //反序列化
        //这里有一个坑在调用jedis.get("news-01").getBytes() --error
        // 这样调用的话会改变字节码取的时候就不对了
        //存jedis.set("news-01".getBytes())取所有也是jedis.get("news-01".getBytes());
        ByteArrayInputStream bri = new ByteArrayInputStream(jedis.get("news-01".getBytes()));
        ObjectInputStream outs = new ObjectInputStream(bri);
        Object o = outs.readObject();
        Object o1 = outs.readObject();
        System.out.println(o);
        System.out.println(o1);
        outs.close();
    }

}