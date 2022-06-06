package ru.geekbrains.market.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import ru.geekbrains.market.model.User;
import ru.geekbrains.market.repositories.UserRepository;

import java.util.Optional;

@SpringBootTest
@ActiveProfiles("h2")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;


    @Test
    public void findOneUserTest() {
        User userDb = new User();
        userDb.setUsername("Petro");
        userDb.setEmail("petro@gmail.com");

        Mockito.doReturn(Optional.of(userDb))
                .when(userRepository)
                .findByUsername("Petro");

        User userPetro = userService.findByUsername("Petro").get();
        Assertions.assertNotNull(userPetro);
        Assertions.assertEquals("petro@gmail.com", userPetro.getEmail());
        Mockito.verify(userRepository, Mockito.times(1)).findByUsername("Petro");
    }
}
