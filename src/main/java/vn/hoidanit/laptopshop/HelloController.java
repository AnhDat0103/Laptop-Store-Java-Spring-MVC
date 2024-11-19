package vn.hoidanit.laptopshop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    public String index() {
        return "Hello Spring Application" + ". I 'am Dat. I am fullstack developer and PM, CEO.";
    }

    @GetMapping("/user")
    public String userPage() {
        return "only user can to access this page.";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "only admin can to access this page.";
    }

}
