package vn.hoidanit.laptopshop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UploadFileService;

@Controller
public class ProductController {

    private final ProductService productService;
    private final UploadFileService uploadFileService;

    public ProductController(ProductService productService, UploadFileService uploadFileService) {
        this.productService = productService;
        this.uploadFileService = uploadFileService;
    }

    @GetMapping("/admin/product")
    public String getProductPage(Model model, @RequestParam("page") Optional<String> page,
            @RequestParam("name") Optional<String> nameProduct) {
        Page<Product> products = this.productService.handleShowAllProduct(page.orElse(""), 3);
        model.addAttribute("products", products.getContent());
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("currentPage", products.getNumber());

        return "admin/product/show";
    }

    @GetMapping("/admin/product/create")
    public String showCreateProductPage(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }

    @PostMapping("/admin/product/create")
    public String createProduct(@ModelAttribute("newProduct") @Valid Product product,
            BindingResult bindingResult,
            @RequestParam("datntFile") MultipartFile imageProduct) {
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(">>>" + error.getField() + ": " + error.getDefaultMessage());
        }
        if (bindingResult.hasErrors()) {
            return "admin/product/create";
        } else {
            product.setImage(this.uploadFileService.handleUploadFile(imageProduct, "product"));
            this.productService.handleSaveProduct(product);
            return "redirect:/admin/product";
        }
    }

    @GetMapping("/admin/product/show/{id}")
    public String showDetailProduct(Model model, @PathVariable long id) {
        Product product = this.productService.handleFindById(id);
        model.addAttribute("product", product);
        return "admin/product/product-detail";
    }

    @GetMapping("/admin/product/update/{id}")
    public String showUpdateProductPage(Model model, @PathVariable long id) {
        Product product = this.productService.handleFindById(id);
        model.addAttribute("productUpdate", product);
        return "admin/product/update";
    }

    @PostMapping("/admin/product/update")
    public String updateProduct(@ModelAttribute("productUpdate") @Valid Product product,
            BindingResult bindingResult, @RequestParam("datntFile") MultipartFile multipartFile) {
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(">>>" + error.getField() + ": " + error.getDefaultMessage());
        }
        if (bindingResult.hasErrors()) {
            return "/admin/product/update/{id}";
        } else {
            this.uploadFileService.handedRemoveFile(product.getImage(), "product");
            product.setImage(this.uploadFileService.handleUploadFile(multipartFile, "product"));
            this.productService.handleUpdateProduct(product);
            return "redirect:/admin/product";
        }

    }

    @GetMapping("/admin/product/delete/{id}")
    public String getRemoveProductPage(Model model, @PathVariable long id) {
        Product product = this.productService.handleFindById(id);
        model.addAttribute("deleteProduct", product);
        return "admin/product/delete";
    }

    @PostMapping("/admin/product/delete")
    public String removeProduct(@ModelAttribute("deleteProduct") Product product) {
        if (product != null) {
            this.productService.handleRemoveProduct(product.getId());
        }
        return "redirect:/admin/product";

    }

}
