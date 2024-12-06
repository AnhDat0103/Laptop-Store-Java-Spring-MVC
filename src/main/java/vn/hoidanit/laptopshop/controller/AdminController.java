package vn.hoidanit.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String showAdminPage() {
        return "admin/dashboard/show";
    }

    @GetMapping("/admin/register")
    public String getRegisterPage() {
        return "admin/auth/register";
    }

    @GetMapping("/admin/login")
    public String getLoginPage() {
        return "admin/auth/login";
    }

}
