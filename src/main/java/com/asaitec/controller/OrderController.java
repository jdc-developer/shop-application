package com.asaitec.controller;

import com.asaitec.dto.OrderCreateDTO;
import com.asaitec.dto.OrderDTO;
import com.asaitec.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final ModelMapper modelMapper;

    public OrderController(OrderService orderService, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderDTO> findByid(@PathVariable Integer id) {
        return ResponseEntity.ok(modelMapper.map(orderService.findById(id), OrderDTO.class));
    }

    @PostMapping
    public ResponseEntity<OrderDTO> create(@RequestBody OrderCreateDTO orderCreateDTO) {
        return ResponseEntity.ok(modelMapper.map(orderService.create(orderCreateDTO), OrderDTO.class));
    }
}
