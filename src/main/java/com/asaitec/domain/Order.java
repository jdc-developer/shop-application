package com.asaitec.domain;

import com.asaitec.domain.offers.Offer;
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

    @ManyToMany
    @JoinTable(
            name = "order_offer",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "offer_id"))
    private List<Offer> offers = new ArrayList<>();

    public Order(List<OrderLine> orderLines) {
        this.dtCreation = LocalDateTime.now();
        this.dtUpdate = LocalDateTime.now();
        this.orderLines.addAll(orderLines);
        this._calculateTotalAmountAndPrice();
    }

    public void decreaseValue(Double value) {
        this.totalPrice -= value;
    }

    public void addOrderLine(OrderLine orderLine) {
        this.orderLines.add(orderLine);
    }

    public void addOffer(Offer offer) {
        this.offers.add(offer);
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
