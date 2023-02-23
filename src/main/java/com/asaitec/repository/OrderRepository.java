package com.asaitec.repository;

import com.asaitec.domain.Order;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface OrderRepository extends JpaRepositoryImplementation<Order, Integer> {
}
