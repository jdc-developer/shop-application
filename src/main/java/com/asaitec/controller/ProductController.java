package com.asaitec.controller;

import com.asaitec.dto.ProductCreateDTO;
import com.asaitec.dto.ProductDTO;
import com.asaitec.dto.ProductUpdateDTO;
import com.asaitec.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDTO> findByid(@PathVariable Integer id) {
        return ResponseEntity.ok(modelMapper.map(productService.findById(id), ProductDTO.class));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody ProductCreateDTO productCreateDTO) {
        return ResponseEntity.ok(modelMapper.map(productService.create(productCreateDTO), ProductDTO.class));
    }

    @PutMapping
    public ResponseEntity<ProductDTO> create(@RequestBody ProductUpdateDTO productUpdateDTO) {
        return ResponseEntity.ok(modelMapper.map(productService.update(productUpdateDTO), ProductDTO.class));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
