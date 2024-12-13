package vn.hoidanit.laptopshop.service;

import java.util.List;
import java.util.Optional;

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
        Product product = validateAndGetProduct(productId);
        Cart cart = getOrCreateCart(currentUser);
        updateCartDetails(cart, product, session);

    }

    private Product validateAndGetProduct(long productId) {
        Product product = this.productService.handleFindById(productId);
        if (product == null) {
            throw new IllegalArgumentException("Product not found with ID: " + productId);
        }
        return product;
    }

    private Cart getOrCreateCart(User currentUser) {
        Cart cart = currentUser.getCart();
        if (cart == null) {
            cart = new Cart();
            cart.setUser(currentUser);
            cart.setSum(0);
            cartRepository.save(cart);
        }
        return cart;
    }

    private void updateCartDetails(Cart cart, Product product, HttpSession session) {
        CartDetail cartDetail = cartDetailRepository.findByCartAndProduct(cart, product).orElse(null);

        if (cartDetail == null) {
            cartDetail = createNewCartDetail(cart, product);
            cart.setSum(cart.getSum() + 1);
            updateSessionCartSum(session, 1);
        } else {
            cartDetail.setQuantity(cartDetail.getQuantity() + 1);
        }

        cartRepository.save(cart);
        cartDetailRepository.save(cartDetail);

    }

    private CartDetail createNewCartDetail(Cart cart, Product product) {
        CartDetail cartDetail = new CartDetail();
        cartDetail.setCart(cart);
        cartDetail.setProduct(product);
        cartDetail.setQuantity(1);
        cartDetail.setPrice(product.getPrice());
        return cartDetail;
    }

    private void updateSessionCartSum(HttpSession session, int increment) {
        long currentSum = session.getAttribute("sum") != null ? ((Number) session.getAttribute("sum")).longValue() : 0;
        session.setAttribute("sum", currentSum + increment);
    }

    public List<CartDetail> findAllCartDetails(Cart cart, HttpSession session) {
        List<CartDetail> cartDetails = this.cartDetailRepository.findAllByCart(cart);
        double totalPice = 0;
        for (CartDetail cartDetail : cartDetails) {
            totalPice += cartDetail.getPrice() * cartDetail.getQuantity();
        }
        session.setAttribute("totalPrice", totalPice);
        return cartDetails;

    }

    public Cart handleFindCartById(long id) {
        return this.cartRepository.findById(id);
    }

    public Cart handleFindCartByUser(User user) {
        return this.cartRepository.findByUser(user);
    }

    public void handleRemoveFromCart(HttpSession session, long cartDetailId) {
        Optional<CartDetail> cartDetail = this.cartDetailRepository.findById(cartDetailId);
        if (cartDetail != null) {
            Cart cart = cartDetail.get().getCart();
            this.cartDetailRepository.deleteById(cartDetailId);
            if (cart.getCartDetails().size() == 0) {
                this.cartRepository.delete(cart);
                session.setAttribute("sum", (long) 0);
            } else {
                cart.setSum((long) cart.getCartDetails().size());
                session.setAttribute("sum", (long) cart.getCartDetails().size());
            }
        }
    }

    public void handleUpdateCartBeforeCheckout(List<CartDetail> cartDetails) {
        for (CartDetail cd : cartDetails) {
            Optional<CartDetail> currentCartDetail = this.cartDetailRepository.findById(cd.getId());
            if (currentCartDetail.isPresent()) {
                currentCartDetail.get().setQuantity(cd.getQuantity());
                this.cartDetailRepository.save(currentCartDetail.get());
            }

        }
    }

    public void handleRemoveCart(Cart cart) {
        if (cart != null) {
            this.cartRepository.delete(cart);
        }
    }

}
