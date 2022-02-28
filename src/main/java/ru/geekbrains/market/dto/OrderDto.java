package ru.geekbrains.market.dto;

import ru.geekbrains.market.model.Order;

import java.util.List;
import java.util.stream.Collectors;

public class OrderDto {

    private String username;
    private List<OrderItemDto> items;
    private int price;
    private String address;

    public OrderDto(Order order) {
        this.username = order.getUser().getUsername();
        this.items = order.getItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
        this.price = order.getPrice();
    }
}
