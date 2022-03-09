package ru.geekbrains.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.geekbrains.market.model.Profile;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Query("select p from Profile p where p.user.username = ?1")
    Optional<Profile> findByUsername(String username);
}
