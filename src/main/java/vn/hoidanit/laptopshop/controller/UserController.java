package vn.hoidanit.laptopshop.controller;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UserService;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String showHomePage(Model model) {
        List<User> list = this.userService.findAllByEmail("a2@gmail.com");
        System.out.println(list);
        return "hello";
    }

    @RequestMapping("/admin/user")
    public String showCreateUserPage(Model model) {
        model.addAttribute("user", new User());
        return "admin/user/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String submit(@ModelAttribute("user") User user, Model model) {
        this.userService.handleSaveUser(user);
        return "hello";
    }

}
