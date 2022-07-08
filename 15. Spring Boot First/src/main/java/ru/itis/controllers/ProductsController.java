package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.ProductDto;
import ru.itis.services.ProductService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/products")
public class ProductsController {
    private final ProductService productService;

    @GetMapping(value = "/add")
    public String add() {
        return "add_product";
    }

    @PostMapping(value = "/add")
    public String add(@ModelAttribute ProductDto productDto) {
        productService.add(productDto);
        return "redirect:/products/add";
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("products", productService.getAll());
        return "products";
    }

    @GetMapping(value = "/update")
    public String update() {
        return "update_product";
    }

    @PostMapping(value = "/update")
    public String update(@ModelAttribute ProductDto productDto) {
        productService.add(productDto);
        return "redirect:/products/update";
    }

    @GetMapping(value = "/delete")
    public String delete() {
        return "delete_product";
    }

    @PostMapping(value = "/delete")
    public String delete(@RequestParam("id") Long id) {
        productService.delete(id);
        return "redirect:/products/delete";
    }
}
