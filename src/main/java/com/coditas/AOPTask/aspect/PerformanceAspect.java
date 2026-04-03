package com.coditas.AOPTask.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerformanceAspect {
    @Pointcut("execution(* com.coditas.AOPTask.service.OrdersService.*(..))")
    public void forOrder(){}
    @Pointcut("execution(* com.coditas.AOPTask.service.ProductService.*(..))")
    public void forProduct(){}

    @Around("forOrder() || forProduct()")
    public Object monitorPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime=System.currentTimeMillis();
        Object result=joinPoint.proceed();
        System.out.println("Execution Time: "+(System.currentTimeMillis()-startTime)+" ms");
        return result;
    }

}
