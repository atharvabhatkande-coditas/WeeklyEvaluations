package com.coditas.AOPTask.service;

import com.coditas.AOPTask.DTO.ErrorResponse;
import com.coditas.AOPTask.aspect.Authorize;
import com.coditas.AOPTask.entity.Product;
import com.coditas.AOPTask.exception.NotFoundException;
import com.coditas.AOPTask.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final JsonMapper jsonMapper;

    @Authorize("ROLE_ADMIN")
    public String updateProduct(Map<String, Object> updates,Long id) {
        Product product=productRepository.findById(id).orElseThrow(()->new NotFoundException("Product not found"));
        Product updatedProduct=jsonMapper.updateValue(product,updates);
        productRepository.save(updatedProduct);
        return "Product Updated Successfully";
    }
}
