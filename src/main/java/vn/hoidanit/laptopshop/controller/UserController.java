package vn.hoidanit.laptopshop.controller;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UserService;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
        return "hello";
    }

    @RequestMapping("/admin/user/create")
    public String showCreateUserPage(Model model) {
        model.addAttribute("user", new User());
        return "admin/user/create";
    }

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String submit(@ModelAttribute("user") User user) {
        this.userService.handleSaveUser(user);
        return "redirect:/admin/user";
    }

    @RequestMapping("/admin/user")
    public String getTableUserPage(Model model) {
        List<User> users = this.userService.findAll();
        model.addAttribute("users", users);
        return "admin/user/tableUsers";
    }

    @RequestMapping("/admin/user/{id}")
    public String showDetailUserPage(Model model, @PathVariable long id) {
        User user = this.userService.getUserDetail(id);
        model.addAttribute("user", user);
        return "admin/user/user-detail";
    }

    @RequestMapping("/admin/user/update/{id}")
    public String showUpdateUserPage(Model model, @PathVariable long id) {
        User currentUser = this.userService.getUserDetail(id);
        model.addAttribute("userUpdated", currentUser);
        return "admin/user/update-info-user";
    }

    @RequestMapping(value = "/admin/user/update", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("userUpdated") User afterUpdatedUser) {
        this.userService.handleUpdateUser(afterUpdatedUser);
        return "redirect:/admin/user";
    }

}
