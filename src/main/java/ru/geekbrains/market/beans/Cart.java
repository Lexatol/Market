package ru.geekbrains.market.beans;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.market.model.OrderItem;
import ru.geekbrains.market.model.Product;
import ru.geekbrains.market.services.ProductService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@RequiredArgsConstructor
@Data
public class Cart {
    private List<OrderItem> items;
    private final ProductService productService;
    private int totalPrice;

    @PostConstruct
    public void init() {
        this.items = new ArrayList<>();
    }

    public void addToCart(Long id) {
        for (OrderItem o : items) {
            if (o.getProduct().getId().equals(id)) {
                o.incrementQuantity();
                recalculate();
                return;
            }
        }
        Product p = productService.findProductById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find product with id: " + id + " (add to cart)"));
        OrderItem orderItem = new OrderItem(p);
        items.add(orderItem);
        recalculate();
    }

    public void decCart(Long id) {
        Iterator<OrderItem> iter = items.iterator();
        while(iter.hasNext()) {
            OrderItem o = iter.next();
            if (o.getProduct().getId().equals(id)) {
                o.decrementQuantity();
                if (o.getQuantity() == 0) {
                    iter.remove();
                }
                recalculate();
                return;
            }
        }
    }

    public void removeCart(Long id) {
        Iterator<OrderItem> iter = items.iterator();
        while(iter.hasNext()) {
            OrderItem o = iter.next();
            if (o.getProduct().getId().equals(id)) {
                iter.remove();
                recalculate();
                return;
            }
        }
    }

    public void clear() {
        items.clear();
        recalculate();
    }

    public void recalculate() {
        totalPrice = 0;
        for (OrderItem o : items) {
            totalPrice += o.getPrice();
        }
    }
}