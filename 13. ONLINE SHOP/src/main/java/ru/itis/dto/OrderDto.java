package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Order;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    private Long id;
    private String title;
    private String customerName;

    public static OrderDto from(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .title(order.getTitle())
                .customerName(order.getCustomer().getFirstName() + " " + order.getCustomer().getLastName())
                .build();
    }

    public static List<OrderDto> from(List<Order> orders) {
        return orders.stream().map(OrderDto::from).collect(toList());
    }
}
