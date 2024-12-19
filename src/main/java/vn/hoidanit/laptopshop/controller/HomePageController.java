package vn.hoidanit.laptopshop.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.dto.ProductFilterRequestDTO;
import vn.hoidanit.laptopshop.service.ProductService;

@Controller
public class HomePageController {

    private final ProductService productService;

    public HomePageController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String showHomePage(Model model, @RequestParam("page") Optional<String> page) {
        Page<Product> list = this.productService.handleShowAllProduct(page.orElse(""), 4);
        model.addAttribute("products", list.getContent());
        model.addAttribute("totalPages", list.getTotalPages());
        model.addAttribute("currentPage", list.getNumber());
        return "client/homepage";
    }

    @GetMapping("/client/product/{id}")
    public String showDetailProductClient(Model model, @PathVariable long id) {
        Product product = this.productService.handleFindById(id);
        model.addAttribute("product", product);
        return "client/product/detail";
    }

    @GetMapping("/client/page/product")
    public String getProductPage(Model model, ProductFilterRequestDTO productFilterRequestDTO,
            HttpServletRequest request) {

        Page<Product> products = this.productService.handleShowAllProduct(productFilterRequestDTO);
        String qs = request.getQueryString();
        if (qs != null && !qs.isBlank()) {
            // remove page
            qs = qs.replace("page=0", "");
        }
        model.addAttribute("products", products.getContent());
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("currentPage", products.getNumber());
        model.addAttribute("queryString", qs);
        return "client/productpage";
    }

    // public static Double[] parsePriceRange(String priceString) {
    // try {
    // if (priceString != null && priceString.startsWith("tu-")) {
    // String[] parts = priceString.replace("tu-", "").split("-");
    // double minPrice = Double.parseDouble(parts[0]) * 1_000_000; // Convert to
    // millions
    // double maxPrice = Double.parseDouble(parts[1]) * 1_000_000;
    // return new Double[] { minPrice, maxPrice };
    // }
    // } catch (Exception e) {
    // throw new IllegalArgumentException("Invalid price range format. Expected
    // format: tu-10-15-trieu");
    // }
    // return null;
    // }

}
