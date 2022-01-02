package ru.itis.services;

import ru.itis.dto.CustomerDto;
import ru.itis.dto.OrderDto;
import ru.itis.dto.ProductDto;

import java.util.List;

public interface OnlineShopService {
    OrderDto addOrder(Long customerId, OrderDto order);

    CustomerDto addCustomer(CustomerDto customer);

    ProductDto addProduct(ProductDto product);

    OrderDto getOrderById(Long id);

    CustomerDto getCustomerById(Long id);

    ProductDto getProductById(Long Id);

    List<ProductDto> getProductsByOrderId(Long id);

    List<OrderDto> getOrdersByCustomerId(Long id);

    List<ProductDto> getProductsByTitle(String title);

    void addOrdersProduct(Long orderId, Long productId);
}
