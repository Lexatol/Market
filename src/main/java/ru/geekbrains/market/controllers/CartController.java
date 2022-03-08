package ru.geekbrains.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.market.beans.Cart;
import ru.geekbrains.market.dto.CartDto;

@RestController
@RequestMapping("api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final Cart cart;

    @GetMapping(produces = "application/json")
    public CartDto getCart() {
        return new CartDto(cart);
    }

    @GetMapping(value = "/add/{id}", produces = "application/json")
    public void addToCart(@PathVariable Long id) {
        cart.addToCart(id);
    }

    @GetMapping("/dec/{id}")
    public void decCart(@PathVariable Long id) {
        cart.decCart(id);
    }

    @GetMapping("/remove/{id}")
    public void removeCart(@PathVariable Long id) {
        cart.removeCart(id);
    }

    @GetMapping("/clear")
    public void clearCart() {
        cart.clear();
    }

    @GetMapping(value = "/dec/{id}", produces = "application/json")
    public void decrementOrRemoveById(@PathVariable Long id) {
        cart.decCart(id);
    }

    @GetMapping("/remove/{id}")
    public void removeById(@PathVariable Long id) {
        cart.removeCart(id);
    }
}
