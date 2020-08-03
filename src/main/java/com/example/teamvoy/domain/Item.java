package com.example.teamvoy.domain;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "item")
public class Item {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "created_date")
    private Instant createdDate;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "is_available")
    private Boolean isAvailable;

    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItems;

}
