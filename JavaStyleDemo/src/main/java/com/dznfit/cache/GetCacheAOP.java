package com.dznfit.cache;

import com.dznfit.dao.NewsMapper;
import com.dznfit.entity.News;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;

@Component
@Aspect
public class GetCacheAOP {

    @Autowired
    NewsMapper newsMapper;

    @Pointcut("@annotation(com.dznfit.cache.GetCache)")
    public void getCache() {
        System.out.println("我是一个切入点");
    }

    /**
     * 在所有标注@getCache的地方切入
     *
     * @param joinPoint
     */
    @Around("getCache()")
    public Object beforeExec(ProceedingJoinPoint joinPoint) {
        try {
            System.out.println("进来了不错");
            MethodSignature ms = (MethodSignature) joinPoint.getSignature();
            Method method = ms.getMethod();
            String ActionName = method.getAnnotation(GetCache.class).name();
            String fieldList = method.getAnnotation(GetCache.class).value();

            String str = method.getAnnotatedReturnType().getType().toString();
            System.out.println("返回类型：" + str.substring(str.lastIndexOf(".") + 1, str.length()));
            for (String field : fieldList.split(",")) {
                ActionName += "." + field;
            }

            //先获取目标方法参数
            String id = null;
            Object[] args = joinPoint.getArgs();
            if (args != null && args.length > 0) {
                id = String.valueOf(args[0]);
            }
            ActionName += "=" + id;
            String redisKey = ms + "." + ActionName;
            System.out.println("得到返回类型+所在包名类名方法名参数名以及参数值：" + redisKey);

            //读写redis
            Jedis jedis = new Jedis();
            String key = redisKey;
            ObjectMapper om = new ObjectMapper();
            //redis存在key则直接读取
            News s = null;
            if (jedis.exists(key)) {
                s = om.readValue(jedis.get(key), News.class);
                return s;
            } else {
                s = (News) joinPoint.proceed();
                jedis.set(key, om.writeValueAsString(s));
                return s;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}
