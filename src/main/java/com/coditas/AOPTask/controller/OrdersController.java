package com.coditas.AOPTask.controller;

import com.coditas.AOPTask.DTO.ApplicationResponse;
import com.coditas.AOPTask.entity.Customer;
import com.coditas.AOPTask.entity.Orders;
import com.coditas.AOPTask.entity.Payment;
import com.coditas.AOPTask.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OrdersController {
    private final OrdersService ordersService;
    @PostMapping("/order")
    public ResponseEntity<ApplicationResponse<String>> orderItems(@RequestBody Orders order){
        ApplicationResponse<String>applicationResponse=new ApplicationResponse<>( ordersService.orderItems(order));
        return  new ResponseEntity<>(applicationResponse, HttpStatus.OK);
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<ApplicationResponse<String>> cancelOrder(@PathVariable Long id){
        ApplicationResponse<String>applicationResponse=new ApplicationResponse<>( ordersService.cancelOrder(id));
        return  new ResponseEntity<>(applicationResponse, HttpStatus.OK);
    }

    @PostMapping("/pay")
    public ResponseEntity<ApplicationResponse<Payment>>makePayment(@RequestBody Payment payment){
        ApplicationResponse<Payment>applicationResponse=new ApplicationResponse<>(ordersService.makePayment(payment));
        return new ResponseEntity<>(applicationResponse,HttpStatus.OK);
    }

}
