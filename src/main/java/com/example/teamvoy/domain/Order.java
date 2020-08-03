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
@Table(name = "order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price")
    private Double price;

    @Column(name = "created_date")
    private Instant createdDate;

    @Column(name = "expiration_date")
    private Instant expirationDate;

    @Column(name = "quantity")
    private Long quantity;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

}
