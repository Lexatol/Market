package ru.geekbrains.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.market.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
