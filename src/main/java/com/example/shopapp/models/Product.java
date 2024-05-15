package com.example.shopapp.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 350)
    private String name;

    private Float price;

    @Column(name = "thumbnail", length = 300)
    private String thumbnail;

    @Column(name = "description")
    private String description;

//    @Column(name = "create_at")
//    private LocalDateTime createdAt;
//
//    @Column(name = "update_at")
//    private LocalDateTime updateAt;
//
//    @PrePersist
//    protected  void onCreate(){
//        createdAt= LocalDateTime.now();
//        updateAt=LocalDateTime.now();
//    }
//
//    @PreUpdate
//    protected void onUpdate() {
//        updateAt = LocalDateTime.now();
//    }

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
