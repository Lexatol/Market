package ru.geekbrains.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.market.model.Profile;
import ru.geekbrains.market.repositories.ProfileRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final UserService userService;

    public Optional<Profile> findByUserName(String name) {
       return profileRepository.findByUsername(name);
    }

    public Optional<Profile> findById(Long id) {
        return profileRepository.findById(id);
    }

    public Profile saveOrUpdate(Profile profile) {
        return profileRepository.save(profile);
    }

}
