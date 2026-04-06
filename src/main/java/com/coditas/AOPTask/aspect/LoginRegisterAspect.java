package com.coditas.AOPTask.aspect;

import com.coditas.AOPTask.entity.Payment;
import com.coditas.AOPTask.entity.TransactionDetails;
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
public class LoginRegisterAspect {

    @Pointcut("execution(* com.coditas.AOPTask.service.RegisterUserService.loginUser(..))")
    public void forLogin(){}

    @AfterReturning(pointcut = "forLogin()",returning="result")
    public void logAfterPaymentSuccess(String result){
        System.out.println(result);
        System.out.println("User Logged in Successfully");
    }


    @AfterThrowing(pointcut = "forLogin()",throwing = "ex")
    public String paymentException(JoinPoint joinPoint, Exception ex){
        System.out.println("Exception Occurred:"+joinPoint.getSignature().getName());
        System.out.println("Failure Reason"+ex.getMessage());
        return (ex.getMessage()+"Please Retry your payment");
    }
}
