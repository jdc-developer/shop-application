package com.asaitec.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double totalAmount;
    private LocalDateTime dtCreation;
    private LocalDateTime dtUpdate;

    @OneToMany
    @JoinColumn(referencedColumnName = "order")
    private List<OrderLine> orderLines = new ArrayList<>();

    public Order() {
        this.dtCreation = LocalDateTime.now();
        this.dtUpdate = LocalDateTime.now();
    }

    public void calculateTotalAmount() {
        AtomicReference<Double> totalAmount = new AtomicReference<>();
        totalAmount.set(0.0);
        this.orderLines.forEach(orderLine -> totalAmount.set(totalAmount.get()+orderLine.getAmount()));
        this.totalAmount = totalAmount.get();
    }
}
