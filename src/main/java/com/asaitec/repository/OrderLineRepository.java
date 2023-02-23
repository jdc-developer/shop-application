package com.asaitec.repository;

import com.asaitec.domain.OrderLine;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface OrderLineRepository extends JpaRepositoryImplementation<OrderLine, Integer> {
}
