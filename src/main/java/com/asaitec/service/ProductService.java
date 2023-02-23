package com.asaitec.service;

import com.asaitec.domain.Product;
import com.asaitec.dto.ProductCreateDTO;
import com.asaitec.dto.ProductUpdateDTO;
import com.asaitec.repository.ProductRepository;
import com.asaitec.service.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findById(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException());
    }

    public Product create(ProductCreateDTO productCreateDTO) {
        Product product = new Product(productCreateDTO.getName(), productCreateDTO.getPrice());
        return productRepository.save(product);
    }

    public Product update(ProductUpdateDTO productUpdateDTO) {
        Product product = findById(productUpdateDTO.getId());

        product.setName(productUpdateDTO.getName());
        product.setPrice(productUpdateDTO.getPrice());
        product.setDtUpdate(LocalDateTime.now());

        return productRepository.save(product);
    }

    public void delete(Integer id) {
        findById(id);
        productRepository.deleteById(id);
    }
}
