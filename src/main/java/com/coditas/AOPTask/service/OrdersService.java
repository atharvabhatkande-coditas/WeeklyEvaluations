package com.coditas.AOPTask.service;
import com.coditas.AOPTask.DTO.ErrorResponse;
import com.coditas.AOPTask.entity.Orders;
import com.coditas.AOPTask.entity.Payment;
import com.coditas.AOPTask.entity.Product;
import com.coditas.AOPTask.entity.TransactionDetails;
import com.coditas.AOPTask.exception.NoStock;
import com.coditas.AOPTask.exception.NotFoundException;
import com.coditas.AOPTask.exception.ValidationException;
import com.coditas.AOPTask.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderItemsRepository orderItemsRepository;
    private final PaymentRepository paymentRepository;
    private final TransactionDetailsRepository transactionDetailsRepository;

    public String orderItems(Orders order) {
        List<Product>productList=productRepository.findAll();
        List<String>productNameList=productList.stream().map(product -> product.getName()).toList();
        List<Product> existingProductList =productRepository.findByNameIn(productNameList);
        if(existingProductList.isEmpty()){
            throw new NotFoundException("No product found");
        }

        order.getOrderItemsList().forEach(orderItems -> orderItems.setOrder(order));
        order.getOrderItemsList().forEach(orderItems -> {
            existingProductList.forEach(product -> {
                if(product.getStock()<orderItems.getQnty()){
                    throw new NoStock("Product out of stock");
                }
                else{
                    product.setStock(product.getStock()-orderItems.getQnty());
                }
            });
        });

        ordersRepository.save(order);
        return "Order Added to Card Successfully Please Complete the payment to place the order";
    }

    public String cancelOrder(Long id) {
        ordersRepository.deleteById(id);
        return "Order cancelled ";
    }

    public Payment makePayment(Payment payment) {
        if(payment.getCardNumber().length()<16){
            throw new ValidationException("Invalid Card Number");
        }
        if(payment.getAmount()<0){
            throw  new ValidationException("Amount Invalid");
        }
        paymentRepository.save(payment);

        return payment;
    }
}
