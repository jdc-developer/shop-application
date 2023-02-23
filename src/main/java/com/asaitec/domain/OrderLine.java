package com.asaitec.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Order order;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    private Integer amount;
    private Double price;
    private LocalDateTime dtCreation;
    private LocalDateTime dtUpdate;

    public OrderLine(Product product, Integer amount) {
        this.product = product;
        this.amount = amount;
        this.price = product.getPrice() * amount;
        this.dtCreation = LocalDateTime.now();
        this.dtUpdate = LocalDateTime.now();
    }
}
