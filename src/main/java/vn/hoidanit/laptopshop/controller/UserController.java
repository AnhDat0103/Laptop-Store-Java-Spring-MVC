package vn.hoidanit.laptopshop.controller;

import vn.hoidanit.laptopshop.domain.Role;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.RoleService;
import vn.hoidanit.laptopshop.service.UploadFileService;
import vn.hoidanit.laptopshop.service.UserService;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UserController {

    private final UserService userService;
    private final UploadFileService uploadFileService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, UploadFileService uploadFileService, RoleService roleService,
            PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.uploadFileService = uploadFileService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
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

    @PostMapping("/admin/user/create")
    public String submit(@ModelAttribute("user") User user, @RequestParam("datntFile") MultipartFile avatarFile) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        user.setAvatar(this.uploadFileService.handleUploadFile(avatarFile, "avatar"));
        user.setRole(this.roleService.handleGetRoleByName(user.getRole().getName()));
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

    @GetMapping("/admin/user/delete/{id}")
    public String getDeletePage(Model model, @PathVariable long id) {
        User userDelete = this.userService.getUserDetail(id);
        model.addAttribute("user", userDelete);
        return "admin/user/delete-user";
    }

    @PostMapping("/admin/user/delete")
    public String deleteUser(@ModelAttribute("user") User user) {
        if (user != null) {
            this.userService.removeUserById(user.getId());
        }
        return "redirect:/admin/user";
    }

}
