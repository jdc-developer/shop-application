package com.asaitec.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineDTO {

    private Integer id;
    private Integer amount;
    private Double price;
    private LocalDateTime dtCreation;
    private LocalDateTime dtUpdate;
    private ProductDTO product;
}
