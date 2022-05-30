package ru.geekbrains.market.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.market.model.Profile;
import ru.geekbrains.market.model.User;
import ru.geekbrains.market.repositories.ProfileRepository;

import java.util.Optional;

@SpringBootTest
public class ProfileServiceTest {


    @MockBean
    private ProfileRepository profileRepository;

    @Autowired
    private ProfileService profileService;

    @Test
    public void findByUsernameTest() {
        Profile profileFromDB = new Profile();
        User user = new User();
        user.setUsername("Petro");
        user.setEmail("milo_Petro");
        profileFromDB.setUser(user);
        Mockito.doReturn(Optional.of(profileFromDB))
                .when(profileRepository)
                .findByUsername("Petro");

        Profile profile = profileService.findByUserName("Petro").get();
        Assertions.assertNotNull(profile);
        Assertions.assertEquals("milo_Petro", profile.getUser().getEmail());
        Mockito.verify(profileRepository, Mockito.times(1)).findByUsername("Petro");
    }

    @Test
    public void findByIdTest() {
        Profile profileFromDB =  new Profile();
        User user = new User();
        user.setUsername("Petro");
        user.setEmail("petro@milo");
        profileFromDB.setId(1L);
        profileFromDB.setUser(user);

        Mockito.doReturn(Optional.of(profileFromDB))
                .when(profileRepository)
                .findById(1L);

        Profile profile = profileService.findById(1L).get();
        Assertions.assertNotNull(profile);
        Assertions.assertEquals("Petro", profile.getUser().getUsername());
        Mockito.verify(profileRepository, Mockito.times(1)).findById(1L);
    }
}
