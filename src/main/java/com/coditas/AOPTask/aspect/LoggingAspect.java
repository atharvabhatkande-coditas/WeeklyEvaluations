package com.coditas.AOPTask.aspect;

import com.coditas.AOPTask.entity.LogAudit;
import com.coditas.AOPTask.repository.LogAuditRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Aspect
public class LoggingAspect {
    @Autowired
    LogAuditRepository logAuditRepository;
    @Pointcut("execution(* com.coditas.AOPTask.service.OrdersService.*(..))")
    public void forOrder(){}
    @Pointcut("execution(* com.coditas.AOPTask.service.ProductService.*(..))")
    public void forProduct(){}

    @Around("forOrder() || forProduct()")
    public Object monitorPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Before Method call");
        Object result=joinPoint.proceed();
        System.out.println("After Method call");
        return result;
    }
    @After("forOrder() || forProduct()")
    public void auditLog(JoinPoint joinPoint) throws Throwable {
        LogAudit logAudit=new LogAudit(null,"User",joinPoint.getKind(), LocalDateTime.now(),joinPoint.getSignature().getName());
        logAuditRepository.save(logAudit);
    }


}
