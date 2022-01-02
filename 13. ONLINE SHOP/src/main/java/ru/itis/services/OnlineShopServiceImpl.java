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
        List<Product> products = productRepository.getAllByOrderId(order.getId());
        Order newOrder = Order.builder()
                .title(order.getTitle())
                .customer(customer)
                .products(products)
                .build();
        return from(orderRepository.save(newOrder));

    }

    @Override
    public CustomerDto addCustomer(CustomerDto customer) {
        List<Order> orders = orderRepository.findAllByCustomer_Id(customer.getId());
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
        return from(orderRepository.getById(id));
    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        return from(customerRepository.getById(id));
    }

    @Override
    public ProductDto getProductById(Long id) {
        return from((productRepository.getById(id)));
    }

    @Override
    public List<ProductDto> getProductsByOrderId(Long id) {
        return from(productRepository.getAllByOrderId(id));
    }

    @Override
    public List<OrderDto> getOrdersByCustomerId(Long id) {
        return from(orderRepository.findAllByCustomer_Id(id));
    }

    @Override
    public List<ProductDto> getProductsByTitle(String title) {
        return from(productRepository.getAllByTitleLike("%" + title + "%"));
    }

    @Override
    public void addOrdersProduct(Long orderId, Long productId) {
        Order order = orderRepository.getById(orderId);
        Product product = productRepository.getById(productId);
        order.getProducts().add(product);
        orderRepository.save(order);
    }
}
