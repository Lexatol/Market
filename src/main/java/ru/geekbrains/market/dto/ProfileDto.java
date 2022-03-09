package ru.geekbrains.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.market.model.Profile;

@Data
@NoArgsConstructor
public class ProfileDto {
    private Long id;
    private String username;
    private String hobbies;
    private String confirmationPassword;

    public ProfileDto(Profile profile) {
        this.id = profile.getId();
        this.username = profile.getUser().getUsername();
        this.hobbies = profile.getHobbies();
    }
}
