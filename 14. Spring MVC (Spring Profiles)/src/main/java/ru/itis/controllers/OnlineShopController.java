package ru.itis.controllers;


import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.CustomerDto;
import ru.itis.dto.OrderDto;
import ru.itis.dto.ProductDto;
import ru.itis.services.OnlineShopService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OnlineShopController {
    private final OnlineShopService onlineShopService;

    @PostMapping(value = "add/customer")
    public CustomerDto addCustomer(@RequestBody CustomerDto customerDto) {
        return onlineShopService.addCustomer(customerDto);
    }

    @PostMapping(value = "add/customer/{customer-id}/order")
    public OrderDto addOrder(@PathVariable("customer-id") Long customerId, @RequestBody OrderDto orderDto) {
        return onlineShopService.addOrder(customerId, orderDto);
    }

    @PostMapping(value = "add/product")
    public ProductDto addProduct(@RequestBody ProductDto productDto) {
        return onlineShopService.addProduct(productDto);
    }

    @GetMapping(value = "/customer")
    public CustomerDto getCustomerById(@RequestParam("id") Long id) {
        return onlineShopService.getCustomerById(id);
    }

    @GetMapping(value = "/order")
    public OrderDto getOrderById(@RequestParam("id") Long id) {
        return onlineShopService.getOrderById(id);
    }

    @GetMapping(value = "/product")
    public ProductDto getProductById(@RequestParam("id") Long id) {
        return onlineShopService.getProductById(id);
    }

    @GetMapping(value = "/orders/customer")
    public List<OrderDto> getOrdersByCustomerId(@RequestParam("id") Long id) {
        return onlineShopService.getOrdersByCustomerId(id);
    }

    @GetMapping(value = "/products/order")
    public List<ProductDto> getProductsByOrderId(@RequestParam("id") Long id) {
        return onlineShopService.getProductsByOrderId(id);
    }

    @PostMapping(value = "update/customer")
    public CustomerDto updateCustomer(@RequestBody CustomerDto customer) {
        return onlineShopService.addCustomer(customer);
    }

    @GetMapping(value = "/products_by_title")
    public List<ProductDto> getProductsByTitle(@RequestParam String title) {
        return onlineShopService.getProductsByTitle(title);
    }

    @PostMapping(value = "add_product_to_order")
    public void addOrdersProduct(@RequestBody ObjectNode objectNode) {
        Long orderId = objectNode.get("orderId").asLong();
        Long productId = objectNode.get("productId").asLong();
        onlineShopService.addOrdersProduct(orderId, productId);
    }
}

