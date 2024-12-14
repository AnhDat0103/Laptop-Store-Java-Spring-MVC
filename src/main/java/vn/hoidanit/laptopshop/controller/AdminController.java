package vn.hoidanit.laptopshop.controller;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.Order;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.dto.UserDTO;
import vn.hoidanit.laptopshop.service.OrderService;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.RoleService;
import vn.hoidanit.laptopshop.service.UserService;

@Controller
public class AdminController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final OrderService orderService;
    private final ProductService productService;

    public AdminController(UserService userService, PasswordEncoder passwordEncoder,
            RoleService roleService, OrderService orderService, ProductService productService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.orderService = orderService;
        this.productService = productService;
    }

    @GetMapping("/admin")
    public String showAdminPage(Model model) {
        model.addAttribute("numberUser", userService.getCount());
        model.addAttribute("numberProduct", productService.getCount());
        model.addAttribute("numberOrder", orderService.getCount());
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
    public String getLoginPage(Model model) {
        model.addAttribute("user", model);
        return "client/auth/login";
    }

    @GetMapping("/access-denied")
    public String getDeniedPage() {
        return "client/auth/accessDenied";
    }

    @GetMapping("/admin/order")
    public String showOrderPage(Model model) {
        List<Order> orders = this.orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "admin/order/show";
    }

    @PostMapping("/admin/order/delete/{id}")
    public String deleteOrder(Model model, @PathVariable long id) {
        this.orderService.handleDeleteOrder(id);
        return "redirect:/admin/order";
    }

}
