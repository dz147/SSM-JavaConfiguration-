package com.dznfit.controller;

import com.dznfit.entity.User;
import com.dznfit.exception.CustomException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

@Aspect
@Component
public class PowerAspect {
    private static Logger logger = Logger.getLogger(String.valueOf(PowerAspect.class));

    @Autowired
    HttpSession httpSession;

    @Pointcut("execution(* com.dznfit.controller.LoginController.userAdd(..))")
    public void emp() {
    }

    @Around("emp()")
    public void powerFilter(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("站住，Please I need to check you");
        User u = (User) httpSession.getAttribute("admin");
        if (u != null) {
            if (!u.getName().equals("dz")) {
                logger.info("不得不得，您没有此权限");
                throw new CustomException("您没有此功能权限");
            } else {
                logger.info("此用户为管理员");
                joinPoint.proceed();
            }
        } else
            throw new CustomException("没有此用户！");
    }
}
