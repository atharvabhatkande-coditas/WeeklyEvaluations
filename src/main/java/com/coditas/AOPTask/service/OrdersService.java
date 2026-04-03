package com.coditas.AOPTask.service;
import com.coditas.AOPTask.DTO.ErrorResponse;
import com.coditas.AOPTask.entity.Orders;
import com.coditas.AOPTask.entity.Product;
import com.coditas.AOPTask.exception.NoStock;
import com.coditas.AOPTask.exception.NotFoundException;
import com.coditas.AOPTask.repository.CustomerRepository;
import com.coditas.AOPTask.repository.OrderItemsRepository;
import com.coditas.AOPTask.repository.OrdersRepository;
import com.coditas.AOPTask.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderItemsRepository orderItemsRepository;

    public String orderItems(Orders order) {
        List<Product>productList=productRepository.findAll();
        List<String>productNameList=productList.stream().map(product -> product.getName()).toList();
        List<Product> existingProductList =productRepository.findByNameIn(productNameList);
        if(existingProductList.isEmpty()){
            throw new NotFoundException("No product found");
        }

        order.getOrderItemsList().forEach(orderItems -> orderItems.setOrder(order));

        ordersRepository.save(order);
        return "Order placed Successfully";
    }

    public String cancelOrder(Long id) {
        ordersRepository.deleteById(id);
        return "Order cancelled ";
    }
}
