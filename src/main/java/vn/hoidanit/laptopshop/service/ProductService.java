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

    public Product handleFindById(long id) {
        Product product = this.productRepository.findById(id);
        System.out.println(product);
        return product;
    }

    public void handleRemoveProduct(long id) {
        this.productRepository.deleteById(id);
    }

    public void handleUpdateProduct(Product updatedProduct) {
        Product currentProduct = this.productRepository.findById(updatedProduct.getId());
        currentProduct.setName(updatedProduct.getName());
        currentProduct.setPrice(updatedProduct.getPrice());
        currentProduct.setDetailDesc(updatedProduct.getDetailDesc());
        currentProduct.setFactory(updatedProduct.getFactory());
        currentProduct.setQuantity(updatedProduct.getQuantity());
        currentProduct.setImage(updatedProduct.getImage());
        this.productRepository.save(currentProduct);
    }

}
