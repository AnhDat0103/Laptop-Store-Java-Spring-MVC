package vn.hoidanit.laptopshop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.dto.OrderDTO;
import vn.hoidanit.laptopshop.service.CartService;
import vn.hoidanit.laptopshop.service.OrderService;
import vn.hoidanit.laptopshop.service.UserService;

@Controller
public class CartController {

    private final UserService userService;
    private final CartService cartService;
    private final OrderService orderService;

    public CartController(UserService userService, CartService cartService, OrderService orderService) {
        this.userService = userService;
        this.cartService = cartService;
        this.orderService = orderService;
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
        model.addAttribute("cart", cart);

        return "client/cart/detail";
    }

    @PostMapping("/delete-cart-product/{cartDetailId}")
    public String removeProductFromCart(@PathVariable long cartDetailId, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        this.cartService.handleRemoveFromCart(session, cartDetailId);
        return "redirect:/cart-details";
    }

    @PostMapping("/confirm-checkout")
    public String getCheckOutPage(@ModelAttribute("cart") Cart cart) {
        List<CartDetail> cartDetails = cart == null ? new ArrayList<CartDetail>() : cart.getCartDetails();
        this.cartService.handleUpdateCartBeforeCheckout(cartDetails);
        return "redirect:/check-out";
    }

    @GetMapping("/check-out")
    public String getCheckoutPage(Model model, Authentication authentication) {
        User currentUser = this.userService.findByEmail(authentication.getName());
        Cart cart = this.cartService.handleFindCartByUser(currentUser);
        List<CartDetail> cartDetails = cart == null ? new ArrayList<>() : cart.getCartDetails();

        double totalPrice = 0;
        for (CartDetail cd : cartDetails) {
            totalPrice += cd.getPrice() * cd.getQuantity();
        }
        model.addAttribute("cartDetails", cartDetails);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("order", new OrderDTO());
        return "client/cart/check-out";
    }

    @PostMapping("/place-order")
    public String placeOrder(Model model, @ModelAttribute("order") @Valid OrderDTO orderDTO,
            BindingResult bindingResult,
            Authentication authentication, HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);
        User currentUser = this.userService.findByEmail(authentication.getName());
        Cart cart = this.cartService.handleFindCartByUser(currentUser);
        List<CartDetail> cartDetails = cart == null ? new ArrayList<>() : cart.getCartDetails();

        double totalPrice = 0;
        for (CartDetail cd : cartDetails) {
            totalPrice += cd.getPrice() * cd.getQuantity();
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("cartDetails", cartDetails);
            model.addAttribute("totalPrice", totalPrice);
            model.addAttribute("order", orderDTO);
            return "client/cart/check-out";
        } else {
            if (currentUser == null || cart == null) {
                System.out.println("Can not place order.");
            }
            this.orderService.handleSaveOrder(orderDTO, currentUser, cart, session);
            session.removeAttribute("cart");
            return "redirect:/";
        }
    }

}
