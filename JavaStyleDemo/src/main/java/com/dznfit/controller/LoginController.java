package com.dznfit.controller;

import com.dznfit.entity.User;
import com.dznfit.service.UserService;
import com.dznfit.entity.User;
import com.dznfit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(User user, HttpSession session) {
        User users = userService.login(user);
        if (users != null) {
            session.setAttribute("admin", users);
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
}
