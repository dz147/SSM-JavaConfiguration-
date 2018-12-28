package com.dznfit.service;

import com.dznfit.dao.NewsMapper;
import com.dznfit.entity.News;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.io.IOException;

@Service
public class NewsServiceImpl {
    @Autowired
    NewsMapper newsMapper;

    @Cacheable("news")
    public News getNewsById(int id) {
        return newsMapper.selectByPrimaryKey(id);
    }



     /*Jedis jedis = new Jedis();
        String key = "news";
        ObjectMapper om = new ObjectMapper();
        //redis存在key则直接读取
        News s = null;
        if (jedis.exists(key)) {
            try {
                s = om.readValue(jedis.get(key),News.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            s = newsMapper.selectByPrimaryKey(id);
            //存取到redis
            try {
                jedis.del(key);
                jedis.set(key, om.writeValueAsString(s));
                System.out.println(s);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }*/

}
