package ru.geekbrains.market.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table (name = "products")
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column (name = "title")
    private String title;

    @Column (name = "price")
    private int price;

    @Column (name = "created_at")
    @CreationTimestamp
    private LocalDateTime createAt;

    @Column (name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updateAt;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "category_id")
    private Category category;

}
