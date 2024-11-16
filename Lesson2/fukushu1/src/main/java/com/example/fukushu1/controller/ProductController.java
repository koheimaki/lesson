package com.example.fukushu1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.fukushu1.model.Product;
import com.example.fukushu1.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/show")
    public String showList(Model model) {
        List<Product> products = productService.findAllProducts();
        model.addAttribute("products", products);
        return "/products/show";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("product", new Product());
        return "/products/add";
    }

    @PostMapping("/add")
    public String add(Product product) {
        productService.saveProduct(product);
        return "redirect:/products/show";
    }

    @GetMapping("/edit/{productId}")
    public String editForm(@PathVariable("productId") Long productId, Model model) {
        Product product = productService.findProductById(productId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid product Id" + productId));
            model.addAttribute("product", product);
        return "products/edit";
    }

    @PostMapping("/edit")
    public String edit(Product product) {
        productService.updateProduct(product);
        return "redirect:/products/show";
    }

    @GetMapping("/delete/{productId}")
    public String deleteById(@PathVariable("productId") Long bookId) {
        productService.deleteProductById(bookId);
        return "redirect:/products/show";
    }

    @GetMapping("/search")
    public String search(@RequestParam(name = "code", required = false) String productCode,
                        Model model) {
            List<Product> products = productService.findByCode(productCode);
            model.addAttribute("products", products);
        return "/products/search";
    }
}
