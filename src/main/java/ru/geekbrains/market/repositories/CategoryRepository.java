package ru.geekbrains.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.market.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
