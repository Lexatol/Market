package ru.geekbrains.market.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "profiles")
@Data
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn (name = "user_id")
    private User user;

    @Column (name = "hobbies")
    private String hobbies;

}
