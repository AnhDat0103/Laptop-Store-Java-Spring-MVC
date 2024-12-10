package vn.hoidanit.laptopshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.Product;
import java.util.List;

public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {

    Optional<CartDetail> findByCartAndProduct(Cart cart, Product product);

    CartDetail save(CartDetail cartDetail);

    List<CartDetail> findAllByCart(Cart cart);
}
