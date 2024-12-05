package vn.hoidanit.laptopshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.hoidanit.laptopshop.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAll();

    Product save(Product product);

    Product findById(long id);

    void deleteById(long id);

}
