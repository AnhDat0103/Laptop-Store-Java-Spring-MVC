package vn.hoidanit.laptopshop.controller;

import vn.hoidanit.laptopshop.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getGreeting() {
        return "hello";
    }

}
