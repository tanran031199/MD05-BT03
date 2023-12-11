package com.example.controller;

import com.example.entity.Category;
import com.example.entity.Product;
import com.example.service.CategoryService;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public String home() {
        return "home";
    }

    @GetMapping("category")
    public String category(ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryService.findAll());
        return "category";
    }

    @GetMapping("/add-cate")
    public String addCate(@RequestParam(name = "id", required = false) Integer cateId, ModelMap modelMap) {
        if (cateId == null) {
            modelMap.addAttribute("category", new Category());
        } else {
            modelMap.addAttribute("category", categoryService.findById(cateId));
        }

        modelMap.addAttribute("parentList", categoryService.getParentList());
        return "add-or-update-category";
    }

    @PostMapping("/save-cate")
    public String saveCate(@ModelAttribute("category") Category category, RedirectAttributes redirectAttributes) {
        if (category.getParent().getId() == 0) {
            category.setParent(null);
        }

        categoryService.save(category);
        return "redirect:/category";
    }

    @GetMapping("/delete-cate/{id}")
    public String deleteCate(@PathVariable("id") Integer cateId) {
        categoryService.delete(cateId);
        return "redirect:/category";
    }

    @GetMapping("product")
    public String product(ModelMap modelMap) {
        modelMap.addAttribute("products", productService.findAll());
        return "product";
    }

    @GetMapping("/add-product")
    public String addProduct(@RequestParam(name = "id", required = false) Integer productId, ModelMap modelMap) {
        if (productId == null) {
            modelMap.addAttribute("product", new Product());
        } else {
            modelMap.addAttribute("product", productService.findById(productId));
        }

        modelMap.addAttribute("categories", categoryService.findAll());
        return "add-or-update-product";
    }

    @PostMapping("/save-product")
    public String saveProduct(@ModelAttribute("product") Product product) {
        if (product.getCategory().getId() == 0) {
            product.setCategory(null);
        }

        productService.save(product);
        return "redirect:/product";
    }

    @GetMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable("id") Integer productId) {
        productService.delete(productId);
        return "redirect:/product";
    }
}
