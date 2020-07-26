package com.datanew.aop;

import com.datanew.util.ConfigureParser;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/10.
 */
@Aspect
@Component
public class ParameterIncepter {
    @Around("execution(* com.datanew.service.impl.*.*(..))")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
            System.out.println(String.format("方法%s被调用",pjp.getSignature()));
            System.out.println("参数："+Arrays.toString(pjp.getArgs()));
           return pjp.proceed();

    }
}
