package com.asaitec.repository;

import com.asaitec.domain.Product;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface ProductRepository extends JpaRepositoryImplementation<Product, Integer> {
}
