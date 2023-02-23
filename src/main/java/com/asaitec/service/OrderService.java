package com.asaitec.service;

import com.asaitec.domain.Order;
import com.asaitec.domain.OrderLine;
import com.asaitec.domain.Product;
import com.asaitec.dto.OrderCreateDTO;
import com.asaitec.repository.OrderLineRepository;
import com.asaitec.repository.OrderRepository;
import com.asaitec.service.exception.ObjectNotFoundException;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;
    private final ProductService productService;

    public OrderService(OrderRepository orderRepository, OrderLineRepository orderLineRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.orderLineRepository = orderLineRepository;
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

        Order order = new Order();
        order.getOrderLines().addAll(orderLines);
        order.calculateTotalAmount();

        order = orderRepository.save(order);
        AtomicReference<Order> orderAtomicReference = new AtomicReference<>();
        orderAtomicReference.set(order);
        orderLines.forEach(orderLine -> {
            orderLine.setOrder(orderAtomicReference.get());
            orderLineRepository.save(orderLine);
        });

        return order;
    }
}
