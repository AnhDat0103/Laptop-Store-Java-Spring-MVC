package vn.hoidanit.laptopshop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.service.ProductService;

@Controller
public class HomePageController {

    private final ProductService productService;

    public HomePageController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
        List<Product> list = this.productService.handleShowAllProduct();
        model.addAttribute("products", list);
        return "client/homepage";
    }

    @GetMapping("/client/product/{id}")
    public String showDetailProductClient(Model model, @PathVariable long id) {
        Product product = this.productService.handleFindById(id);
        model.addAttribute("product", product);
        return "client/product/detail";
    }

}
