package vn.hoidanit.laptopshop.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.dto.UserDTO;
import vn.hoidanit.laptopshop.service.RoleService;
import vn.hoidanit.laptopshop.service.UserService;

@Controller
public class AdminController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public AdminController(UserService userService, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String showAdminPage() {
        return "admin/dashboard/show";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("user", new UserDTO());
        return "client/auth/register";
    }

    @PostMapping("/register")
    public String toRegistration(@ModelAttribute("user") @Valid UserDTO userDTO,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "client/auth/register";
        } else {
            User newUser = this.userService.handleConvertToUser(userDTO);
            newUser.setPassword(this.passwordEncoder.encode(newUser.getPassword()));
            newUser.setRole(this.roleService.handleGetRoleByName("USER"));
            this.userService.handleSaveUser(newUser);
            return "redirect:/login";
        }
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "client/auth/login";
    }

}
