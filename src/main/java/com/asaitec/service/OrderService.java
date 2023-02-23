package com.asaitec.service;

import com.asaitec.domain.Order;
import com.asaitec.domain.OrderLine;
import com.asaitec.domain.Product;
import com.asaitec.dto.OrderCreateDTO;
import com.asaitec.repository.OrderRepository;
import com.asaitec.service.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;

    public OrderService(OrderRepository orderRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    public Order findById(Integer id) {
        return orderRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException());
    }

    public Order create(OrderCreateDTO orderCreateDTO) {
        List<OrderLine> orderLines = new ArrayList<>();
        orderCreateDTO.getOrderLines().forEach(orderLineCreateDTO -> {
            Product product = productService.findById(orderLineCreateDTO.getProduct().getId());
            OrderLine orderLine = new OrderLine(product, orderLineCreateDTO.getAmount());
            orderLines.add(orderLine);
        });

        Order order = new Order(orderLines);
        return orderRepository.save(order);
    }

    public void delete(Integer id) {
        findById(id);
        orderRepository.deleteById(id);
    }
}
