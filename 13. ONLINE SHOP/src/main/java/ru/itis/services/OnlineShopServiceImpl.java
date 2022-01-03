package ru.itis.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dto.CustomerDto;
import ru.itis.dto.OrderDto;
import ru.itis.dto.ProductDto;
import ru.itis.models.Customer;
import ru.itis.models.Order;
import ru.itis.models.Product;
import ru.itis.repositories.CustomerRepository;
import ru.itis.repositories.OrderRepository;
import ru.itis.repositories.ProductRepository;

import java.util.List;

import static ru.itis.dto.ProductDto.from;
import static ru.itis.dto.OrderDto.from;
import static ru.itis.dto.CustomerDto.from;

@RequiredArgsConstructor
@Service
public class OnlineShopServiceImpl implements OnlineShopService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;


    @Override
    public OrderDto addOrder(Long customerId, OrderDto order) {
        Customer customer = customerRepository.getById(customerId);
        List<Product> products = productRepository.findAllByOrderId(order.getId());
        Order newOrder = Order.builder()
                .title(order.getTitle())
                .customer(customer)
                .products(products)
                .build();
        return from(orderRepository.save(newOrder));
    }

    @Override
    public CustomerDto addCustomer(CustomerDto customer) {
        List<Order> orders = orderRepository.findAllByCustomerId(customer.getId());
        Customer newCustomer = Customer.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .orders((orders))
                .build();
        return from(customerRepository.save(newCustomer));
    }

    @Override
    public ProductDto addProduct(ProductDto product) {
        Product newProduct = Product.builder()
                .id(product.getId())
                .title(product.getTitle())
                .price(product.getPrice())
                .build();
        return from(productRepository.save(newProduct));
    }

    @Override
    public OrderDto getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            return from(order);
        } else {
            return null;
        }
    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            return from(customer);
        } else {
            return null;
        }
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            return from(product);
        } else {
            return null;
        }
    }

    @Override
    public List<ProductDto> getProductsByOrderId(Long id) {
        return from(productRepository.findAllByOrderId(id));
    }

    @Override
    public List<OrderDto> getOrdersByCustomerId(Long id) {
        return OrderDto.from(orderRepository.findAllByCustomerId(id));
    }

    @Override
    public List<ProductDto> getProductsByTitle(String title) {
        return from(productRepository.findAllByTitleLike("%" + title + "%"));
    }

    @Override
    public void addOrdersProduct(Long orderId, Long productId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);
        if (order != null && product != null) {
            order.getProducts().add(product);
            orderRepository.save(order);
        }
    }
}
