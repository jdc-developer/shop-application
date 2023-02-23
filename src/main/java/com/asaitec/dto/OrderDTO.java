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
public class OrderDTO {

    private Integer id;
    private Double totalAmount;
    private Double totalPrice;
    private LocalDateTime dtCreation;
    private LocalDateTime dtUpdate;
}
