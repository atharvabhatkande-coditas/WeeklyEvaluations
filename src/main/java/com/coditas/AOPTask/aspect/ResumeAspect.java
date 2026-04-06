package com.coditas.AOPTask.aspect;

import com.coditas.AOPTask.entity.Payment;
import com.coditas.AOPTask.entity.Resume;
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
public class ResumeAspect {

    @Pointcut("execution(* com.coditas.AOPTask.service.ResumeService.uploadResume(..))")
    public void forResume(){}

    @AfterReturning(pointcut = "forResume()",returning="result")
    public void logAfterPaymentSuccess(Resume result){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        System.out.println("Resume Updated Successfully");
    }
    @AfterThrowing(pointcut = "forResume()",throwing = "ex")
    public String paymentException(JoinPoint joinPoint, Exception ex){
        System.out.println("Exception Occurred:"+joinPoint.getSignature().getName());
        System.out.println("Failure Reason"+ex.getMessage());
        return ex.getMessage();
    }

}
