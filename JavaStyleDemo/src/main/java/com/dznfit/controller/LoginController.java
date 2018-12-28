package com.dznfit.controller;

import com.dznfit.entity.News;
import com.dznfit.entity.User;
import com.dznfit.service.NewsServiceImpl;
import com.dznfit.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@ControllerAdvice
@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private NewsServiceImpl newsService;

    @PostMapping("/login")
    public String login(User user, HttpSession session) {
        User users = userService.login(user);
        if (users != null) {
            session.setAttribute("admin", users);
            System.out.println(users);
            return "home";
        } else {
            return "redirect:/loginPage";
        }
    }


    @GetMapping(value = "/home")
    public String loginSucceed() {
        return "home";
    }

    @GetMapping(value = "/loginPage")
    public String UserLogin() {
        return "login";
    }

    @GetMapping(value = "/main")
    public String loadingHome() {
        return "home";
    }


    @GetMapping(value = "/add")
    public String userAdd() {
        return "home";
    }

    @GetMapping(value = "/redis/{id}")
    //@GetCache(name="news",value="id")
    public @ResponseBody News redisTest(@PathVariable("id")int id) {
        return newsService.getNewsById(id);
    }
}
