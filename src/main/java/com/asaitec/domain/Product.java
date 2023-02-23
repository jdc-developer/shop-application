package com.asaitec.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Double price;
    private LocalDateTime dtCreation;
    private LocalDateTime dtUpdate;

    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
        this.dtCreation = LocalDateTime.now();
        this.dtUpdate = LocalDateTime.now();
    }

}
