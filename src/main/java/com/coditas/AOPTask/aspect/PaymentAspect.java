package com.coditas.AOPTask.aspect;

import com.coditas.AOPTask.entity.Payment;
import com.coditas.AOPTask.entity.TransactionDetails;
import com.coditas.AOPTask.repository.TransactionDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Aspect
@RequiredArgsConstructor
public class PaymentAspect {
    private final TransactionDetailsRepository transactionDetailsRepository;

    @Pointcut("execution(* com.coditas.AOPTask.service.OrdersService.makePayment(..))")
    public void forPayment(){}

    @AfterReturning(pointcut = "forPayment()",returning="result")
    public String logAfterPaymentSuccess(Payment result){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();

        TransactionDetails transactionDetails=new TransactionDetails(null,username, LocalDateTime.now(),"Card",result.getOrderId());
        transactionDetailsRepository.save(transactionDetails);
        System.out.println("Transaction ID:"+result.getId());

        System.out.println("Email SMS: Order Placed Successfully");
        return "Payment Successful. Order Placed Successfully";
    }
    @AfterThrowing(pointcut = "forPayment()",throwing = "ex")
    public String paymentException(JoinPoint joinPoint, Exception ex){
        System.out.println("Exception Occurred:"+joinPoint.getSignature().getName());
        System.out.println("Failure Reason"+ex.getMessage());
        return (ex.getMessage()+"Please Retry your payment");
    }
}
