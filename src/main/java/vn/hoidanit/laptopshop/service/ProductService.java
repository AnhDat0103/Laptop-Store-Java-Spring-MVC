package vn.hoidanit.laptopshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.Product_;
import vn.hoidanit.laptopshop.domain.dto.ProductFilterRequestDTO;
import vn.hoidanit.laptopshop.repository.ProductRepository;
import vn.hoidanit.laptopshop.service.specifications.SpecificationProductService;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }

    public Page<Product> handleShowAllProduct(ProductFilterRequestDTO productFilterRequestDTO) {
        Page<Product> products = null;
        String pageRequest = productFilterRequestDTO.getPage() != null ? productFilterRequestDTO.getPage().get()
                : "";
        Sort sort = Sort.unsorted();
        String sortRequest = "";
        if (productFilterRequestDTO.getSort() != null && productFilterRequestDTO.getSort().isPresent()) {
            sortRequest = productFilterRequestDTO.getSort().get();
            if (sortRequest.equals("gia-tang-dan")) {
                sort = Sort.by(Product_.PRICE).ascending();
            }
            if (sortRequest.equals("gia-giam-dan")) {
                sort = Sort.by(Product_.PRICE).descending();
            }
        }
        Pageable pageable = PageRequest.of(getPage(pageRequest), 10, sort);
        if (productFilterRequestDTO.getFactory() == null && productFilterRequestDTO.getPrice() == null
                && productFilterRequestDTO.getTarget() == null
                && productFilterRequestDTO.getPage() == null) {
            products = this.productRepository.findAll(pageable);
        } else {
            products = this.productRepository
                    .findAll(SpecificationProductService.getSpecForProductPage(productFilterRequestDTO), pageable);
        }

        return products;
    }

    public int getPage(String pageRequest) {
        int page = 0;
        try {
            if (pageRequest != null && !pageRequest.equals("")) {
                page = Integer.parseInt(pageRequest);
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        return page;
    }

    public Page<Product> handleShowAllProduct(String pageRequest, int pageSize) {
        int page = 0;
        try {
            if (pageRequest == null) {
                page = 0;
            } else {
                page = Integer.parseInt(pageRequest);
            }
        } catch (NumberFormatException e) {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Product> products = this.productRepository.findAll(pageable);
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

    public long getCount() {
        return this.productRepository.count();
    }

}
