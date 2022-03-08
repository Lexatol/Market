package ru.geekbrains.market.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import ru.geekbrains.market.beans.Cart;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<OrderItem> items;

    @Column (name = "price")
    private int price;

    @Column (name = "address")
    private String address;

    public Order(User user, Cart cart, String address) {
        this.user = user;
        this.price = cart.getTotalPrice();
        this.address = address;
        this.items = new ArrayList<>();
        cart.getItems().stream().forEach(oi-> {
            oi.setOrder(this);
            items.add(oi);
        });
    }



}
