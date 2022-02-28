package ru.geekbrains.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.market.beans.Cart;
import ru.geekbrains.market.dto.OrderDto;
import ru.geekbrains.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.market.model.Order;
import ru.geekbrains.market.model.User;
import ru.geekbrains.market.services.OrderService;
import ru.geekbrains.market.services.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final Cart cart;

    @GetMapping
    public List<OrderDto> getAllOrder(Principal principal) {
        return orderService.findAllUserOrderDto(principal.getName());
    }

    @PostMapping
    public void createNewOrder(Principal principal, @RequestParam String address) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(()->
                new ResourceNotFoundException("Unable to create order for user: " + principal.getName() + ". User doesn't exist"));
        Order order = new Order(user, cart, address);
        orderService.save(order);
        cart.clear();
    }

}
