package com.clubfactory.demo.test.aspect;


import com.clubfactory.demo.test.datasource.DataSource;
import com.clubfactory.demo.test.datasource.MultipleDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.lang.reflect.Method;

/**
 * @author zhehan
 * @date 2017/10/28
 */
@Slf4j
@Aspect
@Component
@Order(-2147483647)
public class MultipleDataSourceAspect {

    @Pointcut("execution(* com.clubfactory.demo.test.service.*.*(..))")
    public void executeBusiness() {
        //这里就是一个切面
    }

    @Before("executeBusiness()")
    public void invoke(JoinPoint joinPoint) {
        try {
            Signature signature = joinPoint.getSignature();
            MethodSignature methodSignature = (MethodSignature) signature;
            //method为接口的Method对象，获取不到实现类方法上的注解
            Method method = methodSignature.getMethod();
            //targetMethod为实现类方法对象
            Method targetMethod = joinPoint.getTarget().getClass().getMethod(method.getName(), method.getParameterTypes());
            //获取注解
            DataSource annotation = targetMethod.getAnnotation(DataSource.class);
            if (annotation != null) {
                //当前开启事务，不切换数据源
//                if (TransactionSynchronizationManager.isActualTransactionActive()) {
//                    log.info(String.format("当前开启事务，不切换数据源，切换数据源为主库; method:%s", method));
//                    MultipleDataSource.setDataSourceKey("coupon");
//                    return;
//                }
                MultipleDataSource.setDataSourceKey(annotation.value());
            }
        } catch (Exception e) {
            MultipleDataSource.setDataSourceKey("coupon");
            log.error("MultipleDataSourceAspectAdvice is error!", e);
        }
    }

    /**
     * 方法执行完后置空
     */
    @After("executeBusiness()")
    public void after(JoinPoint joinPoint) {
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            return;
        }

        if (TransactionSynchronizationManager.isSynchronizationActive()) {
            TransactionSynchronizationManager.clearSynchronization();
        }
        MultipleDataSource.setDataSourceKey(null);
    }
}
