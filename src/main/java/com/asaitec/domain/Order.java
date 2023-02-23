package com.asaitec.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double totalAmount;
    private Double totalPrice;
    private LocalDateTime dtCreation;
    private LocalDateTime dtUpdate;

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    @JoinColumn(name="order_id")
    private List<OrderLine> orderLines = new ArrayList<>();

    public Order(List<OrderLine> orderLines) {
        this.dtCreation = LocalDateTime.now();
        this.dtUpdate = LocalDateTime.now();
        this.orderLines.addAll(orderLines);
        this._calculateTotalAmountAndPrice();
    }

    private void _calculateTotalAmountAndPrice() {
        this.totalAmount = 0.0;
        this.totalPrice = 0.0;
        this.orderLines.forEach(orderLine -> {
            this.totalAmount += orderLine.getAmount();
            this.totalPrice += orderLine.getPrice();
        });
    }
}
