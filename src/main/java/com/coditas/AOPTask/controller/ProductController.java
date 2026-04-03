package com.coditas.AOPTask.controller;

import com.coditas.AOPTask.DTO.ApplicationResponse;
import com.coditas.AOPTask.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @PatchMapping("/update/{id}")
    public ResponseEntity<ApplicationResponse<String>>updateProduct(@RequestBody Map<String,Object> updates,@PathVariable Long id){
        ApplicationResponse<String>applicationResponse=new ApplicationResponse<>(productService.updateProduct(updates,id));
        return new ResponseEntity<>(applicationResponse, HttpStatus.OK);
    }
}
