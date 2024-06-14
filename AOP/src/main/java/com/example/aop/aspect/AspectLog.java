package com.example.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectLog {
    private Logger logger = LoggerFactory.getLogger(AspectLog.class);

    @Before("execution (* com.example.aop.service.*.*(..))")
    public void logBeforeExecution(){
        logger.info("Before execute Devision Function");
    }

    @After("execution(* com.example.aop.service.*.*(..))")
    public  void logAfterExecution(){
        logger.info("After execute Devision Function");
    }

    @AfterReturning("execution(* com.example.aop.service.*.*(..))")
    public String logAfterReturning(){
        logger.info("AfterReturning execute Devision Function");
        return "execution Successfull ... ";
    }

    @AfterThrowing(value = "execution(* com.example.aop.service.*.*(..))",throwing = "ex")
    public Throwable logAfterThrowing(Throwable ex){
        logger.info("AfterThrowing execute Devision Function");
        return ex;
    }

    @Around("execution(* com.example.aop.service.*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint){
        logger.info("Around  : Before execute Devision Function");
        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        logger.info("Around  : After execute Devision Function");
        //change return value Devision function
      //  result = "30";
        return  result;
    }
}
