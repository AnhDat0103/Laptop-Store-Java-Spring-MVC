package vn.hoidanit.laptopshop.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.CartService;
import vn.hoidanit.laptopshop.service.UserService;

@Controller
public class CartController {

    private final UserService userService;
    private final CartService cartService;

    public CartController(UserService userService, CartService cartService) {
        this.userService = userService;
        this.cartService = cartService;
    }

    @GetMapping("/cart-detail")
    public String GetCartPage() {
        return "client/cart/detail";
    }

    @PostMapping("/add-to-cart/{productId}")
    public String addProductToCart(@PathVariable long productId, HttpServletRequest request,
            Authentication authentication) {
        HttpSession session = request.getSession(false);
        User currentUser = this.userService.findByEmail(authentication.getName());
        if (session != null) {
            this.cartService.handleSaveToCart(session, currentUser, productId);
        }
        return "redirect:/";
    }

    @GetMapping("/cart-details")
    public String getCartDetailPage(Model model, Authentication authentication, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String email = authentication.getName();
        User user = this.userService.findByEmail(email);
        Cart cart = this.cartService.handleFindCartByUser(user);
        List<CartDetail> cartDetails = this.cartService.findAllCartDetails(cart, session);
        model.addAttribute("cartDetails", cartDetails);
        return "client/cart/detail";
    }
}
