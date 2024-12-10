package vn.hoidanit.laptopshop.service;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.CartDetailRepository;
import vn.hoidanit.laptopshop.repository.CartRepository;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final ProductService productService;

    public CartService(CartRepository cartRepository, CartDetailRepository cartDetailRepository,
            ProductService productService) {
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.productService = productService;
    }

    public void handleSaveToCart(HttpSession session, User currentUser, long productId) {
        Cart oldCart = currentUser.getCart();
        CartDetail newCartDetail = new CartDetail();
        Product product = this.productService.handleFindById(productId);
        long sum = 0;
        if (oldCart == null) {
            oldCart = new Cart();
            oldCart.setUser(currentUser);
            oldCart.setSum(1);
            if (product != null) {
                newCartDetail.setCart(oldCart);
                newCartDetail.setProduct(product);
                newCartDetail.setQuantity(1);
                newCartDetail.setPrice(product.getPrice());
            }
            this.cartRepository.save(oldCart);
            this.cartDetailRepository.save(newCartDetail);
            session.setAttribute("sum", sum + 1);
        } else {
            CartDetail cartDetailOptional = this.cartDetailRepository.findByCartAndProduct(oldCart, product)
                    .orElse(null);
            if (cartDetailOptional != null) {
                cartDetailOptional.setQuantity(cartDetailOptional.getQuantity() + 1);
            } else {
                cartDetailOptional = new CartDetail();
                cartDetailOptional.setCart(oldCart);
                cartDetailOptional.setProduct(product);
                cartDetailOptional.setQuantity(1);
                cartDetailOptional.setPrice(product.getPrice());
                oldCart.setSum(oldCart.getSum() + 1);
                sum = (long) session.getAttribute("sum");
                session.setAttribute("sum", sum + 1);
            }
            this.cartRepository.save(oldCart);
            this.cartDetailRepository.save(cartDetailOptional);
        }

    }

}
