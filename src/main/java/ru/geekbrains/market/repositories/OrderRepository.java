package ru.geekbrains.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.geekbrains.market.dto.OrderDto;
import ru.geekbrains.market.model.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("Select o from Order o where o.user.username = ?1")
    List<Order> findAllUserOrdersByUsername(String username);
}
