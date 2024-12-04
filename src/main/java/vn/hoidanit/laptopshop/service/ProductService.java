package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }

    public List<Product> handleShowAllProduct() {
        List<Product> products = this.productRepository.findAll();
        if (products.isEmpty()) {
            return null;
        }
        return products;
    }

    public Product handleSaveProduct(Product newProduct) {
        Product product = this.productRepository.save(newProduct);
        System.out.println(product);
        return product;
    }

}
